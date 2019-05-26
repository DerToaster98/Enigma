package de.Enigma.Algorithm;

import de.Enigma.Core.Log;
import de.Enigma.Util.Enums.EAlphabet;
import de.Enigma.Util.Enums.EMill;
import de.Enigma.Util.Enums.EMillAlphabet;

import java.util.HashMap;

/**
 * @author Oliver Seiler
 * @brief Klasse, welche essenziell für die Verschlüsselung ist
 * @details Enthält eine HashMap, welche das "Steckbrett" realisiert
 * @details Enthält die Instanzen der Walzen, welche benutzt werden
 */
public class EnigmaConfig {

    private String key;
    private HashMap<Character, Character> letterChanger = new HashMap<>();
    private Mill[] mills = new Mill[]{null, null, null};
    private Mill reverseMill;


    /**
     * @brief Constructor, erzeugt die Instanzen der Walzen und das Steckbrett anhand des Schlüssels
     */
    public EnigmaConfig(String key) {
        // TODO: Unit test wäre hier sinnvoll, da hier nicht überprüft wird, ob der key gültig ist
        this.key = key;
        // DONE Key auf Richtigkeit prüfen -> Algorithm, wird vorüberprüft
        // DONE Walzen erstellen und hier zuweisen
        String[] keyValues = this.key.split("-");
        EMillAlphabet reverseMillType = EMillAlphabet.getByID(keyValues[0]);

        // Holt sich die "Konfiguration" der Walzen aus dem key
        char[] millIDs = keyValues[1].toCharArray();
        EMillAlphabet millOneType = EMillAlphabet.getByID(String.valueOf(millIDs[0]));
        EMillAlphabet millTwoType = EMillAlphabet.getByID(String.valueOf(millIDs[1]));
        EMillAlphabet millThreeType = EMillAlphabet.getByID(String.valueOf(millIDs[2]));

        // Holt sich die Startwerte der Walzen aus dem key
        char[] millStartMarks = keyValues[2].toCharArray();
        this.reverseMill = new Mill('?', reverseMillType);
        this.mills[0] = new Mill(millStartMarks[0], millOneType);
        this.mills[1] = new Mill(millStartMarks[1], millTwoType);
        this.mills[2] = new Mill(millStartMarks[2], millThreeType);
        // DONE "Steckbrett" befüllen
        if (keyValues.length > 3) {
            for (int i = 3; i < keyValues.length; i++) {
                String[] plugConf = keyValues[i].split(";");
                if (plugConf.length == 2) {
                    this.letterChanger.put(new Character(plugConf[0].toCharArray()[0]), new Character(plugConf[1].toCharArray()[0]));
                    this.letterChanger.put(new Character(plugConf[1].toCharArray()[0]), new Character(plugConf[0].toCharArray()[0]));
                }
            }
        }
        // DONE Buchstaben verschlüsseln schreiben
    }

    /**
     * @param letter          Der Buchstabe der verschlüsselt werden soll.
     * @param mill            Die Walze, welche verschlüsseln soll.
     * @param wasInReturnMill Ob nach diesem Entschlüsselvorgang die Walzen rotiert und gedreht werden sollen.
     * @return Der verschlüsselte Buchstabe
     * @brief Methode, welche für die eigentliche "Veschlüsselung" verantworlich ist. Diese verschlüsselt einzelne Buchstaben.
     */
    public char encryptLetter(char letter, EMill mill, boolean wasInReturnMill) {
        if (letter == ' ') {
            return letter;
        }
        char[] oldAlphabet = getPreviousAlphabet(mill, wasInReturnMill);

        letter = getMill(mill).encryptLetter(letter, oldAlphabet);

        if (wasInReturnMill && mill.equals(EMill.THIRD_MILL)) {
            getMill(EMill.THIRD_MILL).rotateMill();
            checkMills();
        }
        return letter;
    }

    /**
     * @param letter Der Buchstabe, welcher ersetzt werden soll
     * @return Liefert den ersetzten Buchstaben zurück
     * @brief Verschlüsselung durch Steckbrett
     */
    public char encryptLetterWithPlugBoard(char letter) {
        if (letter == ' ') {
            return letter;
        }
        Character character = new Character(letter);
        char toEncrypt = letter;
        if (!this.letterChanger.isEmpty() && this.letterChanger.containsKey(character)) {
            toEncrypt = this.letterChanger.get(character).charValue();
        }
        return toEncrypt;
    }

    /**
     * @brief Diese Methode überprüft die Walzen, und zwar, ob sie drehen sollten.
     * @details Überprüft die Walzen auf ihre Kerben. wenn die dritte Walze die Kerbe überschritten hat, so rotiert auch die zweite. \n
     * Hat auch die zweite Walze die Kerbe überschritten, so rotiert auch die erste....
     */
    public void checkMills() {
        Mill first = getMill(EMill.FIRST_MILL);
        if (first != null && first.shouldRotateNeighborMill()) {
            Mill second = getMill(EMill.SECOND_MILL);
            if (second != null) {
                second.rotateMill();
                if (second.shouldRotateNeighborMill()) {
                    Mill third = getMill(EMill.THIRD_MILL);
                    if (third != null) {
                        third.rotateMill();
                    }
                }
            }
        }
    }

    /**
     * @param mill Beschreibt durch ein Enum, welche Walze gemeint ist
     * @return Liefert die Instanz einer Walze zurück, welche durch 'mill' angesprochen wird -> FIRST_MILL: linke Walze, SECOND_MILL: mittlere Walze, etc.
     * @brief Benutzt, um an die Instanzen der Walzen zu gelangen
     */
    Mill getMill(EMill mill) {
        switch (mill) {
            case FIRST_MILL:
                return this.reverseMill;
            case REVERSE_MILL:
                return this.mills[0];
            case SECOND_MILL:
                return this.mills[1];
            case THIRD_MILL:
                return this.mills[2];
            default:
                Log.getLogger().w(getClass().getName() + ".getMill", "Returning 'null' as requested mill, this should (and can) never happen!");
                return null;
        }
    }

    /**
     * @param currentMill     Die Walze, welche jetzt verschlüsselt.
     * @param wasInReturnMill Gibt an, ob der Buchstabe bereits "auf dem Rückweg ist".
     * @return Liefert das Alphabet als Char Array zurück, welches zuletzt zum Verschlüsseln verwendet wurde.
     * @brief Diese Methode findet heraus, mit welchem Alphabet die vorherige Verschlüsselung stattgefunden hat.
     */
    private char[] getPreviousAlphabet(EMill currentMill, boolean wasInReturnMill) {
        switch (currentMill) {
            case FIRST_MILL:
                if (wasInReturnMill) {
                    return getMill(EMill.SECOND_MILL).getAlphabet();
                }
                return EAlphabet.getAlphabet();
            case REVERSE_MILL:
                return getMill(EMill.THIRD_MILL).getAlphabet();
            case SECOND_MILL:
                if (wasInReturnMill) {
                    return getMill(EMill.THIRD_MILL).getAlphabet();
                }
                return getMill(EMill.FIRST_MILL).getAlphabet();
            case THIRD_MILL:
                if (wasInReturnMill) {
                    return getMill(EMill.REVERSE_MILL).getAlphabet();
                }
                return getMill(EMill.SECOND_MILL).getAlphabet();
            default:
                Log.getLogger().w(getClass().getName() + ".getPreviousAlphabet", "Returning default alphabet as previous alphabet, this should never happen!");
                return EAlphabet.getAlphabet();
        }
    }

    /**
     * @return Gibt den Schlüssel in Form eines speziell konstruierten Strings zurück.
     * @brief Liefert den momentan verwendeten Schlüssel zurück.
     */
    public String getKey() {
        return key;
    }

}

package de.Enigma.Algorithm;

import de.Enigma.Core.Log;
import de.Enigma.Util.Enums.EAlphabet;
import de.Enigma.Util.Enums.EMillAlphabet;
import de.Enigma.Util.Util;

/**
 * @author Oliver Seiler
 * @brief Walzen Klasse, welche eine Walze der Enigma darstellt
 * @details Realisiert als Objekt. Diesem wird sein "Alphabet" bei Erzeugung zugewiesen
 */
public class Mill {

    private char[] alphabet;
    private char markerChar;
    private boolean wasLatestFirstCharTheMark;


    /**
     * @param startPos - Der Buchstabe, welcher über den Schlüssel als Start Buchstabe angegeben wird
     * @param alphabet - Das Alphabet, welches die Walze nutzt
     * @brief Konstruktur der Walzenklasse, übergeben wird das zugehörige Alphabet und die Startstellung
     */
    public Mill(char startPos, EMillAlphabet alphabet) {
        this.alphabet = alphabet.getAlphabet();
        this.markerChar = new Character(alphabet.getTurnMarker());

        int rotations = 0;
        rotations = Util.getIndexOfCharInAlphabet(startPos, EAlphabet.getAlphabet());
        if (rotations > 0 && (startPos != this.alphabet[0] || startPos != '?') && this.markerChar != '?') {
            for (int i = 0; i < rotations; i++) {
                rotateMill();
            }
        }
        Log.getLogger().i(getClass().getName() +".Mill", "Objekt erstellt");
    }

    /**
     * @brief Diese Methode dreht die Walze um eine Position weiter
     * @details Methode, um die Walze um einen Buchstaben zu drehen
     * @details Schiebt das array nach links -> rotiert um einen Buchstaben
     */
    public void rotateMill() {
        char newLastChar = new Character(this.getAlphabet()[0]);
        for (int i = 1; i < this.getAlphabet().length; i++) {
            char currentChar = this.getAlphabet()[i];
            this.getAlphabet()[(i - 1)] = currentChar;
        }
        this.wasLatestFirstCharTheMark = newLastChar == this.markerChar;
        this.getAlphabet()[(this.getAlphabet().length - 1)] = newLastChar;
    }

    /**
     * @param c               - Der Buchstabe, welcher "verschlüssel", bzw. ersetzt werden soll
     * @param oldAlphabet     - Das Alphabet, mit welchem der Buchstabe zuvor verschlüsselt wurde
     * @param wasInReturnMill - Gibt an, ob die Walze von vorne oder von hitnen durchlaufen wurde
     * @return Gibt den verschlüsselten/ersetzten Buchstaben zurück. Wenn es hier Fehler gab, wird ein "?" zurückgegeben
     * @details Diese Methode ersetzt einen Buchstaben durch seinen Wert auf der Walze
     * @brief Methode, um einen Buchstaben zu ersetzen
     */
    public char encryptLetter(char c, char[] oldAlphabet, boolean wasInReturnMill) {
        //Log.getLogger().i(getClass().getName() + ".encryptLetter", "Encrypting letter " + c + " with mill alphabet..");

        int posOfC = Util.getIndexOfCharInAlphabet(c, wasInReturnMill ? this.alphabet.clone() : oldAlphabet);

        if (posOfC >= 0) {
            char cryptedChar = wasInReturnMill ? EAlphabet.getAlphabet().clone()[posOfC] : this.getAlphabet()[posOfC];
            Log.getLogger().i(getClass().getName() + ".encryptLetter", "encrypted letter " + c + " to " + cryptedChar);
            Log.getLogger().i("", "");
            return cryptedChar;
        }
        //DONE: log.w oder log.e
        Log.getLogger().e(getClass().getName() + ".encryptLetter", "Fatal: Failed to encrypt letter " + c + "! It seems that either the alphabet does not exist or the letter is not in the alphabet");
        return '?';
    }

    /**
     * @return true: Die Übertragskerbe wurde überschritten   false: die Kerbe wurde nicht überschritten
     * @brief Diese Methode überprüft, ob die Nachbarwalze auch rotiert werden soll, also ob die Übertragskerbe überschritten wurde
     * @details Methode, welche überprüft, ob die "Übertragskerbe" überschritten wurde
     */
    public boolean shouldRotateNeighborMill() {
        return this.wasLatestFirstCharTheMark && this.getAlphabet()[this.getAlphabet().length - 1] == this.markerChar;
    }

    /**
     * @return Liefert das "Alphabet" der Walze zurück -> das Alphabet, welches das normale Alphabet ersetzt
     * @brief Liefert das "Alphabet" dieser Walze zurück
     */
    public char[] getAlphabet() {
        return alphabet;
    }

}


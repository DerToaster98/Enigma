package de.Enigma.Algorithm;

import java.util.HashMap;

import de.Enigma.Core.Log;
import de.Enigma.Util.Enums.EAlphabet;
import de.Enigma.Util.Enums.EMill;

/**
 * 
 * @brief Klasse, welche essenziell für die Verschlüsselung ist
 * @details Enthält eine HashMap, welche das "Steckbrett" realisiert
 * @details Enthält die Instanzen der Walzen, welche benutzt werden
 * 
 * @author Oliver Seiler
 *
 */
public class EnigmaConfig {

	private String key = "?-???-???-?;?-?;?-?;?";
	private HashMap<Character,Character> letterChanger = new HashMap<Character, Character>();
	private Mill[] mills = new Mill[] {null,null,null};
	private Mill reverseMill = null;

	
	/**
	 * @brief Constructor, erzeugt die Instanzen der Walzen und das Steckbrett anhand des Schlüssels
	 */
	public EnigmaConfig(String key) {
		this.key = key;
		// TODO Key auf Richtigkeit prüfen
		// TODO Walzen erstellen und hier zuweisen
		// TODO "Steckbrett" befüllen
		// TODO Buchstaben verschlüsseln schreiben
	}

	/**
	 * @brief Methode, welche für die eigentliche "Veschlüsselung" verantworlich ist. Diese verschlüsselt einzelne Buchstaben.
	 * @param letter Der Buchstabe der verschlüsselt werden soll.
	 * @param mill Die Walze, welche verschlüsseln soll.
	 * @param wasInReturnMill Ob nach diesem Entschlüsselvorgang die Walzen rotiert und gedreht werden sollen.
	 * @return Der verschlüsselte Buchstabe
	 */
	public char encryptLetter(char letter, EMill mill, boolean wasInReturnMill){
		if(letter == ' ') {
			return letter;
		}
		char[] oldAlphabet = getPreviousAlphabet(mill, wasInReturnMill);
		Character character = new Character(letter);
		char toEncrypt = letter;
		if(!this.letterChanger.isEmpty() && this.letterChanger.containsKey(character)) {
			toEncrypt = this.letterChanger.get(character).charValue();
		}
		toEncrypt = getMill(mill).encryptLetter(toEncrypt, oldAlphabet);
		
		if(wasInReturnMill) {
			checkMills();
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
		if(first != null && first.shouldRotateNeighborMill()) {
			Mill second = getMill(EMill.SECOND_MILL);
			if(second != null) {
				second.rotateMill();
				if(second.shouldRotateNeighborMill()) {
					Mill third = getMill(EMill.THIRD_MILL);
					if(third != null) {
						third.rotateMill();
					}
				}
			}
		}
	}
	/**
	 * @brief Benutzt, um an die Instanzen der Walzen zu gelangen
	 * @param mill Beschreibt durch ein Enum, welche Walze gemeint ist
	 * @return Liefert die Instanz einer Walze zurück, welche durch 'mill' angesprochen wird -> FIRST_MILL: linke Walze, SECOND_MILL: mittlere Walze, etc.
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
			Log.getLogger().w(getClass().getName(), "getMill", "Returning 'null' as requested mill, this should (and can) never happen"); 
			return null;
		}
	}
	
	/**
	 * @brief Diese Methode findet heraus, mit welchem Alphabet die vorherige Verschlüsselung stattgefunden hat.
	 * @param currentMill Die Walze, welche jetzt verschlüsselt.
	 * @param wasInReturnMill Gibt an, ob der Buchstabe bereits "auf dem Rückweg ist".
	 * @return Liefert das Alphabet als Char Array zurück, welches zuletzt zum Verschlüsseln verwendet wurde.
	 */
	private char[] getPreviousAlphabet(EMill currentMill, boolean wasInReturnMill) {
		switch(currentMill) {
		case FIRST_MILL:
			if(wasInReturnMill) {
				return getMill(EMill.SECOND_MILL).getAlphabet();
			}
			return EAlphabet.getAlphabet();
		case REVERSE_MILL:
			return getMill(EMill.THIRD_MILL).getAlphabet();
		case SECOND_MILL:
			if(wasInReturnMill) {
				return getMill(EMill.THIRD_MILL).getAlphabet();
			}
			return getMill(EMill.FIRST_MILL).getAlphabet();
		case THIRD_MILL:
			if(wasInReturnMill) {
				return getMill(EMill.REVERSE_MILL).getAlphabet();
			}
			return getMill(EMill.SECOND_MILL).getAlphabet();
		default:
			Log.getLogger().w(getClass().getName(), "getPreviousAlphabet", "Returning default alphabet as previous alphabet, this should never happen!");
			return EAlphabet.getAlphabet();
		}
	}
	
	/**
	 * @brief Liefert den momentan verwendeten Schlüssel zurück.
	 * @return Gibt den Schlüssel in Form eines speziell konstruierten Strings zurück.
	 */
	public String getKey() {
		return key;
	}

}

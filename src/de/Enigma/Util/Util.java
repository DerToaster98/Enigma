package de.Enigma.Util;


/**
 * @brief Klasse, welche hauptsächlich Hilfsmethoden für die Stringmanipulation enthält
 * @author Alle
 *
 */
public class Util {

	public Util() {
		// TODO Auto-generated constructor stub
	}

	//wandelt String in char-Arrya um
	/**
	 * @brief Methode, welche Strings, in char-Arrays umwandeln kann
	 * @param message Der String, welcher in ein char-Array umgewandelt werden soll.
	 * @return Gibt das erzeugte char-Array zurück
	 */
	public static char[] createCharArray(String message){
		
		char[] c = message.toCharArray();
		
		return c;
	}

	/**
	 * @brief Liefert die Position eines Buchstaben in einem gegebenen Alphabet
	 * @details Liefert position eines buchstaben in einem alphabet zurück !!von 1 bis 26!! Wenn -1: nicht im Alphabet
	 * @param c Buchstabe, von welchem der Index gesucht werden soll
	 * @param alphabet Alphabet, in dem nach dem Buchstaben gesucht werden soll
	 * @return Liefert den Index des gescuhten Buchstabens im Alphabet zurück; -1, wenn das Alphabet den Buchstaben nicht enthält
	 */
	public static int getIndexOfCharInAlphabet(char c, char[] alphabet) {
		int index = 0;
		for(int i = 0; i < alphabet.length; i++) {
			index++;
			if(alphabet[i] == c) {
				return index;
			}
		}
		return -1;
	}

}

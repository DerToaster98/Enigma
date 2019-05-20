package de.Enigma.Util;

public class Util {

	public Util() {
		// TODO Auto-generated constructor stub
	}

	public static char[] createCharArray(String message){
		return null;
	}
	
	// Liefert position eines buchstaben in einem alphabet zurück !!von 1 bis 26!! Wenn -1: nicht im Alphabet
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

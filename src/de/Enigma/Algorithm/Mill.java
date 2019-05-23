package de.Enigma.Algorithm;

import de.Enigma.Util.Enums.EAlphabet;
import de.Enigma.Util.Enums.EMillAlphabet;
import de.Enigma.Util.Util;

/**
 * @brief Walzen Klasse, welche eine Walze der Enigma darstellt
 * @details Realisiert als Objekt. Diesem wird sein "Alphabet" bei Erzeugung zugewiesen
 *
 * @author Oliver Seiler
 */
public class Mill {

	private char[] alphabet;
	private char markerChar;
	private boolean wasLatestFirstCharTheMark;


	/**
	 * @param startPos Der Buchstabe, welcher über den Schlüssel als Start Buchstabe angegeben wird
	 * @param alphabet Das Alphabet, welches die Walze nutzt
	 */
	public Mill(char startPos, EMillAlphabet alphabet) {
		this.alphabet = alphabet.getAlphabet();
		this.markerChar = new Character(alphabet.getTurnMarker());
		
		int rotations = 0;
		rotations = Util.getIndexOfCharInAlphabet(startPos, EAlphabet.getAlphabet());
		if(rotations > 0) {
			for(int i = 0; i < rotations; i++) {
				rotateMill();
			}
		}
	}

	/**
	 * @brief Diese Methode dreht die Walze um eine Position weiter
	 * @details Methode, um die Walze um einen Buchstaben zu drehen
	 * @details Schiebt das array nach links -> rotiert um einen Buchstaben
	 */
	public void rotateMill(){
		char newLastChar = new Character(this.getAlphabet()[(this.getAlphabet().length -1)]);
		for(int i = 1; i < this.getAlphabet().length; i++) {
			char currentChar = this.getAlphabet()[i];
			this.getAlphabet()[(i-1)] = currentChar;
		}
		this.wasLatestFirstCharTheMark = false;
		if(newLastChar == this.markerChar) {
			this.wasLatestFirstCharTheMark = true;
		}
		this.getAlphabet()[(this.getAlphabet().length -1)] = newLastChar;
	}
	
	/**
	 * @brief Diese Methode ersetzt einen Buchstaben durch seinen Wert auf der Walze.
	 * @details Methode, um einen Buchstaben zu ersetzen
	 * @param c der Buchstabe, welcher "verschlüssel", bzw. ersetzt werden soll
	 * @param oldAlphabet Das Alphabet, mit welchem der Buchstabe zuvor verschlüsselt wurde
	 * @return Gibt den verschlüsselten/ersetzten Buchstaben zurück. Wenn es hier Fehler gab, wird ein "?" zurückgegeben.
	 */
	public char encryptLetter(char c, char[] oldAlphabet){
		if(c == ' ') {
			return c;
		}
		int posOfC = Util.getIndexOfCharInAlphabet(c, oldAlphabet);
		if(posOfC > 0) {
			return this.getAlphabet()[posOfC -1];
		}
		//TODO: log.w oder log.e
		return '?';
	}
	
	/**
	 * @brief Diese Methode überprüft, ob die Nachbarwalze auch rotiert werden soll, also ob die Übertragskerbe überschritten wurde
	 * @details Methode, welche überprüft, ob die "Übertragskerbe" überschritten wurde
	 * @return true: Die Übertragskerbe wurde überschritten   false: die Kerbe wurde nicht überschritten 
	 */
	public boolean shouldRotateNeighborMill() {
		if(this.wasLatestFirstCharTheMark && this.getAlphabet()[this.getAlphabet().length -1] == this.markerChar) {
			return true;
		}
		return false;
	}
	
	/**
	 * @brief Liefert das "Alphabet" dieser Walze zurück.
	 * @return Liefert das "Alphabet" der Walze zurück -> das Alphabet, welches das normale Alphabet ersetzt
	 */
	public char[] getAlphabet() {
		return alphabet;
	}

}


package de.Enigma.Algorithm;

import de.Enigma.Util.Enums.EAlphabet;
import de.Enigma.Util.Enums.EMillAlphabet;
import de.Enigma.Util.Util;

/**
 * (@brief) Walzen Klasse, welche eine Walze der Enigma darstellt
 * (@details) Realisiert als Objekt. Diesem wird sein "Alphabet" bei Erzeugung zugewiesen
 *
 * @author DerBoss
 */
public class Mill {

	private int index;
	private char[] alphabet;
	private char markerChar;
	private boolean wasLatestFirstCharTheMark;
	private int position;
	private int counter=0;


	/**
	 * @param indx UNUSED
	 * @param startPos Der Buchstabe, welcher über den Schlüssel als Start Buchstabe angegeben wird
	 * @param alphabet Das Alphabet, welches die Walze nutzt
	 */
	public Mill(int indx, char startPos, EMillAlphabet alphabet) {
		this.alphabet = alphabet.getAlphabet();
		this.markerChar = new Character(alphabet.getTurnMarker());
		index = indx;
		
		int rotations = 0;
		rotations = Util.getIndexOfCharInAlphabet(startPos, EAlphabet.getAlphabet());
		if(rotations > 0) {
			for(int i = 0; i < rotations; i++) {
				rotateMill();
			}
		}
	}

	/**
	 * (@details) Methode, um die Walze um einen Buchstaben zu drehen
	 * (@details) Schiebt das array nach links -> rotiert um einen Buchstaben
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
	 * (@details) Methode, um einen Buchstaben zu ersetzen
	 * @param c der Buchstabe, welcher "verschlüssel", bzw. ersetzt werden soll
	 * @return Gibt den verschlüsselten/ersetzten Buchstaben zurück
	 */
	public char encryptLetter(char c){
		return ' ';
	}
	
	/**
	 * (@details) Methode, welche überprüft, ob die "Übertragskerbe" überschritten wurde
	 * @return true: Die Übertragskerbe wurde überschritten   false: die Kerbe wurde nicht überschritten 
	 */
	public boolean shouldRotateNeighborMill() {
		if(this.wasLatestFirstCharTheMark && this.getAlphabet()[this.getAlphabet().length -1] == this.markerChar) {
			return true;
		}
		return false;
	}
	
	/**
	 * UNUSED
	 * @return true: Walze hat sich zum 26. mal gedreht    false: Walze hat sich weniger als 26x gedreht
	 */
	public boolean incrementCounter(){
		if(counter == 26){
			counter = 1;
			return true;
		}
		counter++;
		return false;
	}


	/**
	 * @return Liefert die Anzahl der Umdrehungen der Walze zurück
	 */
	public int getCounter() {
		return counter;

	}

	public int getIndex() {
		return index;
	}

	/**
	 * @return Liefert das "Alphabet" der Walze zurück -> das Alphabet, welches das normale Alphabet ersetzt
	 */
	public char[] getAlphabet() {
		return alphabet;
	}

}


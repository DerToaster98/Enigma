package de.Enigma.Algorithm;

import de.Enigma.Util.Enums.EAlphabet;
import de.Enigma.Util.Enums.EMillAlphabet;
import de.Enigma.Util.Util;

public class Mill {

	private int index;
	private char[] alphabet;
	private char markerChar;
	private boolean wasLatestFirstCharTheMark;
	private int position;
	private int counter=0;


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

	//Schiebt das array nach links -> rotiert um einen Buchstaben
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
	
	public char encryptLetter(char c){
		return ' ';
	}
	
	public boolean shouldRotateNeighborMill() {
		if(this.wasLatestFirstCharTheMark && this.getAlphabet()[this.getAlphabet().length -1] == this.markerChar) {
			return true;
		}
		return false;
	}
	
	public boolean incrementCounter(){
		if(counter == 26){
			counter = 1;
			return true;
		}
		counter++;
		return false;
	}


	public int getCounter() {
		return counter;

	}

	public int getIndex() {
		return index;
	}

	public char[] getAlphabet() {
		return alphabet;
	}

}


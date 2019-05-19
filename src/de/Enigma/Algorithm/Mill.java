package de.Enigma.Algorithm;

public class Mill {

	private int index;
	private char[] letters;
	private int position;
	private int counter=0;


	public Mill(int i, char startPos) {
		// TODO Auto-generated constructor stub
		index = i;
	}

	public void rotateMill(){

	}
	public char encryptLetter(char c){
		return ' ';
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
}


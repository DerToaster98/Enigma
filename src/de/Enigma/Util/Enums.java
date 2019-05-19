package de.Enigma.Util;

public class Enums {

    //enum EMode;
	public enum EMode {
		ENCRYPT,
		DECRYPT;
	}

    //enum EAlphabet;
	public enum EAlphabet {
		A('a',1),
		B('b',2),
		C('c',3),
		D('d',4),
		E('e',5),
		F('f',6),
		G('g',7),
		H('h',8),
		I('i',9),
		J('j',10),
		K('k',11),
		L('l',12),
		M('m',13),
		N('n',14),
		O('o',15),
		P('p',16),
		Q('q',17),
		R('r',18),
		S('s',19),
		T('t',20),
		U('u',21),
		V('v',22),
		W('w',23),
		X('x',24),
		Y('y',25),
		Z('z',26);

		private char character;
		private int index;
		
		EAlphabet(char c, int i) {
			this.index = i;
			this.character = c;
		}
		
		public int getIndex() {
			return this.index;
		}
		public char getAsChar() {
			return this.character;
		}
		
		public static EAlphabet getFromIndex(int i) {
			if(i >= 1 && i <=26) {
				for(EAlphabet ea : values()) {
					if(ea.getIndex() == i) {
						return ea;
					}
				}
			}
			return null;
		}
		public char[] getAlphabet() {
			char[] array = new char[] {26};
			
			for(int i = 0; i < 26; i++) {
				array[i] = getFromIndex(i+1).getAsChar();
			}
			
			return array;
		}
	}

	public enum EMillAlphabet {
		//Sources: https://de.wikipedia.org/wiki/Enigma-Walzen
		I (1, new char[] {'W','T','O','K','A','S','U','Y','V','R','B','X','J','H','Q','C','P','Z','E','F','M','D','I','N','L','G'}),
		II(2, new char[] {'G','J','L','P','U','B','S','W','E','M','C','T','Q','V','H','X','A','O','F','Z','D','R','K','Y','N','I'});
		//III
		//IV
		//V
		//UKW
		
		private char[] millPhabet;
		private int millID;
		
		EMillAlphabet(int millID, char[] millPhabet) {
			this.millID = millID;
			this.millPhabet = millPhabet;
		}
		
		public char[] getAlphabet() {
			return this.millPhabet.clone();
		}
		
		public int getMillID() {
			return this.millID;
		}
	}

}


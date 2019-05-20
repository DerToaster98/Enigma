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
		public static char[] getAlphabet() {
			char[] array = new char[] {26};
			
			for(int i = 0; i < 26; i++) {
				array[i] = getFromIndex(i+1).getAsChar();
			}
			
			return array;
		}
	}

	public enum EMillAlphabet {
		// Sources: https://de.wikipedia.org/wiki/Enigma-Walzen
		// Walzen 1-5 aus Modell Enigma I
		// UKW Walzen A-B aus Modell Enigma I
		//	    			    1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26
		I    ("1", new char[] {'E','K','M','F','L','G','D','Q','V','Z','N','T','O','W','Y','H','X','U','S','P','A','I','B','R','C','J'}, 'X'),
		II   ("2", new char[] {'A','J','D','K','S','I','R','U','X','B','L','H','W','T','M','C','Q','G','Z','N','P','Y','F','V','O','E'}, 'S'),
		III  ("3", new char[] {'B','D','F','H','J','L','C','P','R','T','X','V','Z','N','Y','E','I','W','G','A','K','M','U','S','Q','O'}, 'M'),
		IV   ("4", new char[] {'E','S','O','V','P','Z','J','A','Y','Q','U','I','R','H','X','L','N','F','T','G','K','D','C','M','W','B'}, 'Q'),
		V    ("5", new char[] {'V','Z','B','R','G','I','T','Y','U','P','S','D','N','H','L','X','A','W','M','J','Q','O','F','E','C','K'}, 'K'),
		// '?' ist nur für Umkehrwalzen, da diese keine Übertragskerbe haben
		UKW_A("A", new char[] {'E','J','M','Z','A','L','Y','X','V','B','W','F','C','R','Q','U','O','N','T','S','P','I','K','H','G','D'}, '?'),
		UKW_B("B", new char[] {'Y','R','U','H','Q','S','L','D','P','X','N','G','O','K','M','I','E','B','F','Z','C','W','V','J','A','T'}, '?'),
		UKW_C("C", new char[] {'F','V','P','J','I','A','O','Y','E','D','R','Z','X','W','G','C','T','K','U','Q','S','B','N','M','H','L'}, '?');
		
		private char[] millPhabet;
		private String millID;
		private char turnMarker;
		
		EMillAlphabet(String millID, char[] millPhabet, char turnMarker) {
			this.millID = millID;
			this.millPhabet = millPhabet;
			this.turnMarker = turnMarker;
		}
		
		public char getTurnMarker() {
			return this.turnMarker;
		}
		
		public char[] getAlphabet() {
			return this.millPhabet.clone();
		}
		
		public String getMillID() {
			return this.millID;
		}
	}

}


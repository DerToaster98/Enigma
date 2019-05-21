package de.Enigma.Util;

/**
 * 
 * @brief Klasse, welche verschiedene Enums enthält, welche verschiedene Aufgaben erfüllen
 * 
 * @author Oliver Seiler
 *
 */
public class Enums {

    //enum EMode;
	/**
	 * @brief Benutzt für die Übergabe des gewählten Modus der Enigma
	 * 
	 * @author Oliver Seiler
	 *
	 */
	public enum EMode {
		ENCRYPT,
		DECRYPT,;
	}

    //enum EAlphabet;
	/**
	 * @brief Ein Enum, welches das "normale" Alphabet enthält
	 * 
	 * @author Oliver Seiler
	 *
	 */
	public enum EAlphabet {
		A('A',1),
		B('B',2),
		C('C',3),
		D('D',4),
		E('E',5),
		F('F',6),
		G('G',7),
		H('H',8),
		I('I',9),
		J('J',10),
		K('K',11),
		L('L',12),
		M('M',13),
		N('N',14),
		O('O',15),
		P('P',16),
		Q('Q',17),
		R('R',18),
		S('S',19),
		T('T',20),
		U('U',21),
		V('V',22),
		W('W',23),
		X('X',24),
		Y('Y',25),
		Z('Z',26),;

		private char character;
		private int index;
		
		EAlphabet(char c, int i) {
			this.index = i;
			this.character = c;
		}
		
		/**
		 * 
		 * @return Liefert den Index dieses Eintrags im Alphabet zurück
		 */
		public int getIndex() {
			return this.index;
		}
		
		/**
		 * 
		 * @return Liefert den char dieses Eintrags
		 */
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
		
		/**
		 * 
		 * @return Liefert das normale Alphabet als array zurück
		 */
		public static char[] getAlphabet() {
			char[] array = new char[26];
			
			for(int i = 0; i < 26; i++) {
				array[i] = getFromIndex(i+1).getAsChar();
			}
			
			return array;
		}
	}
	
	/**
	 * @brief Dieses Enum wird dazu verwendet, um die Instanzen der Walzen der Enigma anzusprechen, bzw. zwischen ihnen zu differenzieren
	 * 
	 * @author Oliver Seiler
	 *
	 */
	public enum EMill {
		FIRST_MILL,
		SECOND_MILL,
		THIRD_MILL,
		REVERSE_MILL,;
	}

	/**
	 * @brief Dieses Enum enthält hauptsächlich die Alphabete für die Walzen 1-5 und die Umkehrwalzen A,B,C, welche in der Enigma I verbaut wurden
	 * 
	 * @author Oliver Seiler
	 *
	 */
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
		UKW_C("C", new char[] {'F','V','P','J','I','A','O','Y','E','D','R','Z','X','W','G','C','T','K','U','Q','S','B','N','M','H','L'}, '?'),;
		
		private char[] millPhabet;
		private String millID;
		private char turnMarker;
		
		EMillAlphabet(String millID, char[] millPhabet, char turnMarker) {
			this.millID = millID;
			this.millPhabet = millPhabet;
			this.turnMarker = turnMarker;
		}
		
		/**
		 * 
		 * @return Liefert den Buchstaben zurück, an welchem diese Walze ihre Übertragskerbe hat. Ist dieser '?', so ist diese Walze eine Umkehrwalze, welche keine Übertragskerbe besitzt
		 */
		public char getTurnMarker() {
			return this.turnMarker;
		}
		
		/**
		 * 
		 * @return Liefert das verwendete Alphabet dieser Walze als char Array zurück
		 */
		public char[] getAlphabet() {
			return this.millPhabet.clone();
		}
		
		/**
		 * 
		 * @return Liefert die 'ID' dieser Walze zurück. 1-5: Walzen 1-5; A,B,C: Umkehrwalzen A,B,C
		 */
		public String getMillID() {
			return this.millID;
		}
	}

}


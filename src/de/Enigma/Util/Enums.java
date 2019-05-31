package de.Enigma.Util;

/**
 * @author Oliver Seiler
 * @brief Klasse, welche verschiedene Enums enthält, welche verschiedene Aufgaben erfüllen
 */
public class Enums {

    //enum EMode;

    /**
     * @author Oliver Seiler
     * @brief Benutzt für die Übergabe des gewählten Modus der Enigma
     */
    public enum EMode {
        /**
         * @brief Modus zum Entschlüsseln
         */
        ENCRYPT,
        /**
         * @brief Modus zum Verschlüsseln
         */
        DECRYPT,
        ;
    }

    //enum EAlphabet;

    /**
     * @author Oliver Seiler
     * @brief Ein Enum, welches das "normale" Alphabet enthält
     */
    public enum EAlphabet {
        /**
         * @brief Repräsentiert den Buchstaben 'A'
         */
        A('A', 0),
        /**
         * @brief Repräsentiert den Buchstaben 'B'
         */
        B('B', 1),
        /**
         * @brief Repräsentiert den Buchstaben 'C'
         */
        C('C', 2),
        /**
         * @brief Repräsentiert den Buchstaben 'D'
         */
        D('D', 3),
        /**
         * @brief Repräsentiert den Buchstaben 'E'
         */
        E('E', 4),
        /**
         * @brief Repräsentiert den Buchstaben 'F'
         */
        F('F', 5),
        /**
         * @brief Repräsentiert den Buchstaben 'G'
         */
        G('G', 6),
        /**
         * @brief Repräsentiert den Buchstaben 'H'
         */
        H('H', 7),
        /**
         * @brief Repräsentiert den Buchstaben 'I'
         */
        I('I', 8),
        /**
         * @brief Repräsentiert den Buchstaben 'J'
         */
        J('J', 9),
        /**
         * @brief Repräsentiert den Buchstaben 'K'
         */
        K('K', 10),
        /**
         * @brief Repräsentiert den Buchstaben 'L'
         */
        L('L', 11),
        /**
         * @brief Repräsentiert den Buchstaben 'M'
         */
        M('M', 12),
        /**
         * @brief Repräsentiert den Buchstaben 'N'
         */
        N('N', 13),
        /**
         * @brief Repräsentiert den Buchstaben 'O'
         */
        O('O', 14),
        /**
         * @brief Repräsentiert den Buchstaben 'P'
         */
        P('P', 15),
        /**
         * @brief Repräsentiert den Buchstaben 'Q'
         */
        Q('Q', 16),
        /**
         * @brief Repräsentiert den Buchstaben 'R'
         */
        R('R', 17),
        /**
         * @brief Repräsentiert den Buchstaben 'S'
         */
        S('S', 18),
        /**
         * @brief Repräsentiert den Buchstaben 'T'
         */
        T('T', 19),
        /**
         * @brief Repräsentiert den Buchstaben 'U'
         */
        U('U', 20),
        /**
         * @brief Repräsentiert den Buchstaben 'V'
         */
        V('V', 21),
        /**
         * @brief Repräsentiert den Buchstaben 'W'
         */
        W('W', 22),
        /**
         * @brief Repräsentiert den Buchstaben 'X'
         */
        X('X', 23),
        /**
         * @brief Repräsentiert den Buchstaben 'Y'
         */
        Y('Y', 24),
        /**
         * @brief Repräsentiert den Buchstaben 'Z'
         */
        Z('Z', 25),
        ;

        private char character;
        private int index;

        /**
         * @param c Der Buchstabe an sich
         * @param i Die Position dieses Buchstabens im Alphabet
         * @brief Eintrag des normalen Alphabets, enthält den 'char' Wert und die Position des Eintrags im Alphabet
         */
        EAlphabet(char c, int i) {
            this.index = i;
            this.character = c;
        }

        /**
         * @return Liefert den Index dieses Eintrags im Alphabet zurück
         * @brief Liefert den Index dieses Eintrags im Alphabet zurück
         */
        public int getIndex() {
            return this.index;
        }

        /**
         * @return Liefert den char dieses Eintrags
         * @brief Liefert den char dieses Eintrags
         */
        public char getAsChar() {
            return this.character;
        }

        /**
         * @param i Repräsentiert die Position (0-25) im Alphabet
         * @return Gibt den Buchstaben an Position i im normalen Alphabet aus
         * @brief Liefert den passenden Eintrag für die gewünschte Position im Alphabet
         */
        public static EAlphabet getFromIndex(int i) {
            if (i >= 0 && i < 26) {
                for (EAlphabet ea : values()) {
                    if (ea.getIndex() == i) {
                        return ea;
                    }
                }
            }
            return null;
        }

        /**
         * @return Liefert das normale Alphabet als array zurück
         * @brief Liefert das normale Alphabet zurück
         */
        public static char[] getAlphabet() {
            char[] array = new char[26];

            for (int i = 0; i < 26; i++) {
                array[i] = getFromIndex(i).getAsChar();
            }

            return array;
        }
    }

    /**
     * @author Oliver Seiler
     * @brief Dieses Enum wird dazu verwendet, um die Instanzen der Walzen der Enigma anzusprechen, bzw. zwischen ihnen zu differenzieren
     */
    public enum EMill {
        /**
         * @brief Marker für die erste Walze (von links)
         */
        FIRST_MILL,
        /**
         * @brief Marker für die zweite Walze (von links)
         */
        SECOND_MILL,
        /**
         * @brief Marker für die dritte Walze (von links)
         */
        THIRD_MILL,
        /**
         * @brief Marker für die Umkehrwalze (von links)
         */
        REVERSE_MILL,
        ;
    }

    /**
     * @author Oliver Seiler
     * @brief Dieses Enum enthält hauptsächlich die Alphabete für die Walzen 1-5 und die Umkehrwalzen A,B,C, welche in der Enigma I verbaut wurden
     */
    public enum EMillAlphabet {
        // Sources: https://de.wikipedia.org/wiki/Enigma-Walzen
        // Walzen 1-5 aus Modell Enigma I
        // UKW Walzen A-B aus Modell Enigma I
        //	    			    1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26
        //
        /**
         * @brief Repräsentiert die Walze I der Enigma I
         */
    	 //	    		   1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26
        I("1", new char[]{'E', 'K', 'M', 'F', 'L', 'G', 'D', 'Q', 'V', 'Z', 'N', 'T', 'O', 'W', 'Y', 'H', 'X', 'U', 'S', 'P', 'A', 'I', 'B', 'R', 'C', 'J'}, 'X'),
        /**
         * @brief Repräsentiert die Walze II der Enigma I
         */
        //	    			1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26
        II("2", new char[]{'A', 'J', 'D', 'K', 'S', 'I', 'R', 'U', 'X', 'B', 'L', 'H', 'W', 'T', 'M', 'C', 'Q', 'G', 'Z', 'N', 'P', 'Y', 'F', 'V', 'O', 'E'}, 'S'),
        /**
         * @brief Repräsentiert die Walze III der Enigma I
         */
        //	    			 1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26
        III("3", new char[]{'B', 'D', 'F', 'H', 'J', 'L', 'C', 'P', 'R', 'T', 'X', 'V', 'Z', 'N', 'Y', 'E', 'I', 'W', 'G', 'A', 'K', 'M', 'U', 'S', 'Q', 'O'}, 'M'),
        /**
         * @brief Repräsentiert die Walze IV der Enigma I
         */
        IV("4", new char[]{'E', 'S', 'O', 'V', 'P', 'Z', 'J', 'A', 'Y', 'Q', 'U', 'I', 'R', 'H', 'X', 'L', 'N', 'F', 'T', 'G', 'K', 'D', 'C', 'M', 'W', 'B'}, 'Q'),
        /**
         * @brief Repräsentiert die Walze V der Enigma I
         */
        V("5", new char[]{'V', 'Z', 'B', 'R', 'G', 'I', 'T', 'Y', 'U', 'P', 'S', 'D', 'N', 'H', 'L', 'X', 'A', 'W', 'M', 'J', 'Q', 'O', 'F', 'E', 'C', 'K'}, 'K'),
        // '?' ist nur für Umkehrwalzen, da diese keine Übertragskerbe haben
        /**
         * @brief Repräsentiert die Umkehrwalze A der Enigma I
         */
        //                     1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26
        UKW_A("A", new char[]{'E', 'J', 'M', 'Z', 'A', 'L', 'Y', 'X', 'V', 'B', 'W', 'F', 'C', 'R', 'Q', 'U', 'O', 'N', 'T', 'S', 'P', 'I', 'K', 'H', 'G', 'D'}, '?'),
        /**
         * @brief Repräsentiert die Umkehrwalze B der Enigma I
         */
        UKW_B("B", new char[]{'Y', 'R', 'U', 'H', 'Q', 'S', 'L', 'D', 'P', 'X', 'N', 'G', 'O', 'K', 'M', 'I', 'E', 'B', 'F', 'Z', 'C', 'W', 'V', 'J', 'A', 'T'}, '?'),
        /**
         * @brief Repräsentiert die Umkehrwalze C der Enigma I
         */
        UKW_C("C", new char[]{'F', 'V', 'P', 'J', 'I', 'A', 'O', 'Y', 'E', 'D', 'R', 'Z', 'X', 'W', 'G', 'C', 'T', 'K', 'U', 'Q', 'S', 'B', 'N', 'M', 'H', 'L'}, '?'),
        ;

        private char[] millPhabet;
        private String millID;
        private char turnMarker;

        /**
         * @param millID     Repräsentiert den "Identifier" der Walze (z.B.: I,II,A,....)
         * @param millPhabet Repräsentiert das Alphabet, welches die Walze verwendet
         * @param turnMarker Repräsentiert die Stelle, an der die Übertragskerbe steht
         * @brief Repräsentiert die "Eigenschaften" einer Walze der Enigma I
         */
        EMillAlphabet(String millID, char[] millPhabet, char turnMarker) {
            this.millID = millID;
            this.millPhabet = millPhabet;
            this.turnMarker = turnMarker;
        }

        /**
         * @return Liefert den Buchstaben zurück, an welchem diese Walze ihre Übertragskerbe hat. Ist dieser '?', so ist diese Walze eine Umkehrwalze, welche keine Übertragskerbe besitzt
         * @brief Liefert den Buchstaben zurück, an welchem für diese Walze die Übertragskerbe steht.
         */
        public char getTurnMarker() {
            return this.turnMarker;
        }

        /**
         * @return Liefert das verwendete Alphabet dieser Walze als char Array zurück
         * @brief Liefert Alphabet der Walze zurück
         */
        public char[] getAlphabet() {
            return this.millPhabet.clone();
        }

        /**
         * @return Liefert die 'ID' dieser Walze zurück. 1-5: Walzen 1-5; A,B,C: Umkehrwalzen A,B,C
         * @brief Liefert den Bezeichner dieser Walze zurück
         */
        public String getMillID() {
            return this.millID;
        }

        /**
         * @param id Die ID der Walze
         * @return Liefert die zur ID gehörende Walzeneinstellung zurück
         * @brief Methode, um Walzeneinstellung aus ihrer ID zu bekommen
         */
        public static EMillAlphabet getByID(String id) {
            switch (id.toUpperCase()) {
                case "1":
                    return EMillAlphabet.I;
                case "2":
                    return EMillAlphabet.II;
                case "3":
                    return EMillAlphabet.III;
                case "4":
                    return EMillAlphabet.IV;
                case "5":
                    return EMillAlphabet.V;
                case "A": case "a":
                    return EMillAlphabet.UKW_A;
                case "B": case "b":
                    return EMillAlphabet.UKW_B;
                case "C": case "c":
                    return EMillAlphabet.UKW_C;
                default:
                    return null;
            }
        }
    }
    
    public enum EExtraCharacters {
    	COMMA(','),
    	SEMICOLON(';'),
    	FULL_STOP('.'),
    	AE('Ä'),
    	OE('Ö'),
    	UE('Ü'),
    	MINUS('-'),
    	PLUS('+'),
    	TAG('#'),
    	SPACE(' '),
    	EXCLAMATION_MARK('!'),
    	QUESTION_MARK('?'),
    	DIVIDE(':'),;
    	
    	private char assignedChar;
    	
    	EExtraCharacters(char character) {
    		this.assignedChar = character;
		}
    	
    	public char getAssignedChar() {
    		return this.assignedChar;
    	}
    	
    	public static boolean isExtraCharacter(char c) {
    		for(EExtraCharacters chara : values()) {
    			if(chara.getAssignedChar() == c) {
    				return true;
    			}
    		}
    		return false;
    	}
    }

}

 
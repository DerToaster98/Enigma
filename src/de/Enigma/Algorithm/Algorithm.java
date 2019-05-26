package de.Enigma.Algorithm;


import de.Enigma.Util.Enums.EMill;
import de.Enigma.Util.Enums.EAlphabet;

/**
 * @brief Die Klasse Algorithm enthält die Ver/Entschlüsselungs Methode für Encrytor und Decryptor,
 * sowie eine checkKey Methode.
 * 
 * @details Ein Key muss folgendermaßen aufgebaut sein:
 * U-AAA-XXX-X1;Y1-X2;Y2-...-X10;Y10 
 * U = wechlche Umkehrwalze verwendet wird, A B oder C 
 * 
 * AAA = welche Walze an welcher Position steht z.B 3 2 1 
 * Es gibt fünf Walzen jede Walze kann nur einmal verwendet werden
 * 
 * XXX= Walzenstellung z.B TCK, es müssen drei Buchstaben sein
 * 
 * X1;Y1 = Buchstabe X1 ist im Steckbrett mit Y2 verbunden z.B E;G
 * Die Buchstaben die mitteinander vertauscht werden müssen mit einem ";" getrennt werden
 * Es können maximal zehn Buchstabenpaare  vertauscht werden.
 * 
 * Beispiel: C-312-RQV-R;Q-A;S-T;L-C;N
 * Umkehrwalze C
 * erste Walze (Walze 3) auf R
 * zweite Walze (Walze 1) auf Q
 * dritte Walze (Walze 2) auf V
 * R mit Q verbunden
 * A mit S verbunden
 * T mit L verbunden
 * C mit N verbunden
 *
 *
 * Enthält Basis Algorithmus für Ver/Entschlüsselung
 * Außerdem ist eine Methode enthalten, welche überprüft, ob ein Schlüssel korrekt ist
 * Dies überprüft die "Steckbrett Sektion" des Schlüssels auf Duplikate
 * 
 * @author Lisa Binkert
 */
public abstract class Algorithm {
	private EnigmaConfig conf;
	private String [] metaData;
	private String ukw;
	private String [] walzen;
	private String [] position;
	private String []buchstabenArray;
	private int buchstabenCounter;
	private char[] plugboard = new char[40];
	private int counter = 0 ;
	
	private 
	
	

	public Algorithm() {
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @brief Ver-/Entschlüssel einen Buchstaben
	 * @param letter
	 * @return
	 */
	protected char encrypt(char letter){
		
		//erste Walze -> zweite Walze -> dritte Walze -> Umkehrwalze
		for (EMill mill : EMill.values()) {
			letter = conf.encryptLetter(letter, mill, false);
		}
		for (int i = 2; i >0; i--) {
			letter = conf.encryptLetter(letter, EMill.values()[i], true);
		}
		
		//Buchstabe zurückgeben
		return letter;
	}


	
	/**
	 * @brief Überprüft ob der Schlüssel korrekt eingegeben wurde
	 * @param key
	 * @return
	 */
public boolean checkKey(String key){
		
		String [] parts = key.split("-");
		if(checkUKW(parts[0])) {
			ukw = parts[0];
			if(checkMills(parts[1])) {
				if(checkPosition(parts[2])) {
	 				
					for (int i = 3; i < parts.length;i++) {
						
						if(checkLetter(parts[i])== false){
							
							return false;
						}
						
						
					}
					return true;
				}
			}

		}
		return false;

	}

private boolean checkLetter(String txt) {
	char[]c = txt.toCharArray();
	if(c.length==3) {
		System.out.println(c.length);
		if(notInPlugboard(c[0])&& inAlphabet(c[0],true)) {
			buchstabenArray[buchstabenCounter] = String.valueOf(c[0]);
			buchstabenCounter++;
			if(c[1]== ';') {
				buchstabenArray[buchstabenCounter] = "&";
				buchstabenCounter++;
				if(notInPlugboard(c[2]) &&inAlphabet(c[2],true)) {
				buchstabenArray[buchstabenCounter] = String.valueOf(c[2]);
				buchstabenCounter++;
				buchstabenArray[buchstabenCounter] = ",  ";
				buchstabenCounter++;
					return true;
				}
			}
		}
	}
	
	return false;
}


private boolean notInPlugboard(char c) {
	for (int i = 0; i < plugboard.length; i++) {
		if(c == plugboard[i]) {
			return false;
		  }
	}
	return true;
}

private boolean inAlphabet(char c, boolean plugboardBool) {
	for (EAlphabet alphabet : EAlphabet.values()) {
		char lowerAlphabet = (char)(alphabet.getAsChar() + 32);
		if(c==alphabet.getAsChar()){
			if(plugboardBool) {
				plugboard[counter]= c;
				counter++;
				plugboard[counter]= (char)(c+32);
				counter++;
			}
		}
		if(c== lowerAlphabet) {	
			if(plugboardBool) {
				plugboard[counter]= c;
				counter++;
				plugboard[counter]= (char)(c-32);
				counter++;
			}
			return true;
		}
		
	}
	return false;
}	
private  boolean checkPosition(String txt) {
	
	char[] c = txt.toCharArray();
	if(c.length==3) {
		if(inAlphabet(c[0],false)&&inAlphabet(c[1],false)&& inAlphabet(c[2],false)) {
			for (int i = 0; i < c.length; i++) {
				position[i] = String.valueOf(c[i]);
			}
			
			return true;
		}
	}
	
	return false;
}

private boolean checkMills(String txt) {
	
	char[] mill = txt.toCharArray();
	if(mill.length==3) {
	for (int i = 1; i <=5; i++) {
		char x = (char)(i+'0');
		if(mill[0] == x ) {
			for (int j = 1; j <= 5; j++) {
				char y = (char) (j+'0');
				if(mill[1]== y && mill[1]!=x) {
					for (int k = 1 ; k <= 5; k++) {
						char z = (char)(k+'0');
						if(mill[2]== z && mill[2]!=y && mill[2]!= x) {
							for (int l = 0; l < mill.length; l++) {
								walzen[i] = String.valueOf(mill[i]);
							}
						return true;
					}
			}
		}
	}
	}
}
	}
	return false;
}


private boolean checkUKW(String u) {
	if(u.equals("A")| u.equals("B")| u.equals("C") ) {
		return true;
	}
	return false;
}	
	/**
	 * @brief Übergibt metaData an FileHandler()
	 */

	protected  void createMetaData() {
		metaData[0]= "Typ: "+getClass().getName();
		metaData[1]= "Umkehrwalze: "+ getUmkehrwalze();
		metaData[2]= "1. Walze: "+ getWalze(0)+ " Startposition: "+ getPosition(0);
		metaData[3]= "2. Walze: "+ getWalze(1)+ " Startposition: "+ getPosition(1);
		metaData[4]= "3. Walze: "+ getWalze(2)+ " Startposition: "+ getPosition(2);
		metaData[5]= "Vertauschte Buchstaben:" +getBuchstaben();	
		
	}

	private String getBuchstaben() {
		String buchstaben = " ";
		for (int i = 0; i < buchstabenArray.length; i++) {
			buchstaben = buchstaben+ " "+ buchstabenArray[i];
		}
		return buchstaben;
	}

	private String getPosition(int i) {
		// TODO Auto-generated method stub
		return position[i];
	}

	private String getWalze(int i) {
		return walzen[i];
	}

	private String getUmkehrwalze() {
		// TODO Auto-generated method stub
		return ukw;
	}
}

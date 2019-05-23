package de.Enigma.Algorithm;

import de.Enigma.Util.Enums.EMill;

public abstract class Algorithm {
	private EnigmaConfig conf;

	/*
	 * Überprüfung ob Schlüssel korrekt ist 
	 * U-AAA-XXX-X1;Y1-X2;Y2-...-X10;Y10 max zehn Buchstaben
	 * U = wechlche Umkehrwalze verwendet wird, A B oder C 
	 * AAA = welche Walze an welcher Position steht z.B 3 2 1 
	 * XXX= Walzenstellung z.B 07 23 04 aber als Buchstabe
	 * X1;Y1 = Buchstabe X1 ist im Steckbrett mit Y2 verbunden z.B E;G
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
	 */
	
	public Algorithm() {
		// TODO Auto-generated constructor stub
		
	}

	protected char encrypt(char letter){
		
		//linke Walze
		letter = conf.encryptLetter(letter, EMill.FIRST_MILL, false);
		
		//mittlere Walze
		letter = conf.encryptLetter(letter, EMill.SECOND_MILL, false);
		
		//rechte Walze
		letter = conf.encryptLetter(letter, EMill.THIRD_MILL, false);
		
		//Umkehrwalze
		letter = conf.encryptLetter(letter, EMill.REVERSE_MILL, false);
		
		//rechte Walze
		letter = conf.encryptLetter(letter, EMill.THIRD_MILL, true);
		
		//mittlere Walze
		letter = conf.encryptLetter(letter, EMill.SECOND_MILL, true);
		
		//linke Walze
		letter = conf.encryptLetter(letter, EMill.FIRST_MILL, true);
		
		//Walzen prüfen und eventuell rotieren
		conf.checkMills();
		
		//Buchstabe zurückgeben
		return letter;
	}

	protected boolean checkKey(String key){
		
		
		
		return false;
	}

	protected abstract void createMetaData();
}

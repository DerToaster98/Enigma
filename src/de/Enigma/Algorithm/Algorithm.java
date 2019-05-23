package de.Enigma.Algorithm;

import de.Enigma.Util.Enums.EMill;

/**
 * @brief Die Klasse Algorithm enthält die Ver/Entschlüsselungs Methode für Encrytor und Decryptor,
 * sowie eine checkKey Methode.
 * 
 * @detailed Ein Key muss folgendermaßen aufgebaut sein:
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
	


	
	public Algorithm() {
		// TODO Auto-generated constructor stub
		
	}

	protected char encrypt(char letter){
		
		//erste Walze -> zweite Walze -> dritte Walze -> Umkehrwalze
		for (EMill mill : EMill.values()) {
			letter = conf.encryptLetter(letter, mill, false);
		}
		for (int i = 2; i >0; i--) {
			letter = conf.encryptLetter(letter, EMill.values()[i], true);
		}
		
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

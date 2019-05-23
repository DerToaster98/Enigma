package de.Enigma.Algorithm;

import de.Enigma.Util.*;
import de.Enigma.Util.Enums.EMode;

public class AlgorithmController {

	private String text;
	private EnigmaConfig eConfig;
	private Encryptor encrypt;
	private Decryptor decrypt;
	//private Algorithm cryptor;
	private Util util;
	private Enums.EMode cryptMode;


	public AlgorithmController(String key, Enums.EMode mode ) {
		// TODO Auto-generated constructor stub
		cryptMode = mode;
		
		if(!encrypt.checkKey(key)) {
			//Schlüssel enthält fehler
			
			//Schlüssel neu generieren
			
			//message an Log.w
			
		}
		
	}

	public String crypt(String txt){
		
		//txt in char array umwandeln
		char[] c = util.createCharArray(txt);
		char letter;
		for (int i = 0; i < c.length; i++) {
			if(c[i]==' ') {
				//Leerzeichen an FileHandler
			}
			else {
				//Buchstabe wird ver oder entschlüsselt
				if(cryptMode.equals(EMode.ENCRYPT) ) {
					// Buchstabe mit Encryptor verschlüsseln
					letter = encrypt.encrypt(c[i]);
					
					// Buchstabe an FileHandler
				}
				else if (cryptMode.equals(EMode.DECRYPT)) {
					// Buchstabe mit Decryptor entschlüsseln
					letter = encrypt.encrypt(c[i]);
					// Buchstabe an FileHandler
					
				}
				
				
			}
		}
		
		return txt;
	}

}



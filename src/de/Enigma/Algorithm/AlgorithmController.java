package de.Enigma.Algorithm;

import de.Enigma.Core.Log;
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
	private FileHandler fileHandler;
	private Log logger;

	/**
	 * @brief Die Klasse steuert die Ver- und Entschlüsselung.
	 * @param key
	 * @param mode
	 */

	public AlgorithmController(String key, Enums.EMode mode ) {
		// TODO Auto-generated constructor stub
		cryptMode = mode;
		encrypt = new Encryptor();
		decrypt = new Decryptor();
		util = new Util();
		fileHandler = FileHandler.getFileHandler();
		logger = Log.getLogger();
		
		if(!encrypt.checkKey(key)) {
			//Schlüssel enthält fehler
			
			//Schlüssel neu generieren
			
			
			//message an Log.w
			logger.w("AlgorithmController", "checkKey()", "Schlüssel wurde falsch eingegeben- Schlüssel wird automatisch generiert");
			
		}
		
		
		eConfig = new EnigmaConfig(key);
	}
	/**
	 * @brief Ver- und Entschlüsselt den Text
	 * @param txt, Text der ver- oder entschlüsselt werden soll
	 * @return
	 */
	public String crypt(String txt){
		
		//txt in char array umwandeln
		char[] c = util.createCharArray(txt);
		char letter;
		//@Lisa: Warum keine for-in Schleife? Wäre etwas schicker meiner Meinung nach....
		for (int i = 0; i < c.length; i++) {
			if(c[i]==' '|| c[i]=='.') {
				//Leerzeichen an FileHandler
				fileHandler.appendChar(c[i]);
			}
			else {
				//Buchstabe wird ver oder entschlüsselt
				if(cryptMode.equals(EMode.ENCRYPT) ) {
					// Buchstabe mit Encryptor verschlüsseln
					letter = encrypt.encrypt(c[i]);
					// Buchstabe an FileHandler
					fileHandler.appendChar(c[i]);
				}
				else if (cryptMode.equals(EMode.DECRYPT)) {
					// Buchstabe mit Decryptor entschlüsseln
					letter = decrypt.encrypt(c[i]);
					// Buchstabe an FileHandler
					fileHandler.appendChar(c[i]);
					
				}
				
				
			}
		}
		logger.i("AlgorithmConroller", "crypt", "Text wurde ver/entschlüsselt");
		return txt;
	}

}



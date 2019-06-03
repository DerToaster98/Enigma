package de.Enigma.Algorithm;

import de.Enigma.Core.Log;
import de.Enigma.Core.Main;
import de.Enigma.Util.FileHandler;
import de.Enigma.Util.Util;

public class AlgorithmController {

    private String key;
    private EnigmaConfig eConfig;
    private Algorithm algorithm;
    private Main main;


    /**
     * 
     * @param key
     * @brief Die Klasse steuert die Ver- und Entschlüsselung.
     * @author Lisa Binkert
     */
    public AlgorithmController(Main m, String key) {
        // TODO Auto-generated constructor stub
        main = m;
        this.key = key;

        while (!Util.isKeyValid(key)) {
            //Schlüssel neu generieren
            key = Util.getNewRandomKey();
            //message an Log.w
            Log.getLogger().w(getClass().getName() + "checkKey", "Schlüssel wurde falsch eingegeben oder generiert -> neuer Schlüssel wird automatisch generiert!");

        }
        FileHandler.getFileHandler().setKey(key);
        eConfig = new EnigmaConfig(key);
        algorithm = new Algorithm(this);

    }

    /**
     * @param txt Text der ver- oder entschlüsselt werden soll
     * @return
     * @brief Ver- und Entschlüsselt den Text
     * @author Lisa Binkert
     */
    public void crypt(String txt) {
        //txt in char array umwandeln
        String cryptText = "";
        char[] c = Util.createCharArray(txt);
        char letter;
		
        FileHandler.getFileHandler().resetEncodedText();

        Log.getLogger().i(getClass().getName() + ".crypt", "Encrypting text " + txt);
        
        for (char value : c) {
            if (value == ' ' || value == '.' || value == ',' || value == '?' || value == '!') {
                //Nicht zu verschlüsselnde Zeichen an FileHandler
                FileHandler.getFileHandler().appendChar(value);
                cryptText = cryptText.concat(String.valueOf(value));
            } else {
                // Buchstabe mit verschlüsseln
            	Log.getLogger().i(getClass().getName() + ".crypt", "Encrypting letter " + value);
                letter = algorithm.encrypt(value);
                // Buchstabe an FileHandler
                FileHandler.getFileHandler().appendChar(letter);
                cryptText = cryptText.concat(String.valueOf(letter));
            }
        }
        System.out.println(cryptText);
        algorithm.createMetaData();
        if(main != null) {
        	main.onFinished();
            Log.getLogger().i(getClass().getName() + ".crypt", "Text wurde verschlüsselt.");
        }
    }
    
    public String getKey() {
        return key;
    }

    EnigmaConfig getEnigmaConfig() {
        return eConfig;
    }

    AlgorithmController getController() {
        return this;
    }

}



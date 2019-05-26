package de.Enigma.Algorithm;

import de.Enigma.Core.Log;
import de.Enigma.Util.Enums;
import de.Enigma.Util.Enums.EMode;
import de.Enigma.Util.FileHandler;
import de.Enigma.Util.Util;

public class AlgorithmController {

    private String text;
    private String key;
    private EnigmaConfig eConfig;
    private Algorithm algorithm;
    private Enums.EMode cryptMode;


    /**
     * @param key
     * @param mode
     * @brief Die Klasse steuert die Ver- und Entschlüsselung.
     */
    public AlgorithmController(String key, Enums.EMode mode) {
        // TODO Auto-generated constructor stub
        cryptMode = mode;
        this.key = key;
        

        while (!util.isKeyValid(key)) {
            //Schlüssel neu generieren
            key = Util.getNewRandomKey();
            //message an Log.w
            Log.getLogger().w(getClass().getName() + "checkKey", "Schlüssel wurde falsch eingegeben oder generiert -> neuer Schlüssel wird automatisch generiert!");

        }
        eConfig = new EnigmaConfig(key);
        algorithm = new Algorithm();
       
    }

    /**
     * @param txt, Text der ver- oder entschlüsselt werden soll
     * @return
     * @brief Ver- und Entschlüsselt den Text
     */
    public void crypt(String txt) {

        //txt in char array umwandeln
        char[] c = Util.createCharArray(txt);
        char letter;
        //@Lisa: Warum keine for-in Schleife? Wäre etwas schicker meiner Meinung nach....
        for (char value : c) {
            if (value == ' ' || value == '.') {
                //Leerzeichen an FileHandler
                FileHandler.getFileHandler().appendChar(value);
            } else {
            	
                    // Buchstabe mit Decryptor entschlüsseln
                    letter = algorithm.encrypt(value);
                    // Buchstabe an FileHandler
                    FileHandler.getFileHandler().appendChar(letter);

                }


            }
        
            Log.getLogger().i(getClass().getName() + ".crypt", "Text wurde verschlüsselt.");
            
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



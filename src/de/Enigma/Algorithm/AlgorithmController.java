package de.Enigma.Algorithm;

import de.Enigma.Core.Log;
import de.Enigma.Util.*;
import de.Enigma.Util.Enums.EMode;

public class AlgorithmController {

    private String text;
    private String key;
    private EnigmaConfig eConfig;
    private Encryptor encrypt;
    private Decryptor decrypt;
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
        encrypt = new Encryptor();
        decrypt = new Decryptor();

        while (!encrypt.isKeyValid(key)) {
            //Schlüssel neu generieren
            key = Util.getNewRandomKey();
            //message an Log.w
            Log.getLogger().w(getClass().getName() + "checkKey", "Schlüssel wurde falsch eingegeben oder generiert- neuer Schlüssel wird automatisch generiert");

        }
        eConfig = new EnigmaConfig(key);
    }

    /**
     * @param txt, Text der ver- oder entschlüsselt werden soll
     * @return
     * @brief Ver- und Entschlüsselt den Text
     */
    public String crypt(String txt) {

        //txt in char array umwandeln
        char[] c = Util.createCharArray(txt);
        char letter;
        //@Lisa: Warum keine for-in Schleife? Wäre etwas schicker meiner Meinung nach....
        for (char value : c) {
            if (value == ' ' || value == '.') {
                //Leerzeichen an FileHandler
                FileHandler.getFileHandler().appendChar(value);
            } else {
                //Buchstabe wird ver oder entschlüsselt
                if (cryptMode.equals(EMode.ENCRYPT)) {
                    // Buchstabe mit Encryptor verschlüsseln
                    letter = encrypt.encrypt(value);
                    // Buchstabe an FileHandler
                    FileHandler.getFileHandler().appendChar(letter);
                } else if (cryptMode.equals(EMode.DECRYPT)) {
                    // Buchstabe mit Decryptor entschlüsseln
                    letter = decrypt.encrypt(value);
                    // Buchstabe an FileHandler
                    FileHandler.getFileHandler().appendChar(letter);

                }


            }
        }
        if (cryptMode.equals(EMode.DECRYPT)) {
            Log.getLogger().i(getClass().getName() + "crypt", "Text wurde verschlüsselt");
        } else if (cryptMode.equals(EMode.ENCRYPT)) {
            Log.getLogger().i(getClass().getName() + "crypt", "Text wurde entschlüsselt");
        }
        return txt;
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



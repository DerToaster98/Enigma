package de.Enigma.Algorithm;

import de.Enigma.Core.Log;
import de.Enigma.Core.Main;
import de.Enigma.Util.FileHandler;
import de.Enigma.Util.Util;

/**
 * @author Lisa Binkert
 * @brief Die Klasse steuert die Ver- und Entschlüsselung.
 **/
public class AlgorithmController {

    private Algorithm algorithm;
    private Main main;

    /**
     * @param m   - Instanz der Main, wird verwendet um das Ende des Vorgangs bekannt zu geben
     * @param kii - Der Schlüssel, welcher übergeben wird um die Walzen und das Steckbrett zu konfigurieren
     * @brief Konstruktor des AlgorithmControllers, EnigmaConfig und Algorithm Instanz werden hier direkt mit erzeugt
     **/
    public AlgorithmController(Main m, String kii) {
        main = m;
        String key = kii;

        while (!Util.isKeyValid(key)) {
            //Schlüssel neu generieren
            key = Util.getNewRandomKey();
            //message an Log.w
            Log.getLogger().w(getClass().getName() + "checkKey", "Schlüssel wurde falsch eingegeben oder generiert -> neuer Schlüssel wird automatisch generiert");

        }
        FileHandler.getFileHandler().setKey(key);
        EnigmaConfig eConfig = new EnigmaConfig(key);
        algorithm = new Algorithm(eConfig);

    }

    /**
     * @param txt - Text der ver- oder entschlüsselt werden soll
     * @brief Ver- und Entschlüsselt den Text
     * @details Ver-/entschlüsselt den Text und übergibt die einzelnen Zeichen an den FileHandler
     */
    public void crypt(String txt) {
        //txt in char array umwandeln
        String cryptText = "";
        char[] c = Util.createCharArray(txt);
        char letter;

        FileHandler.getFileHandler().resetEncodedText();

        Log.getLogger().i(getClass().getName() + ".crypt", "Encrypting text " + txt);

        for (int i = 0; i < c.length; i++) {
            char value = c[i];

            main.updateProgressBar(i + 1);

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
        Log.getLogger().i(getClass().getName() + ".crypt", txt + " -> " + cryptText);
        algorithm.createMetaData();
        if (main != null) {
            main.onFinished();
            Log.getLogger().i(getClass().getName() + ".crypt", "Text wurde verschlüsselt");
        }
    }

}
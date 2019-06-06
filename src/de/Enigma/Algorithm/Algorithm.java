package de.Enigma.Algorithm;


import de.Enigma.Core.Log;
import de.Enigma.Util.Enums.EMill;
import de.Enigma.Util.FileHandler;

/**
 * @author Lisa Binkert
 * @brief Die Klasse Algorithm enthält die Ver/Entschlüsselungs Methode,
 */
public class Algorithm {
    private EnigmaConfig conf;
    private AlgorithmController controller;

    /**
     * @brief Konstruktor des Algorithmus
     * @param controller - Instanz des AlgorithmControllers, welche die Steuerung übernimmt
     */
    public Algorithm(AlgorithmController controller) {
        this.controller = controller;
        conf = controller.getEnigmaConfig();


    }

    /**
     * @param letter - Buchstabe, welcher verschlüsselt werden soll
     * @return Gibt den verschlüsselten Buchstaben zurück, nachdem er das Steckbrett und alle Walzen und danach wieder das Steckbrett durchlaufen hat
     * @brief Ver-/Entschlüssel einen Buchstaben
     */
    public char encrypt(char letter) {
        char origLetter = letter;
        Log.getLogger().i(getClass().getName() + ".encrypt", "Encrypting char " + origLetter + "..");
        letter = conf.encryptLetterWithPlugBoard(letter);
        //erste Walze -> zweite Walze -> dritte Walze -> Umkehrwalze
        // Funktioniert so!
        for (EMill mill : EMill.values()) {
            letter = conf.encryptLetter(letter, mill, false);
        }
        for (int i = 2; i >= 0; i--) {
            letter = conf.encryptLetter(letter, EMill.values()[i], true);
        }

        // Buchstabe durchläuft zuletzt noch einmal das steckbrett...
        letter = conf.encryptLetterWithPlugBoard(letter);

        //Dritte Walze drehen und danach die Stellung der Walzen überprüfen
        conf.getMill(EMill.THIRD_MILL).rotateMill();
        conf.checkMills();

        Log.getLogger().i(getClass().getName() + ".encrypt", "Encrypted char " + origLetter);
        Log.getLogger().i("", "");
        Log.getLogger().i(getClass().getName() + ".encrypt", origLetter + " -> " + letter);
        Log.getLogger().i("", "LINE");
        //Buchstabe zurückgeben
        return letter;
    }


    /**
     * @brief Erstellt die Metadaten des Vorgangs und übergibt diese an den FileHandler
     */
    public void createMetaData() {
        String key = controller.getKey();
        String[] parts = key.split("-");
        char[] walzen = parts[1].toCharArray();
        char[] position = parts[2].toCharArray();
        String buchstaben = " ";
        for (int i = 3; i < parts.length; i++) {
            buchstaben = buchstaben.concat(parts[i] + " ");
        }
        if (buchstaben.equals(" ")) buchstaben = " keine";

        FileHandler.getFileHandler().setMetaData(1, "Umkehrwalze: " + parts[0]);
        FileHandler.getFileHandler().setMetaData(2, "1. Walze: " + walzen[0] + " - Startposition: " + position[0]);
        FileHandler.getFileHandler().setMetaData(3, "2. Walze: " + walzen[1] + " - Startposition: " + position[1]);
        FileHandler.getFileHandler().setMetaData(4, "3. Walze: " + walzen[2] + " - Startposition: " + position[2]);
        FileHandler.getFileHandler().setMetaData(5, "Vertauschte Buchstaben:" + buchstaben);
    }


}

package de.Enigma.Algorithm;


import de.Enigma.Core.Log;
import de.Enigma.Util.Enums.EMill;

/**
 * @author Lisa Binkert
 * @brief Die Klasse Algorithm enthält die Ver/Entschlüsselungs Methode,
 *
 */
public class Algorithm {
    private EnigmaConfig conf;
    private AlgorithmController controller;
    @SuppressWarnings("unused")
	private String key;
    private String[] metaData = new String[6];


    public Algorithm(AlgorithmController controller) {
        // TODO Auto-generated constructor stub
        this.controller = controller;
        conf = controller.getEnigmaConfig();


    }

    /**
     * @param letter
     * @return
     * @brief Ver-/Entschlüssel einen Buchstaben
     */
    protected char encrypt(char letter) {
    	char origLetter = letter;
    	Log.getLogger().i(getClass().getName() +".encrypt", "Encrypting char " + origLetter + "...");
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
        
        Log.getLogger().i(getClass().getName() +".encrypt", "Encrypted char " + origLetter);
        Log.getLogger().i("", "");
        Log.getLogger().i(getClass().getName() + ".encrypt", origLetter + " -> " + letter);
        Log.getLogger().i("", "-------------------------------------------------------------------------------");
        //Buchstabe zurückgeben
        return letter;
    }


    /**
     * @brief Übergibt metaData an FileHandler()
     */
    protected void createMetaData() {
        String key = controller.getKey();
        String[] parts = key.split("-");
        char[] walzen = parts[1].toCharArray();
        char[] position = parts[2].toCharArray();
        String buchstaben = " ";
        for (int i = 3; i < parts.length; i++) {
            buchstaben = buchstaben.concat(parts[i]+" ");
        }

        metaData[1] = "Umkehrwalze: " + parts[0];
        metaData[2] = "1. Walze: " + walzen[0] + " Startposition: " + position[0];
        metaData[3] = "2. Walze: " + walzen[1] + " Startposition: " + position[1];
        metaData[4] = "3. Walze: " + walzen[2] + " Startposition: " + position[2];
        metaData[5] = "Vertauschte Buchstaben:" + buchstaben;
        //FileHandler.getFileHandler().setMetaData(metaData);
    }


}

package de.Enigma.Core;

import de.Enigma.Algorithm.AlgorithmController;
import de.Enigma.UI.GUI;
import de.Enigma.Util.FileHandler;

/**
 * @author Alle
 * @brief Dies ist die Hauptklasse und "Instanz" des Programms
 * @details Hier werden lediglich die Kern-Instanzen erzeugt und aufgerufen. Außerdem ist diese Klasse für die Kommunikation der einzelnen Kern-Instanzen untereinander essenziell.
 */
public class Main {

    private GUI window;
    private AlgorithmController controller;

    private Main() {
        Log.getLogger();
        initGUI();
    }

    /**
     * @param args Verschiedene (nicht verwendete) Argumente, welche dem Programm beim Start mitgegeben werden können
     * @brief "Startmethode" des Programms
     */
    public static void main(String[] args) {
        new Main();
    }

    private void initGUI() {
        window = new GUI(this);
    }

    /**
     * @param text    Der zu ver/entschlüsselnde Text
     * @param key     Der Schlüssel, mit dem gearbeitet wird
     * @param encrypt Ob nun ver- oder entschlüsselt wird
     * @brief Wird aufgerufen, wenn der Button, welcher die Ver/Entschlüsselung anstößt, geklickt wird
     */
    public void btnOkClicked(String text, String key, boolean encrypt) {
        FileHandler.getFileHandler().setKey(key);
        FileHandler.getFileHandler().setClearText(text);
        //TODO Doxygen und Initialisierung von dem ganzen ding
        //übergabeParameter einbinden
        // encrypt als boolean abfragen -> wenn true dann "new Encryptor", sonst "new Decryptor"?
        controller = new AlgorithmController(key);
        controller.crypt(text);
    }

    /**
     * @brief Wird aufgerufen, wenn der Button, welcher den laufen Ver/Entschlüsselungsvorgang abbricht, geklickt wird
     */
    public void btnCancelClicked() {

    }

    /**
     * @brief Methode, die die Routine nachdem der Ver- bzw. Entschlüsselungsprozess erfolgreich beendet wurde ausführt
     * @details Der Routine soll die Dateien erstellen lassen und die GUI benachrichtigen
     */
    public void onFinished() {
        FileHandler.getFileHandler().makeFiles();
        window.onFinished();
    }
}

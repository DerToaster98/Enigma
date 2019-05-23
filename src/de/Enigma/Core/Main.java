package de.Enigma.Core;

import de.Enigma.UI.GUI;
import de.Enigma.Util.FileHandler;

/**
 * @brief Dies ist die Hauptklasse und "Instanz" des Programms
 * @details Hier werden lediglich die Kern-Instanzen erzeugt und aufgerufen. Außerdem ist diese Klasse für die Kommunikation der einzelnen Kern-Instanzen untereinander essenziell.
 * @author Alle
 *
 */
public class Main {

    private GUI window;

    private Main() {

        Log.getLogger();
        initGUI();
    }

    /**
     * @brief "Startmethode" des Programms
     * @param args Verschiedene (nicht verwendete) Argumente, welche dem Programm beim Start mitgegeben werden können
     */
    public static void main(String[] args) {
        new Main();
    }

    private void initGUI() {
        GUI window = new GUI(this);
        window.show();
        this.window = window;
    }

    /**
     * @brief Wird aufgerufen, wenn der Button, welcher die Ver/Entschlüsselung anstößt, geklickt wird
     * @param text Der zu ver/entschlüsselnde Text
     * @param key Der Schlüssel, mit dem gearbeitet wird
     * @param encrypt Ob nun ver- oder entschlüsselt wird 
     */
    public void btnOkClicked(String text, String key, boolean encrypt) {
        //TODO Doxygen und Initialisierung von dem ganzen ding
        //übergabeParameter einbinden
        // encrypt als boolean abfragen -> wenn true dann "new Encryptor", sonst "new Decryptor"?
        System.out.println("BTN_OK Clicked");
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

    /**
     * @brief Methode, um auf die Instanz der GUI zugreifen zu können
     * @return Liefert die Instanz der GUI zurück
     */
	public GUI getWindow() {
		return window;
	}

}

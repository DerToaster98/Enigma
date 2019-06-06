package de.Enigma.Core;

import de.Enigma.Algorithm.AlgorithmController;
import de.Enigma.UI.GUI;
import de.Enigma.Util.FileHandler;
import de.Enigma.Util.Util;

/**
 * @author Alle
 * @brief Dies ist die Hauptklasse und "Instanz" des Programms
 * @details Hier werden lediglich die Kern-Instanzen erzeugt und aufgerufen. Außerdem ist diese Klasse für die Kommunikation der einzelnen Kern-Instanzen untereinander essenziell.
 */
public class Main {

    private GUI window;
    private long startingTime;

    /**
     * @brief Privater Main Konstruktor
     */
    private Main() {
        Log.getLogger();
        window = new GUI(this);
    }

    /**
     * @param args - Verschiedene (nicht verwendete) Argumente, welche dem Programm beim Start mitgegeben werden können
     * @brief "Startmethode" des Programms
     */
    public static void main(String[] args) {
        new Main();
    }

    /**
     * @param text          - Der zu ver/entschlüsselnde Text
     * @param key           - Der Schlüssel, mit dem gearbeitet wird
     * @param chbx_selected - Zeigt an, ob der Text wie bei der echten Enigma behandelt werden soll
     * @param decrypt - Gibt an, ob ver- oder entschlüsselt werden soll
     * @brief Wird aufgerufen, wenn der Button, welcher die Ver/Entschlüsselung anstößt, geklickt wird
     */
    public void btnOkClicked(String text, String key, boolean chbx_selected, boolean decrypt) {

        startingTime = System.currentTimeMillis();
        Log.getLogger().i(getClass().getName() + ".btnOkClicked", "Prozess gestartet um " + startingTime);

        if (chbx_selected && !decrypt) {
            text = Util.prepareStringForReligma(text);
        }
        FileHandler.getFileHandler().setKey(key);

        FileHandler.getFileHandler().setClearText(text);

        new AlgorithmController(this, key).crypt(text);

    }

    /**
     * @brief Methode, die die Routine nachdem der Ver- bzw. Entschlüsselungsprozess erfolgreich beendet wurde ausführt
     * @details Die Routine soll die Dateien erstellen lassen und die GUI benachrichtigen
     */
    public void onFinished() {
        long endTime = System.currentTimeMillis();
        Log.getLogger().i(getClass().getName() + ".btnOkClicked", "Prozess beendet um " + endTime);
        long processDuration = endTime - startingTime;
        FileHandler.getFileHandler().setMetaData(6, "Dauer: " + processDuration + "ms");
        Log.getLogger().i(getClass().getName() + ".btnOkClicked", "Prozessdauer: " + processDuration);

        FileHandler.getFileHandler().makeFiles();
        window.onFinished();
    }
}

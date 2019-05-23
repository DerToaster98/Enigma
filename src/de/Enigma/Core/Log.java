package de.Enigma.Core;

import de.Enigma.Util.FileHandler;

/**
 * @author Nikolai
 * @brief Logger Klasse die eine in C++ geschriebene .dll einbindet, um die Logs zu schreiben
 * @details Realisiert als Singleton -> nur eine Instanz pro Programminstanz.\n
 * Zugriff über getLogger() Methode von überall aus möglich
 */
public class Log {

    private static Log logger;

    /**
     * @brief Privater Konstruktor, zum Erzeugen eines Singelton Objektes
     * @details Der Konstruktor kann nur intern aufgerufen werden, somit kann nur die Methode getLogger() ein Objekt erzeugen.
     * Der Logger als Singelton Objekt zu erzeugen, ist logisch, da nur eine Instanz des Loggers existieren sollte, denn sonst kann es zu Fehlern und Überschneidungen kommen.
     */
    private Log() {
        FileHandler.getFileHandler().makeLogFile();
    }

    /**
     * @param className  - Name der Klasse von der der Aufruf erfolgt ist
     * @param methodName - Name der Methode von der der Aufruf erfolgt ist
     * @param info       - Info Nachricht, die vom Logger geloggt werden soll
     * @brief Methode zum Schreiben von Info-Logs
     * @details Info Logeintrag:
     * erstellt über die .dll einen Info-Log der über Vorgänge innerhalb der Anwendung informieren soll.\n
     * Darunter zählen:\n
     * - generelle Infos über die Programmroutinen, wie zum Beispiel:\n
     * - Routine gestartet\n
     * - Routine erfolgreich beendet\n
     * - Routine startet Subroutine\n
     * - ...\n
     * - Infos zu Programmabläufen\n
     * - ...\n
     * Der Aufruf erfolgt immer über die static getLogger() Methode: Log.getLogger().i("className", "methodName", "warning");
     */
    public void i(String className, String methodName, String info) {
        FileHandler.getFileHandler().forceWrite("LOG", className+" "+ methodName +" "+ info, "INFO: ");
    }

    /**
     * @param className  Name der Klasse von der der Aufruf erfolgt ist
     * @param methodName Name der Methode von der der Aufruf erfolgt ist
     * @param warning    Warning Nachricht, die vom Logger geloggt werden soll
     * @brief Methode zum Schreiben von Warning-Logs
     * @details Warning Logeintrag:
     * erstellt über die .dll einen Warning-Log der über potentiell gefährliche Vorgänge innerhalb der Anwendung informieren soll.\n
     * Darunter zählen:\n
     * - potenzielle Exceptions, die nicht durch einen try-catch gecatched werden können\n
     * - potenzielle breakpoints im Code die zu vernachlässigbaren Fehlern führen können\n
     * - potenzielle aber korrigierbare Fehler\n
     * - ...
     * \n
     * Der Aufruf erfolgt immer über die static getLogger() Methode: Log.getLogger().w("className", "methodName", "warning");
     */
    public void w(String className, String methodName, String warning) {
        FileHandler.getFileHandler().forceWrite("LOG", className+" "+ methodName +" "+ warning, "WARNING: ");
    }

    /**
     * @param className  Name der Klasse von der der Aufruf erfolgt ist
     * @param methodName Name der Methode von der der Aufruf erfolgt ist
     * @param error      Error Nachricht, die vom Logger geloggt werden soll
     * @brief Methode zum Schreiben von Error-Logs
     * @details Error Logeintrag:
     * erstellt über die .dll einen Error-Log der über kritische Fehler innerhalb der Anwendung informieren soll.\n
     * Darunter zählen:\n
     * - Exceptions, die durch einen try-catch geworfen werden\n
     * - kritische Fehler, die das System beeinträchtigen können\n
     * - ...\n
     * Der Aufruf erfolgt immer über die static getLogger() Methode: Log.getLogger().e("className", "methodName", "warning");
     */
    public void e(String className, String methodName, String error) {
        FileHandler.getFileHandler().forceWrite("LOG", className+" "+ methodName +" "+ error, "ERROR: ");
    }

    /**
     * @return gibt das logger Singelton Objekt zurück
     * @brief Methode zum Erstellen des Singelton Logger-Objektes
     * @details Erstellt ein Singleton Objekt, sobald die Instanz benötigt wird und gibt, falls das Objekt schon existiert das Objekt zurück.
     * Das stellt sicher, dass es nur einen Logger gibt und dieser von allen Objekten von überall aus genutzt werden kann.
     */
    public static Log getLogger() {
        if (logger == null) {
            logger = new Log();
        }
        return logger;
    }

}

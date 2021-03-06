package de.Enigma.Core;

import de.Enigma.Util.FileHandler;
import de.Enigma.Util.Util;

/**
 * @author Nikolai Klatt
 * @brief Logger Klasse, um die Logs zu schreiben
 * @details Realisiert als Singleton, das heißt nur eine Instanz pro Programminstanz.\n
 * Zugriff über getLogger() Methode von überall aus möglich.
 */
public class Log {

    //private Logger Instanz
    private static Log logger;
    private boolean loggingDiasbled = false;

    /**
     * @brief Privater Konstruktor, zum Erzeugen eines Singelton Objektes
     * @details Der Konstruktor kann nur intern aufgerufen werden, somit kann nur die Methode getLogger() ein Objekt erzeugen.
     * Der Logger als Singelton Objekt zu erzeugen, ist logisch, da nur eine Instanz des Loggers existieren sollte, denn sonst kann es zu Fehlern und Überschneidungen kommen.
     * Der Logger erzeugt auch gleich den FileHandler.
     */
    private Log() {
        FileHandler.getFileHandler();
    }

    //@formatter:off

    /**
     * @return gibt das logger Singelton Objekt zurück
     * @brief Methode zum Erstellen des Singelton Logger-Objektes
     * @details Erstellt ein Singleton Objekt, sobald die Instanz benötigt wird und gibt, falls das Objekt schon existiert das Objekt zurück.
     * Das stellt sicher, dass es nur einen Logger gibt und dieser von allen Objekten von überall aus genutzt werden kann.
     */
    public static Log getLogger() {
        if (logger == null) {
            logger = new Log();
            logger.i(Log.class.getName() + ".Logger", "Logger Instanz erzeugt");
        }
        return logger;
    }

    /**
     * @param class_Method_Name - Name der Klasse und Methode von der der Aufruf erfolgt ist
     * @param info              - Info Nachricht, die vom Logger geloggt werden soll
     * @brief Methode zum Schreiben von <b>Info-Logs</b>
     * @details Erstellt über die .dll einen Info-Log der über Vorgänge innerhalb der Anwendung informieren soll.\n
     * Darunter zählen:\n
     * - generelle Infos über die Programmroutinen, wie zum Beispiel:\n
     * - Routine gestartet\n
     * - Routine erfolgreich beendet\n
     * - Routine startet Subroutine\n
     * - ...\n
     * - Infos zu Programmabläufen\n
     * - ...\n
     * Der Aufruf erfolgt immer über die static getLogger() Methode: Log.getLogger().i(getClass().getName()+ ".methodName", "warning");
     */
    //@formatter:on
    public void i(String class_Method_Name, String info) {
        if (!loggingDiasbled) {
            checkLogFile();
            if (!class_Method_Name.equals("")) {
                String log = Util.resolveLogString(class_Method_Name, info, "INFO");
                FileHandler.getFileHandler().writeLog(log + ".");
            } else if (info.equals("BLANK_LINE")) FileHandler.getFileHandler().writeLog("");
            else if (info.equals("LINE"))
                FileHandler.getFileHandler().writeLog("--------------------------------------------------------------------------------------------------------------------------");
        }
    }

    /**
     * @param class_Method_Name - Name der Klasse und Methode von der der Aufruf erfolgt ist
     * @param warning           - Warning Nachricht, die vom Logger geloggt werden soll
     * @brief Methode zum Schreiben von <b>Warning-Logs</b>
     * @details Erstellt über die .dll einen Warning-Log der über potentiell gefährliche Vorgänge innerhalb der Anwendung informieren soll.\n
     * Darunter zählen:\n
     * - potenzielle Exceptions, die nicht durch einen try-catch gecatched werden können\n
     * - potenzielle breakpoints im Code die zu vernachlässigbaren Fehlern führen können\n
     * - potenzielle aber korrigierbare Fehler\n
     * - ...
     * \n
     * Der Aufruf erfolgt immer über die static getLogger() Methode: Log.getLogger().w(getClass().getName()+ ".methodName", "warning");
     */
    public void w(String class_Method_Name, String warning) {
        if (!loggingDiasbled) {
            checkLogFile();
            String log = Util.resolveLogString(class_Method_Name, warning, "WARNING");
            FileHandler.getFileHandler().writeLog(log + "!");
        }
    }

    /**
     * @param class_Method_Name - Name der Klasse und Methode von der der Aufruf erfolgt ist
     * @param error             - Error Nachricht, die vom Logger geloggt werden soll
     * @brief Methode zum Schreiben von <b>Error-Logs</b>
     * @details Erstellt über die .dll einen Error-Log der über kritische Fehler innerhalb der Anwendung informieren soll.\n
     * Darunter zählen:\n
     * - Exceptions, die durch einen try-catch geworfen werden\n
     * - kritische Fehler, die das System beeinträchtigen können\n
     * - ...\n
     * Der Aufruf erfolgt immer über die static getLogger() Methode: Log.getLogger().e(getClass().getName()+ ".methodName", "warning");
     */
    public void e(String class_Method_Name, String error) {
        if (!loggingDiasbled) {
            checkLogFile();
            String log = Util.resolveLogString(class_Method_Name, error, "ERROR");
            FileHandler.getFileHandler().writeLog(log + "!");
        }
    }

    /**
     * @brief Methode zum Erzeugen lassen der Logfile, sobald sie benötigt wird
     */
    private void checkLogFile() {
        if (!FileHandler.getFileHandler().logFileexists())
            FileHandler.getFileHandler().createNewCurrentDirectory();
        FileHandler.getFileHandler().makeLogFile();
    }

    /**
     * @param isLoggingDisabled - Zeigt, ob das Logging deaktiviert werden soll
     * @brief Methode, die das Logging deaktivieren kann
     */
    public void setLoggingDisabled(boolean isLoggingDisabled) {
        loggingDiasbled = isLoggingDisabled;
    }
}
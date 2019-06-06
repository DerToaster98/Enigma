package de.Enigma.Util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import de.Enigma.Core.Log;

import java.io.*;
import java.net.URLDecoder;

/**
 * @author Nikolai Klatt
 * @brief FileHandler, der die Funktionalitäten mit den Dateien inne hat
 * @details Realisiert als Singleton, das heißt nur eine Instanz pro Programminstanz.\n
 * Zugriff über getFileHandler() Methode von überall aus möglich.
 */
public class FileHandler {
    // private FileHandler Instanz
    private static FileHandler fileHandler;

    private final String HOME = createPath();
    private String currentHome;

    private String key;
    private String encodedText = "missingNo";
    private String clearText;
    private String[] metaData = new String[7];
    //metaData[0] -> Typ
    //metaData[1] -> UKW
    //metaData[2] -> 1.Walze
    //metaData[3] -> 2.Walze
    //metaData[4] -> 3.Walze
    //metaData[5] -> Steckbrett
    //metaData[6] -> Dauer

    private LogFile logFile;

    /**
     * @brief privater FileHandler Konstruktor
     */
    private FileHandler() {
    }

    /**
     * @brief Methode, zum Erstellen eines Verzeichnisses für den aktuellen Prozess
     */
    public void createNewCurrentDirectory() {
        //erstellt einen Neues Verzeichnis, das Datum und Zeit als Namen hat
        currentHome = HOME + "/[" + Util.getTimeString() + "]/";
        makeNewFolder(currentHome);
    }

    /**
     * @param letter - Verschlüsselter Buchstabe
     * @brief Methode zum Anhängen eines Buchstabens an den encodedText
     */
    public void appendChar(char letter) {
        encodedText = encodedText.concat(String.valueOf(letter));
    }

    /**
     * @param text - Log Nachricht
     * @brief Methode zum Schreiben eines Logs in die LogFile
     */
    public void writeLog(String text) {
        logFile.write(text);
    }

    /**
     * @return Stringrepräsentation des Verzeichnisses
     * @brief Methode zum Erstellen eines Verzeichnisses im Verzeichnis, von dem die Anwendung gestartet wurde.
     */
    private String createPath() {
        //Holt den String des Verzeichnisses, in welchem das Programm gestartet wurde
        String p = FileHandler.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        String path = null;
        try {
            path = URLDecoder.decode(p, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.getLogger().e(getClass().getName() + ".createPath",
                    "Decoding Path ran into a problem: UnsupportedEncodingException");
        }
        makeNewFolder(path + "/Enigma");

        return path + "/Enigma/";
    }

    /**
     * @param path - Pfad des Ordners, der erstellt werden soll
     * @brief Methode zum Erstellen eines Ordners
     */
    private void makeNewFolder(String path) {
        if (!(new File(path).exists())) {
            if (!(new File(path).mkdir()))
                Log.getLogger().w(getClass().getName() + ".makeNewFolder", "Verzeichnis " + path + " aufgrund eines Fehlers nicht angelegt");
            else
                Log.getLogger().i(getClass().getName() + ".makeNewFolder", "Verzeichnis " + path + " erfolgreich angelegt");
        }
    }

    /**
     * @brief Methode zum Erstellen der LogDatei
     */
    public void makeLogFile() {
        try {
            logFile = new LogFile(currentHome + "Logfile.log");
            if (logFile.createNewFile())
                Log.getLogger().i(getClass().getName() + ".makeLogFile", "LogFile wurde erfolgreich erzeugt");
        } catch (IOException e) {
            e.printStackTrace();
            Log.getLogger().e(getClass().getName() + ".makeLogFile", "Error während der Erzeugung der LogFile");
        }
    }

    /**
     * @brief Methode zum Erstellen der Text und Enigma Dateien
     */
    public void makeFiles() {
        try {
            EnigmaFile enigmaFile = new EnigmaFile(currentHome + "Enigma.enigma", metaData, key, clearText, encodedText);
            if (enigmaFile.createNewFile())
                Log.getLogger().i(getClass().getName() + ".makeFiles", "EngimaFile wurde erfolgreich erzeugt");
            TextFile textFile = new TextFile(currentHome + "Text.txt", metaData, key, clearText, encodedText);
            if (textFile.createNewFile())
                Log.getLogger().i(getClass().getName() + ".makeFiles", "TextFile wurde erfolgreich erzeugt");
        } catch (IOException e) {
            e.printStackTrace();
            Log.getLogger().e(getClass().getName() + ".makeFiles", "Error während der Erzeugung der Output Files");
        }

    }

    /**
     * @param f   - Datei, aus der gelesen werden soll
     * @param key - Schlüssel des Wertes, der gelesen werden soll
     * @return Aus der Datei gelesener String-Wert
     * @brief Methode zum Einlesen aus einer Datei
     * @details Aus der Datei wird mit Hilfe des JsonReaders ein JsonObjekt gelesen,
     * aus welchem dann die jeweiligen Values gelesen werden.
     */
    public String inputFromFile(File f, String key) {
        Gson gson = new Gson();
        JsonReader jr = null;
        try {
            jr = new JsonReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.getLogger().e(getClass().getName() + ".inputFromFile", "Fehler beim Lesen der .enigma Datei " + f.getAbsolutePath());
        }
        JsonObject jO = gson.fromJson(jr, JsonObject.class);

        return jO.get(key).getAsString();
    }

    /**
     * @param clearText - Klartext, der gesetzt werden soll
     * @brief Methode zum setzen des Klartexts
     */
    public void setClearText(String clearText) {
        this.clearText = clearText;
    }

    /**
     * @brief Methode zum Zurücksetzen des verschlüsselten Texts
     */
    public void resetEncodedText() {
        this.encodedText = "";
    }

    /**
     * @param index - Stelle der metaData
     * @param type  - Beschreibung der metaData
     * @brief Methode zum Setzen der MetaDaten
     */
    public void setMetaData(int index, String type) {
        metaData[index] = type;
    }

    /**
     * @param key - Key, der gesetzt werden soll
     * @brief Methode zum Setzen des Keys
     */
    public void setKey(String key) {
        this.key = key;
        Log.getLogger().i(Log.class.getName() + ".setKey", "Gesetzter Schlüssel: " + key);
    }

    /**
     * @return Verzeichnis
     * @brief Methode zum Zurückgeben des HOME Verzeichnisses
     */
    public String getHOME() {
        return HOME;
    }

    /**
     * @return Verzeichnis
     * @brief Methode zum Zurückgeben des currentHome Verzeichnisses
     */
    public String getCurrentHome() {
        return currentHome;
    }

    /**
     * @return Ob die LogFile existiert
     * @brief Methode zum Überprüfen, ob die LogFile existiert
     */
    public boolean logFileexists() {
        try {
            return logFile.exists();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @brief Methode zum zurückgeben des momentanen Keys
     * @return momentaner Key
     */
    public String getKey() {
        return this.key;
    }


    /**
     * @return gibt das fileHandler Singelton Objekt zurück
     * @brief Methode zum Erstellen des Singelton FileHandler-Objektes
     * @details Erstellt ein Singleton Objekt, sobald die Instanz benötigt wird und gibt, falls das Objekt schon existiert das Objekt zurück.
     * Das stellt sicher, dass es nur einen FileHandler gibt und dieser von allen Objekten von überall aus genutzt werden kann.
     */
    public static FileHandler getFileHandler() {
        if (fileHandler == null) {
            fileHandler = new FileHandler();
        }
        return fileHandler;
    }

}

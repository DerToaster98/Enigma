package de.Enigma.Util;

import de.Enigma.Core.Log;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class FileHandler {

    private static FileHandler fileHandler;

    private final String HOME = createPath();
    private String currentHome;

    private String key;
    private String encodedText = "missingNo";
    private String clearText;
    private String[] metaData = new String[6];
    //metaData[0] -> Typ


    private LogFile logFile;
    private TextFile textFile;
    private EnigmaFile enigmaFile;

    private FileHandler() {
    }

    public void createNewCurrentDirectory() {
        currentHome = HOME + "/[" + Util.getTimeString() + "]/";
        makeNewFolder(currentHome);
    }

    public void appendChar(char letter) {
        encodedText = encodedText.concat(String.valueOf(letter));
    }

    public void writeLog(String text, String key) {
        logFile.write(key, text);
    }

    private String createPath() {

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

    private void makeNewFolder(String path) {
        if (!(new File(path).exists())) {
            if (!(new File(path).mkdir()))
                Log.getLogger().w(getClass().getName() + ".makeNewFolder", "Verzeichnis " + path + " aufgrund eines Fehlers nicht angelegt!");
            else
                Log.getLogger().i(getClass().getName() + ".makeNewFolder", "Verzeichnis " + path + " erfolgreich angelegt!");
        }
    }

    public void makeLogFile() {
        try {
            logFile = new LogFile(currentHome + "Logfile.log");
            if (logFile.createNewFile())
                Log.getLogger().i(getClass().getName() + ".makeLogFile", "LogFile wurde erfolgreich erzeugt!");
        } catch (IOException e) {
            e.printStackTrace();
            Log.getLogger().e(getClass().getName() + ".makeLogFile", "Error während der Erzeugung der LogFile!");
        }
    }

    public void makeFiles() {
        try {
            enigmaFile = new EnigmaFile(currentHome + "Enigma.enigma", metaData, key, clearText, encodedText);
            if (enigmaFile.createNewFile())
                Log.getLogger().i(getClass().getName() + ".makeFiles", "EngimaFile wurde erfolgreich erzeugt!");
            textFile = new TextFile(currentHome + "Text.txt", metaData, key, clearText, encodedText);
            if (textFile.createNewFile())
                Log.getLogger().i(getClass().getName() + ".makeFiles", "TextFile wurde erfolgreich erzeugt!");
        } catch (IOException e) {
            e.printStackTrace();
            Log.getLogger().e(getClass().getName() + ".makeFiles", "Error während der Erzeugung der Output Files!");
        }

    }

    public void setClearText(String clearText) {
        this.clearText = clearText;
    }

    public void resetEncodedText() {
        this.encodedText = "";
    }

    public void setTypeMetaData(String type) {
        metaData[0] = type;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHOME() {
        return HOME;
    }

    public boolean logFileexists() {
        try {
            return logFile.exists();
        } catch (Exception e) {
            return false;
        }
    }

    public static FileHandler getFileHandler() {
        if (fileHandler == null) {
            fileHandler = new FileHandler();
        }
        return fileHandler;
    }

}

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

    private String toWrite;
    private String key;
    private String encodedText;
    private String clearText;
    private String metaData;

    private LogFile logFile;
    private TextFile textFile;
    private EnigmaFile enigmaFile;

    private FileHandler() {
        currentHome = HOME + "/[" + Util.getTimeString() + "]/";
        makeNewFolder(currentHome);
    }

    public void appendChar(char letter) {
        toWrite = toWrite.concat(String.valueOf(letter));
    }

   public void forceWrite(String File, String text, String key){
//        if (File.equals("LOG")){
            logFile.write(key, text);
        }
//        else if (File.equals("TEXT")){
//            textFile.write();
//        }
//        else
//            enigmaFile.write();
//    }

    private String createPath() {

        String p = FileHandler.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        String path = null;
        try {
            path = URLDecoder.decode(p, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.getLogger().e("FileHandler", "createPath",
                    "Decoding Path ran into a problem: UnsupportedEncodingException");
        }
        makeNewFolder(path + "/Enigma");

        return path + "/Enigma/";
    }

    private void makeNewFolder(String path) {
        if (!(new File(path).exists())) {
            if (!(new File(path).mkdir()))
                Log.getLogger().w("FileHandler", "makeNewFolder", "Verzeichnis " + path + " aufgrund eines Fehlers nicht angelegt!");
            else Log.getLogger().i("FileHandler", "makeNewFolder", "Verzeichnis " + path + " erfolgreich angelegt!");
        }
    }

    public void makeLogFile() {
        try {
            logFile = new LogFile(currentHome + "Logfile.log");
            if (logFile.createNewFile())
                Log.getLogger().i("FileHandler", "makeLogFile", "LogFile wurde erfolgreich erzeugt!");
        } catch (IOException e) {
            e.printStackTrace();
            Log.getLogger().e("FileHandler", "makeLogFile", "Error während der Erzeugung der LogFile!");
        }
    }

    public void makeFiles() {

    }

    public String getHOME() {
        return HOME;
    }

    public static FileHandler getFileHandler() {
        if (fileHandler == null) {
            fileHandler = new FileHandler();
        }
        return fileHandler;
    }
}

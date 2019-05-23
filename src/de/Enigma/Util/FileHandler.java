package de.Enigma.Util;

import de.Enigma.Core.Log;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class FileHandler {

    private final String HOME = createPath();

    private String toWrite;
    private String key;
    private String encodedText;
    private String clearText;
    private String metaData;

    private File logFile;
    private File outputFile;
    private File enigma;

    public void appendChar(char letter) {
        toWrite = toWrite.concat(String.valueOf(letter));
    }

    public void forceWrite(String text, String key) {

    }

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
        if (!(new File(path+"/Enigma").exists()))
        new File(path+"/Enigma").mkdir();

        return path;
    }

    public void createNewEnigma(){
        
    }

    public String getHOME() {
        return HOME;
    }
}

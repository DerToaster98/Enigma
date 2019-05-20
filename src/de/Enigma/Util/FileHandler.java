package de.Enigma.Util;

import de.Enigma.Core.Log;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class FileHandler {

    private final String PATH = createPath();

    private String toWrite;
    private File logFile;
    private File outputFile;
    private File enigma;

    public void appendChar(char letter){
        toWrite = toWrite.concat(String.valueOf(letter));
    }

    public void forceWrite(){

    }

    private String createPath() {

        String p = FileHandler.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        String path = null;
        try {
            path = URLDecoder.decode(p, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("FileHandler","createPath", "Decoding Path ran into a problem: UnsupportedEncodingException");
        }


        return path;
    }

    public String getPATH() {
        return PATH;
    }
}

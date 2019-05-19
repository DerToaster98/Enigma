package de.Enigma.Core;

import java.io.File;

public class FileHandler {

    private String toWrite;
    private File logFile;
    private File outputFile;
    private File enigma;

    public void appendChar(char letter){
        toWrite = toWrite.concat(String.valueOf(letter));
    }

    public void forceWrite(){

    }

}

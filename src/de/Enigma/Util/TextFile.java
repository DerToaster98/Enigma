package de.Enigma.Util;

import de.Enigma.Core.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class TextFile extends File {

    TextFile(String pathname, String[] metaData, String key, String clearText, String encodedText) {
        super(pathname);
        write(clearText);
    }

    public void write(String text) {

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this, true));
            writer.write(text);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.getLogger().e("TextFile", "write", "Error während des Schreibens in die Textdatei!");
        }
    }

}

package de.Enigma.Util;

import de.Enigma.Core.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class LogFile extends File {

    LogFile(String pathname) {
        super(pathname);
        writeImmediateMetaData();
    }

    private void writeImmediateMetaData() {

    }

    public void write(String key, String text) {

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this, true));
            writer.write(key + " " + text);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            Log.getLogger().e(getClass().getName() + ".write", "Error während des Schreibens in die Logdatei!");
        } finally {
            try {
                writer.close();
                Log.getLogger().w(getClass().getName() + ".write", "Statement writer.close() could throw NullPointerException!");
            } catch (IOException e) {
                e.printStackTrace();
                Log.getLogger().e(getClass().getName() + ".write", "Error beim Schließen des Writers!");
            }
        }
    }
}

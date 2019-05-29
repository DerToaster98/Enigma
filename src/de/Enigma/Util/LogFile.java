package de.Enigma.Util;

import de.Enigma.Core.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class LogFile extends File {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7409210531157611910L;

	LogFile(String pathname) {
        super(pathname);
        writeImmediateMetaData();
    }

    private void writeImmediateMetaData() {

    }

    public void write(String key, String text) {

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this, true));
            writer.write(key + " " + text);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.getLogger().e(getClass().getName() + ".write", "Error während des Schreibens in die Logdatei!");

        }
    }
}

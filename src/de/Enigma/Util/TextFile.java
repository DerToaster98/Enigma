package de.Enigma.Util;

import de.Enigma.Core.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class TextFile extends File {


    /**
	 * 
	 */
	private static final long serialVersionUID = 2386034996542721472L;

	TextFile(String pathname, String[] metaData, String key, String clearText, String encodedText) {
        super(pathname);


        write("Key:", key);
        write("Klartext: ", clearText);
        if (metaData[0].equals("Verschlüsselung"))
        write("Verschlüsselter Text: ", encodedText);
        else write("Entschlüsselter Text: ", encodedText);
    }

    public void write(String desc, String text) {

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this, true));
            writer.write("***************************************************************************************");
            writer.newLine();
            writer.write(desc);
            writer.newLine();
            writer.write(text);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.getLogger().e(getClass().getName() + ".write", "Error während des Schreibens in die Textdatei!");
        }
    }

}

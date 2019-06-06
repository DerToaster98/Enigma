package de.Enigma.Util;

import de.Enigma.Core.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Nikolai Klatt
 * @brief Klasse, um eine Text Datei zu erzeugen
 * @details Die Enigma Datei wird benötigt, um die erzeugten Daten dem Nutzer nachvollziehbar und gut strukturiert darzustellen.
 */
class TextFile extends File {

    private static final long serialVersionUID = 2386034996542721472L;

    /**
     * @param pathname    - Wo und unter welchem Namen die Datei erstellt werden soll.
     * @param metaData    - Die MetaDaten des Prozesses
     * @param key         - verwendeter Schlüssel
     * @param clearText   - Text in Klarschrift
     * @param encodedText - Verschlüsselter Text
     * @brief Package Private Konstruktor der TextFile
     */
    TextFile(String pathname, String[] metaData, String key, String clearText, String encodedText) {
        super(pathname);


        write("Key:", key);
        write("Klartext: ", clearText);
        if (metaData[0].equals("Verschlüsselung"))
            write("Verschlüsselter Text: ", encodedText);
        else write("Entschlüsselter Text: ", encodedText);
        for (String s : metaData) {
            write(null, s);
        }
    }

    /**
     * @param desc - Bezeichnung des Datenteils
     * @param text - Datenteil
     * @brief Methode, um die Daten in die TextFile zu schreiben.
     */
    public void write(String desc, String text) {

        //writer der in die Datei schreibt
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this, true));
            writer.write("***************************************************************************************");

            if (desc != null) {
                writer.newLine();
                writer.write(desc);
            }
            writer.newLine();
            writer.write(text);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            //Error
            Log.getLogger().e(getClass().getName() + ".write", "Error während des Schreibens in die Textdatei");
        }
    }
}

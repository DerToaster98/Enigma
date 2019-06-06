package de.Enigma.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Nikolai Klatt
 * @brief Klasse, um eine Log Datei zu erzeugen
 * @details Die Log Datei wird benötigt, um die Vorgänge im System nachvollziehen zu können.
 */
class LogFile extends File {


    private static final long serialVersionUID = -7409210531157611910L;

    /**
     * @param pathname - Wo und unter welchem Namen die Datei erstellt werden soll.
     * @brief Package Private Konstruktor der LogFile
     */
    LogFile(String pathname) {
        super(pathname);
    }

    /**
     * @param text - Log Nachricht
     * @brief Methode zum schreiben von Logs in die Log Datei
     */
    public void write(String text) {

        //writer zum Schreiben in die Datei
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this, true));
            writer.write(text);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            //ERROR WEGEN LOGFILES
            //nicht logbar, da er sonst in einer Endlosschleife resultiert
        }
    }
}

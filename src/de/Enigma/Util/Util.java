package de.Enigma.Util;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Alle
 * @brief Klasse, welche Hilfsmethoden für die Stringmanipulation enthält
 */
public class Util {

    public Util() {
        // TODO Auto-generated constructor stub
    }

    //wandelt String in char-Arrya um

    /**
     * @param message Der String, welcher in ein char-Array umgewandelt werden soll.
     * @return Gibt das erzeugte char-Array zurück
     * @brief Methode, welche Strings, in char-Arrays umwandeln kann
     */
    public static char[] createCharArray(String message) {

        return message.toCharArray();
    }

    /**
     * @param c        Buchstabe, von welchem der Index gesucht werden soll
     * @param alphabet Alphabet, in dem nach dem Buchstaben gesucht werden soll
     * @return Liefert den Index des gescuhten Buchstabens im Alphabet zurück; -1, wenn das Alphabet den Buchstaben nicht enthält
     * @brief Liefert die Position eines Buchstaben in einem gegebenen Alphabet
     * @details Liefert position eines buchstaben in einem alphabet zurück !!von 1 bis 26!! Wenn -1: nicht im Alphabet
     */
    public static int getIndexOfCharInAlphabet(char c, char[] alphabet) {
        int index = 0;
        for (char value : alphabet) {
            index++;
            if (value == c) {
                return index;
            }
        }
        return -1;
    }

    /**
     * @return String der das Datum enthält. Format: 2016-11-16 12.08.43
     * @brief Methode zum Erzeugen eines Date String
     * @details Dieser String soll als Ordnername für die Dateien, die vom FileHandler erzeugt werden gemacht werden.
     */
    static String getTimeString() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");
        return dateFormat.format(new Date());
    }

    /**
     * @param s Der zu konvertierende String
     * @return JSON String
     * @brief Methode zum Konvertieren eines Strings zu einem JSON String
     */
    static String resolveStringtoJSON(String s) {
        Gson gson = new Gson();
        return gson.toJson(s);
    }

}
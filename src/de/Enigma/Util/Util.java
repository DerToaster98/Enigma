package de.Enigma.Util;

import com.google.gson.Gson;
import de.Enigma.Core.Log;
import de.Enigma.Util.Enums.EAlphabet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Alle
 * @brief Klasse, welche Hilfsmethoden für die Stringmanipulation enthält
 */
public class Util {

    public Util() {
    }

    //wandelt String in char-Array um

    /**
     * @param message Der String, welcher in ein char-Array umgewandelt werden soll.
     * @return Gibt das erzeugte char-Array zurück
     * @brief Methode, welche Strings, in char-Arrays umwandeln kann
     */
    public static char[] createCharArray(String message) {
        return message.toUpperCase().toCharArray();
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
     * @return Liefert einen zufälligen Schlüssel zurück
     * @brief Erzeugt einen zufälligen Schlüssel
     */
    public static String getNewRandomKey() {
        char[] rmValues = new char[]{'A', 'B', 'C'};
        List<Integer> millValues = new ArrayList<Integer>();
        //Umkehrwalze Werte
        for (int i = 1; i <= 5; i++) {
            millValues.add(i);
        }
        int plugboardElementCount;

        String key = "";

        Random rdm = new Random();
        //Umkehrwalze wählen und anhängen
        key += rmValues[rdm.nextInt(rmValues.length)];
        key += "-";

        //walzen wählen und anhängen
        for (int millIndex = 1; millIndex <= 3; millIndex++) {
            Integer millID = millValues.get(rdm.nextInt(millValues.size()));
            key += millID;

            millValues.remove(millID);
        }
        key += "-";
        //Startpositionen der Walzen wählen und anhängen
        for (int startPosIndex = 1; startPosIndex <= 3; startPosIndex++) {
            key += EAlphabet.getFromIndex(rdm.nextInt(26) + 1);
        }
        //key += "-";

        //Steckbrett erstellen
        char[] alphabet = EAlphabet.getAlphabet().clone();
        plugboardElementCount = rdm.nextInt(11);
        for (int i = 1; i < plugboardElementCount; i++) {
            String plug = "-";
            int firstCharIndex = rdm.nextInt(alphabet.length);
            while (alphabet[firstCharIndex] == '?') {
                firstCharIndex = rdm.nextInt(alphabet.length);
            }
            plug += alphabet[firstCharIndex];
            plug += ";";
            alphabet[firstCharIndex] = '?';

            int secondCharIndex = rdm.nextInt(alphabet.length);
            while (alphabet[secondCharIndex] == '?') {
                secondCharIndex = rdm.nextInt(alphabet.length);
            }
            plug += alphabet[secondCharIndex];
            alphabet[secondCharIndex] = '?';
            key += plug;
        }
        Log.getLogger().i(Util.class.getName() + ".getNewRandomKey", "Neuen Schlüssel generiert: " + key);
        return key;
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
    static String resolvetoJSON(String[] s) {
        Gson gson = new Gson();
        return gson.toJson(s);
    }

    private static List<Character> plugboard = new ArrayList<>();


    public static boolean isKeyValid(String key) {

        String[] parts = key.split("-");
        if (checkUKW(parts[0])) {

            if (checkMillsKey(parts[1])) {
                if (checkPositionKey(parts[2])) {

                    for (int i = 3; i < parts.length; i++) {

                        if (!checkLetterKey(parts[i])) {

                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }


    private static boolean checkLetterKey(String txt) {
        char[] c = txt.toCharArray();
        if (c.length == 3) {
            System.out.println(c.length);
            if (notInPlugboardKey(c[0]) && inAlphabetKey(c[0], true)) {

                if (c[1] == ';') {

                    if (notInPlugboardKey(c[2]) && inAlphabetKey(c[2], true)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    private static boolean notInPlugboardKey(char c) {
        if (plugboard.contains(c)) {
            return false;
        }

        return true;
    }


    private static boolean inAlphabetKey(char c, boolean plugboardBool) {
        char character = String.valueOf(c).toUpperCase().toCharArray()[0];
        for (EAlphabet alphabet : EAlphabet.values()) {

            if (character == alphabet.getAsChar()) {
                if (plugboardBool) {
                    plugboard.add(character);
                }
            }

            return true;
        }


        return false;
    }

    private static boolean checkPositionKey(String txt) {
        //Position der einzelnen Walzen
        char[] c = txt.toCharArray();
        if (c.length == 3) {
            return inAlphabetKey(c[0], false) && inAlphabetKey(c[1], false) && inAlphabetKey(c[2], false);
        }

        return false;
    }

    private static boolean checkMillsKey(String txt) {
        //einzelne Walzen
        char[] mill = txt.toCharArray();
        if (mill.length == 3) {
            for (int i = 1; i <= 5; i++) {
                char x = (char) (i + '0');
                if (mill[0] == x) {
                    for (int j = 1; j <= 5; j++) {
                        char y = (char) (j + '0');
                        if (mill[1] == y && mill[1] != x) {
                            for (int k = 1; k <= 5; k++) {
                                char z = (char) (k + '0');
                                if (mill[2] == z && mill[2] != y && mill[2] != x) {

                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    private static boolean checkUKW(String u) {
        return u.equalsIgnoreCase("A") || u.equalsIgnoreCase("B") || u.equalsIgnoreCase("C");
    }


}
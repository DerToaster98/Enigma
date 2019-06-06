package de.Enigma.Util;

import com.google.gson.Gson;
import de.Enigma.Core.Log;
import de.Enigma.Util.Enums.EAlphabet;
import de.Enigma.Util.Enums.EExtraCharacters;

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

    /**
     * @param message - Der String, welcher in ein char-Array umgewandelt werden soll.
     * @return Gibt das erzeugte char-Array zurück
     * @brief Methode, welche Strings, in char-Arrays umwandeln kann
     * @author Lisa Binkert
     */
    public static char[] createCharArray(String message) {

        return message.toCharArray();
    }

    /**
     * @param c        - Buchstabe, von welchem der Index gesucht werden soll
     * @param alphabet - Alphabet, in dem nach dem Buchstaben gesucht werden soll
     * @return Liefert den Index des gescuhten Buchstabens im Alphabet zurück; -1,
     * wenn das Alphabet den Buchstaben nicht enthält
     * @brief Liefert die Position eines Buchstaben in einem gegebenen Alphabet
     * @details Liefert position eines buchstaben in einem alphabet zurück !!von 0
     * bis 25!! Wenn <0: nicht im Alphabet
     * @author Oliver Seiler
     */
    public static int getIndexOfCharInAlphabet(char c, char[] alphabet) {
        int index = 0;
        for (char value : alphabet) {
            if (value == c) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * @return Liefert einen zufälligen Schlüssel zurück
     * @brief Erzeugt einen zufälligen Schlüssel
     * @author Oliver Seiler
     */
    public static String getNewRandomKey() {
        char[] rmValues = new char[]{'A', 'B', 'C'};
        List<Integer> millValues = new ArrayList<>();
        // Umkehrwalze Werte
        for (int i = 1; i <= 5; i++) {
            millValues.add(i);
        }
        int plugboardElementCount;

        String key = "";

        Random rdm = new Random();
        // Umkehrwalze wählen und anhängen
        key += rmValues[rdm.nextInt(rmValues.length)];
        key += "-";

        // walzen wählen und anhängen
        for (int millIndex = 1; millIndex <= 3; millIndex++) {
            Integer millID = millValues.get(rdm.nextInt(millValues.size()));
            key += millID;

            millValues.remove(millID);
        }
        key += "-";
        // Startpositionen der Walzen wählen und anhängen
        for (int startPosIndex = 1; startPosIndex <= 3; startPosIndex++) {
            key += EAlphabet.getFromIndex(rdm.nextInt(26));
        }
        // key += "-";

        // Steckbrett erstellen
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
     * @details Dieser String soll als Ordnername für die Dateien, die vom
     * FileHandler erzeugt werden genutzt werden.
     * @author Nikolai Klatt
     */
    static String getTimeString() {
        //nicht logbar, da benötigt, um den Logger zu starten...
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");
        return dateFormat.format(new Date());
    }

    /**
     * @return String der das Datum enthält. Format: 2016-11-16 12.08.43.123
     * @brief Methode zum Erzeugen eines Date String mit Millisekunden
     * @details Dieser String soll als Log-Timestamp
     * @author Nikolai Klatt
     */
    private static String getTimeStringMillis() {
        //nicht logbar, da sonst eine Endlosschleife erzeugt würde...
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss.SSS");
        return dateFormat.format(new Date());
    }

    /**
     * @param s - Der zu konvertierende String
     * @return JSON String
     * @brief Methode zum Konvertieren eines Strings zu einem JSON String
     * @author Nikolai Klatt
     */
    static String resolvetoJSON(String[] s) {
        Gson gson = new Gson();
        return gson.toJson(s);
    }

    private static List<Character> plugboard = new ArrayList<Character>();

    /**
     * @param keyA - Schlüssel, der geprüft werden soll
     * @return true bedeutet Schlüssel ist korrekt, false -> Schlüssel enthält
     * Fehler
     * @brief Überprüft ob Key gültig ist
     * @details Ein Key muss folgendermaßen aufgebaut sein:
     * U-AAA-XXX-X1;Y1-X2;Y2-...-X10;Y10 U = wechlche Umkehrwalze verwendet
     * wird, A B oder C
     * <p>
     * AAA = welche Walze an welcher Position steht z.B 3 2 1 Es gibt fünf
     * Walzen jede Walze kann nur einmal verwendet werden
     * <p>
     * XXX= Walzenstellung z.B TCK, es müssen drei Buchstaben sein
     * <p>
     * X1;Y1 = Buchstabe X1 ist im Steckbrett mit Y2 verbunden z.B E;G Die
     * Buchstaben die mitteinander vertauscht werden müssen mit einem ";"
     * getrennt werden Es können maximal zehn Buchstabenpaare vertauscht
     * werden.
     * <p>
     * Beispiel: C-312-RQV-R;Q-A;S-T;L-C;N Umkehrwalze C erste Walze (Walze
     * 3) auf R zweite Walze (Walze 1) auf Q dritte Walze (Walze 2) auf V
     * R mit Q verbunden A mit S verbunden T mit L verbunden C mit N
     * verbunden
     * @author Lisa Binkert
     */
    public static boolean isKeyValid(String keyA) {
        String key = keyA.toUpperCase();
        String[] parts = key.split("-");
        if (checkUKW(parts[0])) {
            //TODO Loggen, welcher teil des schlüssels falsch ist
            if (checkMillsKey(parts[1])) {
                if (checkPositionKey(parts[2])) {

                    for (int i = 3; i < parts.length; i++) {

                        if (!checkLetterKey(parts[i])) {
                            return false;
                        }

                    }
                    Log.getLogger().i(Util.class + ".isKeyValid", "Key (" + keyA + ") ist gültig");
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * @param txt - ein TeilString der Steckbretteingabe
     * @return true bedeutet Teilstring ist korrekt
     * @brief Überprüft die Steckbretteingaben (vierter Teil bis Ende von Key)
     * @details Eine Eingaben besteht aus zwei Buchstaben, getrennt durch ein
     * Semikolon. Jeder Buchstabe darf nur einmal vorkommen.
     * @author Lisa Binkert
     */
    private static boolean checkLetterKey(String txt) {
        char[] c = txt.toCharArray();
        if (c.length == 3) {
            if (notInPlugboardKey(c[0]) && inAlphabetKey(c[0], true)) {

                if (c[1] == ';') {

                    return notInPlugboardKey(c[2]) && inAlphabetKey(c[2], true);
                }
            }
        }

        Log.getLogger().w(Util.class.getName() + "isKeyValid", "Steckbretteingabe ist ungültig");
        return false;
    }

    /**
     * @param c - Buchstabe von der Steckbretteingabe
     * @return true bedeutet plugboard enthält c nicht, false -> plugboard
     * enthält c
     * @brief Teilfunktion von checkLetterKey()
     * @details Überprüft ob sich char in der plugboard-Liste befindet.
     * @author Lisa Binkert
     */
    private static boolean notInPlugboardKey(char c) {
        return !plugboard.contains(c);
    }

    /**
     * @param c             - Buchstabe
     * @param plugboardBool - true bedeutet schreib c in plugboard,
     * @return true bedeutet c ist ein Buchstabe
     * @brief Teilfunktion von checkPositionKey() und checkLetterKey()
     * @details Überprüft ob der char ein Buchstabe ist. Bei checkLetterKey() ist
     * der Parameter plugboardBool auf true, dann wird der Buchstabe
     * zusätzlich noch zur plugboard-Liste hinzugefügt.
     * @author Lisa Binkert
     */
    private static boolean inAlphabetKey(char c, boolean plugboardBool) {
        char character = String.valueOf(c).toUpperCase().toCharArray()[0];
        for (EAlphabet alphabet : EAlphabet.values()) {

            if (character == alphabet.getAsChar()) {
                if (plugboardBool) {
                    plugboard.add(character);
                }
                return true;
            }

        }

        return false;
    }

    /**
     * @param txt - TeilString des Keys
     * @return true bedeutet Teilstring ist korrekt
     * @brief Überprüft ob die Walzenpositionen gültig sind (dritter Teil des Keys)
     * @details Die Positionen werden von drei Buchstaben bestimmt
     * @author Lisa Binkert
     */
    private static boolean checkPositionKey(String txt) {
        // Position der einzelnen Walzen
        char[] c = txt.toCharArray();
        if (c.length == 3) {
            return inAlphabetKey(c[0], false) && inAlphabetKey(c[1], false) && inAlphabetKey(c[2], false);
        }
        Log.getLogger().w(Util.class.getName() + "isKeyValid", "Walzenpositionen im Schüssek ungültig");
        return false;
    }

    /**
     * @param txt - TeilString des Keys
     * @return true bedeutet Teilstring ist korrekt
     * @brief Überprüft ob die Walzen gültig sind (zweiter Teil des Keys)
     * @details Es müssen drei Zahlen zwischen eins und fünf sein, jede Zahl darf
     * maximal einmal vorkommen.
     * @author Lisa Binkert
     */
    private static boolean checkMillsKey(String txt) {
        // einzelne Walzen
        List<Character> number = new ArrayList<>();
        char[] millArray = txt.toCharArray();
        if (millArray.length == 3) {
            for (char mill : millArray) {
                for (int i = 1; i <= 5; i++) {
                    char[] x = String.valueOf(i).toCharArray();
                    if (mill == x[0]) {
                        if (number.contains(mill)) {
                            return false;
                        } else {
                            number.add(x[0]);
                        }
                    }

                }
            }
            return true;
        }

        Log.getLogger().w(Util.class.getName() + "isKeyValid", "Walzenangaben im Schlüssel sind ungültig");
        return false;
    }

    /**
     * @param u - TeilString des Keys, besteht aus nur einem Buchstaben
     * @return true bedeutet Teilstring ist korrekt
     * @brief Überprüft ob die Umkehrwalzen gültig sind (erster Teil des Keys)
     * @details ein Buchstabe, entweder A, B oder C
     * @author Lisa Binkert
     */
    private static boolean checkUKW(String u) {
        if (u.equalsIgnoreCase("A") || u.equalsIgnoreCase("B") || u.equalsIgnoreCase("C")) {
            return true;
        }
        Log.getLogger().w(Util.class.getName() + "isKeyValid", "Umkehrwalzenangaben im Schlüssel sind ungültig");
        return false;
    }


    public static void resetPlugBoardList() {
        Util.plugboard = null;
        Util.plugboard = new ArrayList<>();
    }

    /**
     * @param originalText - Der originale Text, der verschlüsselt werden soll
     * @return Liefert den bearbeiteten Text zurück
     * @author Oliver Seiler
     * @brief Methode, welche einen Text auf die Verschlüsselung vorbereitet
     * @details Sämtliche 'CH' und 'CK' werden durch ein 'Q' ersetzt und der Text wird in 5er Gruppen unterteilt
     */
    public static String prepareStringForReligma(String originalText) {
        String tmp = originalText.replaceAll(" ", "");

        tmp = tmp.replaceAll("CH", "Q");
        tmp = tmp.replaceAll("CK", "Q");

        //in 5er gruppen gruppieren
        String filteredString = "";
        int counter = 0;
        for (char c : tmp.toCharArray()) {
            if (!EExtraCharacters.isExtraCharacter(c)) {
                if (counter % 5 == 0 && counter > 0) {
                    filteredString += EExtraCharacters.SPACE.getAssignedChar();
                }
                filteredString += c;
            } else if (c != EExtraCharacters.SPACE.getAssignedChar()) {
                if (counter % 5 == 0 && counter > 0) {
                    filteredString += EExtraCharacters.SPACE.getAssignedChar();
                }
                filteredString += 'X';
            }
            counter++;
        }

        Log.getLogger().i(Util.class.getName() + "preapreStringForReligma", "String für Verschlüsselung mit emulierter Enigma formatiert");
        return filteredString;
    }

    /**
     * @param class_method_name - Name der Klasse und Methode von der der Log-Aufruf erfolgt ist
     * @param message           - Nachricht, die vom Logger geloggt werden soll
     * @param type              - Typ des Logs
     * @return Richtig formatierter Log-String
     * @brief Methode zum Erstellen der Log-Strings
     * @author Nikolai Klatt
     */
    public static String resolveLogString(String class_method_name, String message, String type) {
        String log;
        String time = getTimeStringMillis();

        log = "(" + time + ") - [" + type + "]: " + class_method_name + ":" + message;
        return log;
    }
}
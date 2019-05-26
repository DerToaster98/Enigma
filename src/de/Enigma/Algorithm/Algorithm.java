package de.Enigma.Algorithm;


import de.Enigma.Util.Enums.EAlphabet;
import de.Enigma.Util.Enums.EMill;

/**
 * @author Lisa Binkert
 * @brief Die Klasse Algorithm enthält die Ver/Entschlüsselungs Methode für Encrytor und Decryptor,
 * sowie eine checkKey Methode.
 * @details Ein Key muss folgendermaßen aufgebaut sein:
 * U-AAA-XXX-X1;Y1-X2;Y2-...-X10;Y10
 * U = wechlche Umkehrwalze verwendet wird, A B oder C
 * <p>
 * AAA = welche Walze an welcher Position steht z.B 3 2 1
 * Es gibt fünf Walzen jede Walze kann nur einmal verwendet werden
 * <p>
 * XXX= Walzenstellung z.B TCK, es müssen drei Buchstaben sein
 * <p>
 * X1;Y1 = Buchstabe X1 ist im Steckbrett mit Y2 verbunden z.B E;G
 * Die Buchstaben die mitteinander vertauscht werden müssen mit einem ";" getrennt werden
 * Es können maximal zehn Buchstabenpaare  vertauscht werden.
 * <p>
 * Beispiel: C-312-RQV-R;Q-A;S-T;L-C;N
 * Umkehrwalze C
 * erste Walze (Walze 3) auf R
 * zweite Walze (Walze 1) auf Q
 * dritte Walze (Walze 2) auf V
 * R mit Q verbunden
 * A mit S verbunden
 * T mit L verbunden
 * C mit N verbunden
 * <p>
 * <p>
 * Enthält Basis Algorithmus für Ver/Entschlüsselung
 * Außerdem ist eine Methode enthalten, welche überprüft, ob ein Schlüssel korrekt ist
 * Dies überprüft die "Steckbrett Sektion" des Schlüssels auf Duplikate
 */
public abstract class Algorithm {
    private EnigmaConfig conf;
    private AlgorithmController controller;
    private String key;
    private String[] metaData;
    private char[] plugboard = new char[40];
    private int counter = 0;


    public Algorithm() {
        // TODO Auto-generated constructor stub
        conf = controller.getEnigmaConfig();
        controller = controller.getController();

    }

    /**
     * @param letter
     * @return
     * @brief Ver-/Entschlüssel einen Buchstaben
     */
    protected char encrypt(char letter) {

        //erste Walze -> zweite Walze -> dritte Walze -> Umkehrwalze
        for (EMill mill : EMill.values()) {
            letter = conf.encryptLetter(letter, mill, false);
        }
        for (int i = 2; i > 0; i--) {
            letter = conf.encryptLetter(letter, EMill.values()[i], true);
        }

        //Buchstabe zurückgeben
        return letter;
    }


    /**
     * @param key
     * @return
     * @brief Überprüft ob der Schlüssel korrekt eingegeben wurde
     */
    public boolean isKeyValid(String key) {

        String[] parts = key.split("-");
        if (checkUKW(parts[0])) {

            if (checkMills(parts[1])) {
                if (checkPosition(parts[2])) {

                    for (int i = 3; i < parts.length; i++) {

                        if (!checkLetter(parts[i])) {

                            return false;
                        }


                    }
                    return true;
                }
            }

        }
        return false;

    }

    private boolean checkLetter(String txt) {
        char[] c = txt.toCharArray();
        if (c.length == 3) {
            System.out.println(c.length);
            if (notInPlugboard(c[0]) && inAlphabet(c[0], true)) {

                if (c[1] == ';') {

                    if (notInPlugboard(c[2]) && inAlphabet(c[2], true)) {
                        ;
                        return true;
                    }
                }
            }
        }

        return false;
    }


    private boolean notInPlugboard(char c) {
        for (char value : plugboard) {
            if (c == value) {
                return false;
            }
        }
        return true;
    }

    private boolean inAlphabet(char c, boolean plugboardBool) {
        for (EAlphabet alphabet : EAlphabet.values()) {
            char lowerAlphabet = (char) (alphabet.getAsChar() + 32);
            if (c == alphabet.getAsChar()) {
                if (plugboardBool) {
                    plugboard[counter] = c;
                    counter++;
                    plugboard[counter] = (char) (c + 32);
                    counter++;
                }
            }
            if (c == lowerAlphabet) {
                if (plugboardBool) {
                    plugboard[counter] = c;
                    counter++;
                    plugboard[counter] = (char) (c - 32);
                    counter++;
                }
                return true;
            }

        }
        return false;
    }

    private boolean checkPosition(String txt) {

        char[] c = txt.toCharArray();
        if (c.length == 3) {
            return inAlphabet(c[0], false) && inAlphabet(c[1], false) && inAlphabet(c[2], false);
        }

        return false;
    }

    private boolean checkMills(String txt) {

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


    private boolean checkUKW(String u) {
        return u.equals("A") | u.equals("B") | u.equals("C");
    }

    /**
     * @brief Übergibt metaData an FileHandler()
     */

    protected void createMetaData() {
        metaData[0] = "Typ: " + getClass().getName();
        String key = controller.getKey();
        String[] parts = key.split("-");
        char[] walzen = parts[1].toCharArray();
        char[] position = parts[2].toCharArray();
        String buchstaben = " ";
        for (int i = 3; i < parts.length; i++) {
            buchstaben = buchstaben + parts[i] + " ";
        }

        metaData[1] = "Umkehrwalze: " + parts[0];
        metaData[2] = "1. Walze: " + walzen[0] + " Startposition: " + position[0];
        metaData[3] = "2. Walze: " + walzen[1] + " Startposition: " + position[1];
        metaData[4] = "3. Walze: " + walzen[2] + " Startposition: " + position[2];
        metaData[5] = "Vertauschte Buchstaben:" + buchstaben;

    }


}

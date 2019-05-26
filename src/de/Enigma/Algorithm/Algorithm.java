package de.Enigma.Algorithm;


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
public class Algorithm {
    private EnigmaConfig conf;
    private AlgorithmController controller;
    private String key;
    private String[] metaData;


    public Algorithm(AlgorithmController controller) {
        // TODO Auto-generated constructor stub
        this.controller = controller;
        conf = controller.getEnigmaConfig();


    }

    /**
     * @param letter
     * @return
     * @brief Ver-/Entschlüssel einen Buchstaben
     */
    protected char encrypt(char letter) {
        letter = conf.encryptLetterWithPlugBoard(letter);
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


    /**
     * @brief Übergibt metaData an FileHandler()
     */

    protected void createMetaData() {
        metaData[0] = "Typ: ";
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

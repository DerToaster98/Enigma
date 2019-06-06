package de.Enigma.Util;

import com.google.gson.stream.JsonWriter;
import de.Enigma.Core.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Nikolai Klatt
 * @brief Klasse, um eine Enigma Datei zu erzeugen
 * @details Die Enigma Datei wird benötigt, um die Daten für das System einfach zugänglich zu speichern.
 * So können wir sie zu einem späteren Zeitpunkt wieder einlesen lassen. Die Datei soll als Json Datei formatiert sein.
 */
class EnigmaFile extends File {

    private static final long serialVersionUID = 6316501597004789916L;
    private String metaData;
    private String key;
    private String clearText;
    private String encodedText;

    /**
     * @param pathname    - Wo und unter welchem Namen die Datei erstellt werden soll.
     * @param metaData    - Die MetaDaten des Prozesses
     * @param key         - verwendeter Schlüssel
     * @param clearText   - Text in Klarschrift
     * @param encodedText - Verschlüsselter Text
     * @brief Package Private Konstruktor der EnigmaFile
     */
    EnigmaFile(String pathname, String[] metaData, String key, String clearText, String encodedText) {
        super(pathname);

        this.metaData = Util.resolvetoJSON(metaData);
        this.key = key;
        this.clearText = clearText;
        this.encodedText = encodedText;
        write();
    }

    /**
     * @brief Methode, um die Daten in die EnigmaFile zu schreiben.
     * @details Unter Verwendung des JsonWriters wird ein Json Objekt in die Enigma Datei geschrieben.
     */
    public void write() {

        JsonWriter writer;
        try {
            writer = new JsonWriter(new FileWriter(this));

            //einzelnes JsonObjekt, das alle Daten enthält
            writer.beginObject();
            writeValueToObject(writer, "metaData", metaData);
            writeValueToObject(writer, "key", key);
            writeValueToObject(writer, "clearText", clearText);
            writeValueToObject(writer, "encodedText", encodedText);
            writer.endObject();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.getLogger().e(getClass().getName() + ".write", "Error während des Schreibens in die Enigmadatei");
        }
    }

    /**
     * @param writer - JsonWriter, der das Json Objekt in die Datei schreibt.
     * @param name   - Name des Values der geschrieben werden soll
     * @param value  - Value der geschrieben werden soll
     * @throws IOException Wenn die Datei nicht beschrieben werden kann, oder sie nicht existiert
     * @brief Methode, um einem Json Objekt ein Value hinzuzufügen
     */
    private void writeValueToObject(JsonWriter writer, String name, String value) throws IOException {
        //schreibt einen neuen Value
        writer.name(name);
        writer.value(value);
    }
}
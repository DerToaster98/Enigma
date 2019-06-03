package de.Enigma.Util;

import com.google.gson.stream.JsonWriter;
import de.Enigma.Core.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class EnigmaFile extends File {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6316501597004789916L;
	private String metaData;
    private String key;
    private String clearText;
    private String encodedText;

    EnigmaFile(String pathname, String[] metaData, String key, String clearText, String encodedText) {
        super(pathname);

        this.metaData = Util.resolvetoJSON(metaData);
        this.key = key;
        this.clearText = clearText;
        this.encodedText = encodedText;
        write();
    }

    public void write() {

        JsonWriter writer;
        try {
            writer = new JsonWriter(new FileWriter(this));

            writer.beginObject();
            writeObject(writer, "metaData", metaData);
            writeObject(writer, "key", key);
            writeObject(writer, "clearText", clearText);
            writeObject(writer, "encodedText", encodedText);
            writer.endObject();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.getLogger().e(getClass().getName() + ".write", "Error während des Schreibens in die Enigmadatei!");
        }
    }

    /**
     * @param writer JsonWriter, der das Json Objekt in die Datei schreibt.
     * @param name Name des Values der geschrieben werden soll
     * @param value Value der geschrieben werden soll
     * @throws IOException Wenn die Datei nicht beschrieben werden kann, oder sie nicht existiert
     */
    private void writeObject(JsonWriter writer, String name, String value) throws IOException {
        writer.name(name);
        writer.value(value);
    }

}

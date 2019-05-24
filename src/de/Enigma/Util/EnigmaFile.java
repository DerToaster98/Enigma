package de.Enigma.Util;

import java.io.File;

class EnigmaFile extends File {

    private String keyJson;
    private String encodedTextJson;
    private String clearTextJson;
    private String metaDataJson;

    private final String EXTENSION = ".enigma";

    EnigmaFile(String pathname, String metaData, String key, String clearText, String encodedText) {
        super(pathname);

        metaDataJson = Util.resolveStringtoJSON(metaData);
        keyJson = Util.resolveStringtoJSON(key);
        clearTextJson = Util.resolveStringtoJSON(clearText);
        encodedTextJson = Util.resolveStringtoJSON(encodedText);
        
    }

}

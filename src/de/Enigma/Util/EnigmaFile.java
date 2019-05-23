package de.Enigma.Util;

import java.io.File;

public class EnigmaFile extends File {

    private final String EXTENSION = ".enigma";

    public EnigmaFile(String pathname) {
        super(pathname);
    }

}

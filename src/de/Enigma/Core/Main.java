package de.Enigma.Core;

import de.Enigma.UI.GUI;
import de.Enigma.Util.FileHandler;

public class Main {

    private GUI window;
    private FileHandler fileHandler;

    private Main() {

        Log.getLogger();
        initGUI();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void initGUI() {
        GUI window = new GUI(this);
        window.show();
        this.window = window;
    }

    public void btnOkClicked(String text, String key, boolean encrypt) {
        //TODO
        //übergabeParameter einbinden
        // encrypt als boolean abfragen -> wenn true dann "new Encryptor", sonst "new Decryptor"?
        System.out.println("BTN_OK Clicked");
    }

    public void btnCancelClicked() {

    }

    public FileHandler getFileHandler() {
        if (fileHandler == null){
            fileHandler = new FileHandler();
        }
        return fileHandler;
    }

	public GUI getWindow() {
		return window;
	}

}

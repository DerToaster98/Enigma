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
    }

    public void btnOkClicked() {
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
}

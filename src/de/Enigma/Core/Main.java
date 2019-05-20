package de.Enigma.Core;

import de.Enigma.UI.GUI;
import de.Enigma.Util.FileHandler;

public class Main {

	private GUI window;
	private FileHandler fileHandler;

	private Main() {

		initFileHandler();
		System.out.println(fileHandler.getPATH());
		initGUI();
	}
	
	public static void main(String[] args) {
		new Main();
	}

	private void initGUI() {
		GUI window = new GUI(this);
		window.show();
	}

	private void initFileHandler(){
		fileHandler = new FileHandler();
	}

	public void btnOkClicked(){
		System.out.println("BTN_OK Clicked");
	}

	public void btnCancelClicked(){

	}

	public FileHandler getFileHandler(){
		return fileHandler;
	}
}

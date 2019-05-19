package de.Enigma.Core;

import de.Enigma.UI.GUI;

public class Main {

	private Main() {
		initGUI();
	}
	
	public static void main(String[] args) {
		new Main();
	}

	private void initGUI() {
		GUI window = new GUI(this);
		window.show();
	}

	public void btnOkClicked(){
		System.out.println("BTN_OK Clicked");
	}

	public void btnCancelClicked(){

	}
}

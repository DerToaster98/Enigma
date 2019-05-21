package de.Enigma.Algorithm;

import java.util.HashMap;

import de.Enigma.Util.Enums.EMill;

public class EnigmaConfig {

	private String key;
	private HashMap<Character,Character> letterChanger = new HashMap<Character, Character>();
	private Mill[] mills = new Mill[] {null,null,null};
	private Mill reverseMill = null;

	public EnigmaConfig() {
		// TODO Key auf Richtigkeit prüfen
		// TODO Walzen erstellen und hier zuweisen
		// TODO "Steckbrett" befüllen
		// TODO Buchstaben verschlüsseln schreiben
	}

	public char encryptLetter(char letter){
	return ' ';
	}

	//Überprüft die Walzen auf ihre Kerben. wenn die dritte Walze die Kerbe überschritten hat, so rotiert auch die zweite. Hat auch die zweite Walze die Kerbe überschritten, so rotiert auch die erste....
	private void checkMills() {
		Mill first = getMill(EMill.FIRST_MILL);
		if(first != null && first.shouldRotateNeighborMill()) {
			Mill second = getMill(EMill.SECOND_MILL);
			if(second != null) {
				second.rotateMill();
				if(second.shouldRotateNeighborMill()) {
					Mill third = getMill(EMill.THIRD_MILL);
					if(third != null) {
						third.rotateMill();
					}
				}
			}
		}
	}
	//Methode, um eine Walze anhand des enums zu identifizieren
	private Mill getMill(EMill mill) {
		switch (mill) {
		case FIRST_MILL:
			return this.reverseMill;
		case RETURN_MILL:
			return this.mills[0];
		case SECOND_MILL:
			return this.mills[1];
		case THIRD_MILL:
			return this.mills[2];
		default:
			return null;
		}
	}

}

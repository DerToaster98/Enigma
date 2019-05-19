package de.Enigma.Algorithm;

public class Algorithm implements Runnable {

	//Überprüfung ob Schlüssel korrekt ist 
	//AAA-XXX-X1;Y1-X2;Y2-...-X10;Y10 max zehn Buchstaben
	//AAA = welche Walze an welcher Position steht z.B 3 2 1 
	//XXX= Walzenstellung z.B 07 23 4, 
	//X1;Y1 = Buchstabe X1 ist im Steckbrett mit Y2 verbunden z.B E;G
	
	/*
	 * Enthält Basis Algorithmus für Ver/Entschlüsselung
	 * Außerdem ist eine Methode enthalten, welche überprüft, ob ein Schlüssel korrekt ist
	 * Dies überprüft die "Steckbrett Sektion" des Schlüssels auf Duplikate
	 */
	
	public Algorithm() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}

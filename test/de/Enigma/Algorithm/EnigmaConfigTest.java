/**
 * 
 */
package de.Enigma.Algorithm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Lisa Binkert
 *
 */
public class EnigmaConfigTest {

	@Test
	public void encryptLetterWithPlugBoardTest() {
		//Dieser Test testet die Ersetzung eines Buchstabens durch das Steckbrett
		String key = "A-124-TER-A;P";
		EnigmaConfig conf = new EnigmaConfig(key);
		char charA = 'A'; // soll zu  P werden
		char charP = 'P'; // soll zu A werden
		char charZ = 'Z'; //Dieser Buchstabe ist nicht im Steckbrett enthalten, er soll nicht verändert werden
		
		assertEquals('P', conf.encryptLetterWithPlugBoard(charA));
		assertEquals('A', conf.encryptLetterWithPlugBoard(charP));
		assertEquals('Z', conf.encryptLetterWithPlugBoard(charZ));

		
	}

}

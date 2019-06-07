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
		
		String key = "A-124-TER-A;P";
		EnigmaConfig conf = new EnigmaConfig(key);
		char charA = 'A';
		char charP = 'P';
		char charZ = 'Z';
		
		assertEquals('P', conf.encryptLetterWithPlugBoard(charA));
		assertEquals('A', conf.encryptLetterWithPlugBoard(charP));
		assertEquals('Z', conf.encryptLetterWithPlugBoard(charZ));

		
	}

}

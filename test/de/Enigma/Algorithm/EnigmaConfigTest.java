/**
 * 
 */
package de.Enigma.Algorithm;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Admin
 *
 */
public class EnigmaConfigTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

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

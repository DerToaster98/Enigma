/**
 * 
 */
package de.Enigma.Algorithm;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.Enigma.Util.Enums.EAlphabet;
import de.Enigma.Util.Enums.EMillAlphabet;

/**
 * @author Oliver Seiler
 *
 */
public class MillTest {

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
	public void rotateMillTest() {
		Mill millTest = new Mill('?', EMillAlphabet.I);
		
		char origFirstChar = EMillAlphabet.I.getAlphabet()[0];
		//System.out.println("Orig First: " + origFirstChar);
		char newFirstChar = EMillAlphabet.I.getAlphabet()[1];
		//System.out.println("Orig Last: " + newFirstChar);
		
		millTest.rotateMill();
		
		assertEquals(origFirstChar, millTest.getAlphabet()[millTest.getAlphabet().length -1]);
		//System.out.println("New Last: " +millTest.getAlphabet()[millTest.getAlphabet().length -1]);
		assertEquals(newFirstChar, millTest.getAlphabet()[0]);
		//System.out.println("New First: " +millTest.getAlphabet()[0]);
		
	}
	
	@Test
	public void testCreation() {
		char[] origPhabet = EMillAlphabet.IV.getAlphabet();
		Mill millTest = new Mill('?', EMillAlphabet.IV);
		
		assertTrue(areArraysEqual(origPhabet, millTest.getAlphabet()));
	}
	
	@Test
	public void testEncryption() {
		char inputChar = 'A';
		char expectedOutputChar = 'E';
		
		Mill millTest = new Mill('?', EMillAlphabet.I);
		
		char encryptedNormal = millTest.encryptLetter(inputChar, EAlphabet.getAlphabet(), false);
		
		assertEquals(expectedOutputChar, encryptedNormal);
		
		expectedOutputChar = 'A';
		inputChar = 'E';
		
		char encryptedReverse = millTest.encryptLetter(inputChar, EAlphabet.getAlphabet(), true);
		
		assertEquals(expectedOutputChar, encryptedReverse);
	}

	private boolean areArraysEqual(char[] origPhabet, char[] alphabet) {
		if(origPhabet.length != alphabet.length) {
			return false;
		}
		for(int i = 0; i < origPhabet.length; i++) {
			if(origPhabet[i] != alphabet[i]) {
				return false;
			}
		}
		return true;
	}
	
	//Testbare szenarien:
	//  - Walze rotieren -> DONE
    //  - Buchstaben verschlüsseln -> DONE
    //  - "Einstellen" der Walze am Anfang -> DONE
	
	

}

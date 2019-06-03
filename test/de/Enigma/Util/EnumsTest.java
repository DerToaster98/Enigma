/**
 * 
 */
package de.Enigma.Util;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.Enigma.Util.Enums.EAlphabet;
import de.Enigma.Util.Enums.EExtraCharacters;
import de.Enigma.Util.Enums.EMillAlphabet;

/**
 * @author Admin
 *
 */
public class EnumsTest {

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
	public void eAlphabetGetFromIndexTest() {
		assertEquals(EAlphabet.E, EAlphabet.getFromIndex(4));
		assertNull(EAlphabet.getFromIndex(26));
	}
	
	@Test
	public void eExtraCharacterIsExtraCharacterTest() {
		assertTrue(EExtraCharacters.isExtraCharacter('?'));
		assertFalse(EExtraCharacters.isExtraCharacter('A'));
	}
	
	@Test
	public void eMillAlphabetGetByIDTest() {
		assertEquals(EMillAlphabet.I, EMillAlphabet.getByID("1"));
		assertEquals(EMillAlphabet.II, EMillAlphabet.getByID("2"));
		assertEquals(EMillAlphabet.III, EMillAlphabet.getByID("3"));
		assertEquals(EMillAlphabet.IV, EMillAlphabet.getByID("4"));
		assertEquals(EMillAlphabet.V, EMillAlphabet.getByID("5"));
		
		assertEquals(EMillAlphabet.UKW_A, EMillAlphabet.getByID("A"));
		assertEquals(EMillAlphabet.UKW_B, EMillAlphabet.getByID("B"));
		assertEquals(EMillAlphabet.UKW_C, EMillAlphabet.getByID("C"));
		
		assertNull(EMillAlphabet.getByID("Bobdus"));
	}
	
	@Test
	public void eMillAlphabetGetTurnMarkerTest() {
		assertEquals('X', EMillAlphabet.I.getTurnMarker());
		assertEquals('S', EMillAlphabet.II.getTurnMarker());
		assertEquals('M', EMillAlphabet.III.getTurnMarker());
		assertEquals('Q', EMillAlphabet.IV.getTurnMarker());
		assertEquals('K', EMillAlphabet.V.getTurnMarker());
		
		assertEquals('?', EMillAlphabet.UKW_A.getTurnMarker());
		assertEquals('?', EMillAlphabet.UKW_B.getTurnMarker());
		assertEquals('?', EMillAlphabet.UKW_C.getTurnMarker());
	}
}

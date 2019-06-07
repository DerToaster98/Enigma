/**
 * 
 */
package de.Enigma.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.Enigma.Util.Enums.EAlphabet;
import de.Enigma.Util.Enums.EExtraCharacters;
import de.Enigma.Util.Enums.EMillAlphabet;

/**
 * @author Oliver Seiler
 *
 */
public class EnumsTest {

	@Test
	public void eAlphabetGetFromIndexTest() {
		//Überprüft, ob die Methode, welche den Index eines Buchstabens im Alphabet ermittelt, den rictigen Index ermittelt
		//->Überprüft, ob ein Buchstabe an der richtigen Stelle des Alphabets steht
		assertEquals(EAlphabet.E, EAlphabet.getFromIndex(4));
		assertNull(EAlphabet.getFromIndex(26));
	}
	
	@Test
	public void eExtraCharacterIsExtraCharacterTest() {
		//Überprüft eine Methode die überprüft ob es sich um ein Sonderzeichen von EExtraCharacter handelt
		assertTrue(EExtraCharacters.isExtraCharacter('?'));
		assertFalse(EExtraCharacters.isExtraCharacter('A'));
	}
	
	@Test
	public void eMillAlphabetGetByIDTest() {
		//Überpüft ob man anhand der ID die Richtige Konfiguration für die Walzen erhält.
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
		//Überprüft ab man die Richtige Übertragskerbe für die einzelnen Walzen erhält
		//die Umkehrwalzen haben ein '?' als Übertragskerbe, da diese eigentlich enthalten, da sie sich nicht rotiern
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

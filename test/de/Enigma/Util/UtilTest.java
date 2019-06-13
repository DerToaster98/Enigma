/**
 * 
 */
package de.Enigma.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Lisa Binkert
 *
 */
public class UtilTest {

	@Test
	public void prepareStringForReligmaTest() {
		//Überprüft ob ein String richtig formatiert wird
		//Buchstaben in Großbuchstaben
		//CK und CH werden zu Q; Satz- und Sonderzeichen werden zu X
		//Gesamter Text wird in fünfer Gruppen zerteilt
		String text = "Nachricht, Bobdus? Jucken Schokolade, -Schlange. Hackfleisch!";
		String ergebnis = "NAQRI QTXBO BDUSX JUQEN SQOKO LADEX XSQLA NGEXH AQFLE ISQX";
						  
		String whatDidIt = Util.prepareStringForReligma(text.toUpperCase());
		
		assertEquals(ergebnis, whatDidIt);
	}
	
	@Test
	public void getIndexOfCharInAlphabetTest() {
		//Überprüft ob der Richtige Index eines chars in einem char-Array ausgegeben wird
		char[] alphabet = new char[] {'A','B','C','D'};
		
		assertEquals(1, Util.getIndexOfCharInAlphabet('B', alphabet));
		assertEquals(-1, Util.getIndexOfCharInAlphabet('L', alphabet));
	}


	@Test
	public void isKeyValidTest() {
		//Überprüfung der Methode, die überprüft ob ein Schlüssel gültig ist
		String key0 = "A-413-HRR-T;N-F;V-D;K-S;O-Q;P";
		String key1 = "F-123-FJU-T;O-K;Q-B;M";//Falsche Umkehrwalze
		String key2 = "A-512-FDE-Q;W-E;R-T;Z-U;I-O;P-A;S-D;F-G;H-J;K-L;Y-X;C-V;B";//zuviele vertauschte Buchstaben
		String key3 = "B-332-JNZ";//Falsche Walzen (Zahle doppelt)
		String key4 = "C-415-F4A-I;P"; //Falsche Walzenposition
		String key5 = "A-512-FUU-R;O-P;Q-T;R"; //Falsche Steckbretteingabe
		String key6 = "A-712-NBV-L;O"; //Falsche Walzeneingabe(Zahl>5)
		
		assertTrue(Util.isKeyValid(key0));
		
		assertFalse(Util.isKeyValid(key1));
		assertFalse(Util.isKeyValid(key2));
		assertFalse(Util.isKeyValid(key3));
		assertFalse(Util.isKeyValid(key4));
		assertFalse(Util.isKeyValid(key5));
		assertFalse(Util.isKeyValid(key6));
	}
	
	
}

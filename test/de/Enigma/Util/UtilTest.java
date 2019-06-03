/**
 * 
 */
package de.Enigma.Util;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Admin
 *
 */
public class UtilTest {

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
	public void isKeyValidTest() {
		
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

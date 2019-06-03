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
public class AlgorithmTest {

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
	public void encrytTest() {
		
		//Ablauf: A->(1)->E->(2)->S->(3)->G->(UKWA)->Y->(3)->O->(2)->Y->(1)->O
		char inputChar= 'A';
		String key = "A-123-AAA";
		
		AlgorithmController controller = null;
		try {
			controller = new AlgorithmController(null, key);
		} catch(NullPointerException npe) {
			//Muss gecatcht und gecancelt werden weil wir sonst jedes mal die Main und den dazugehörigen Rattenschwanz istanziieren müssen
		}
		Algorithm algo = new Algorithm(controller);
		
		assertEquals('O', algo.encrypt(inputChar));
		
		
		
		
	}

}

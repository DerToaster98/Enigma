/**
 * 
 */
package de.Enigma.Algorithm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Lisa Binkert & Oliver Seiler
 *
 */
public class AlgorithmTest {
	
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

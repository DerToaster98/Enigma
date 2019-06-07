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
		
		Algorithm algo = new Algorithm(new EnigmaConfig(key));
		
		assertEquals('O', algo.encrypt(inputChar));
		
		algo = new Algorithm(new EnigmaConfig(key));
		inputChar = 'O';
		assertEquals('A', algo.encrypt(inputChar));
		
		
		
	}

}

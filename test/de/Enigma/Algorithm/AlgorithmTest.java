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
		//Dieser Test testet die Ver- und Entschlüsselung eines einzelnen Buchstaben
		//Das A soll bei der Walzenstellung A-123-AAA zu einem O werden.
		//Das O wiederum muss bei der gleichen Walzenstellung wieder zu einm A werden
		//Ablauf: A->(1)->E->(2)->S->(3)->G->(UKW-A)->Y->(3)->O->(2)->Y->(1)->O
		char inputChar= 'A';
		String key = "A-123-AAA";
		
		Algorithm algo = new Algorithm(new EnigmaConfig(key));
		
		assertEquals('O', algo.encrypt(inputChar));
		
		algo = new Algorithm(new EnigmaConfig(key));
		inputChar = 'O';
		assertEquals('A', algo.encrypt(inputChar));
		
		
		
	}

}

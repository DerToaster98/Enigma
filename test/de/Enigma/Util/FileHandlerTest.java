/**
 * 
 */
package de.Enigma.Util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

/**
 * @author Oliver Seiler
 *
 */
public class FileHandlerTest {

	@Test
	public void testAppendChar() {
		//Überprüft ob der String richtig  zusammengesetzt wird
		try {
			Field encodedTextField = FileHandler.class.getDeclaredField("encodedText");
			
			encodedTextField.setAccessible(true);
			
			String currVal = (String) encodedTextField.get(FileHandler.getFileHandler());
			
			FileHandler.getFileHandler().resetEncodedText();
			
			currVal = (String) encodedTextField.get(FileHandler.getFileHandler());
			assertEquals("", currVal);
			
			char[] helloWorldChars = new char[] {'H','A','L','L','O',' ','W','E','L','T'};
			for(char c : helloWorldChars) {
				FileHandler.getFileHandler().appendChar(c);
			}
			
			currVal = (String) encodedTextField.get(FileHandler.getFileHandler());
			assertEquals("HALLO WELT", currVal);
			
			encodedTextField.setAccessible(false);
			
		} catch(NoSuchFieldException nsfe) {
			nsfe.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void checkFileHandlerInstantiation() {
		//Überprüft ob der FileHandler richtig instanziiert wird
		Object fileHandlerObj = FileHandler.getFileHandler();
		
		assertNotNull(FileHandler.getFileHandler());
		assertTrue(fileHandlerObj instanceof FileHandler);
	}

}
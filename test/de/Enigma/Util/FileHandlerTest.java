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
		try {
			Field encodedTextField = FileHandler.class.getDeclaredField("encodedText");
			
			encodedTextField.setAccessible(true);
			
			String currVal = (String) encodedTextField.get(FileHandler.getFileHandler());
			
			FileHandler.getFileHandler().resetEncodedText();
			
			currVal = (String) encodedTextField.get(FileHandler.getFileHandler());
			assertEquals("", currVal);
			
			FileHandler.getFileHandler().appendChar('H');
			FileHandler.getFileHandler().appendChar('A');
			FileHandler.getFileHandler().appendChar('L');
			FileHandler.getFileHandler().appendChar('L');
			FileHandler.getFileHandler().appendChar('O');
			FileHandler.getFileHandler().appendChar(' ');
			FileHandler.getFileHandler().appendChar('W');
			FileHandler.getFileHandler().appendChar('E');
			FileHandler.getFileHandler().appendChar('L');
			FileHandler.getFileHandler().appendChar('T');
			
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
		Object fileHandlerObj = FileHandler.getFileHandler();
		
		assertNotNull(FileHandler.getFileHandler());
		assertTrue(fileHandlerObj instanceof FileHandler);
	}

}
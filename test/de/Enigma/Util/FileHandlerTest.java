/**
 * 
 */
package de.Enigma.Util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Nikolai Klatt
 *
 */
public class FileHandlerTest {



	@Test
	public void testAppendChar() {

	}

	@Test
	public void checkFileHandlerInstantiation() {
		Object fileHandlerObj = FileHandler.getFileHandler();
		
		assertNotNull(FileHandler.getFileHandler());
		assertTrue(fileHandlerObj instanceof FileHandler);
	}

}
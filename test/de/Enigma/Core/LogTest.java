/**
 * 
 */
package de.Enigma.Core;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Oliver Seiler
 *
 */
public class LogTest {

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
	public void checkLoggerInstantiation() {
		Object loggerObj = Log.getLogger();
		
		assertNotNull(Log.getLogger());
		assertTrue(loggerObj instanceof Log);
	}

}

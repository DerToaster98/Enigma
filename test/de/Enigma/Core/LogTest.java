/**
 * 
 */
package de.Enigma.Core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Oliver Seiler
 *
 */
public class LogTest {

	@Test
	public void checkLoggerInstantiation() {
		Object loggerObj = Log.getLogger();
		
		assertNotNull(Log.getLogger());
		assertTrue(loggerObj instanceof Log);
	}

}

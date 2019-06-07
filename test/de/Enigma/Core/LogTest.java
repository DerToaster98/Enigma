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
		//Überprüft, ob der Logger richtig instanziiert wird
		//-> Ob überhaupt ein Objekt erstellt wurde und ob dieses auch eine Instanz von Log ist
		Object loggerObj = Log.getLogger();
		
		assertNotNull(Log.getLogger());
		assertTrue(loggerObj instanceof Log);
	}

}

package de.Enigma;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.Enigma.Algorithm.AlgorithmTest;
import de.Enigma.Algorithm.EnigmaConfigTest;
import de.Enigma.Algorithm.MillTest;
import de.Enigma.Core.LogTest;
import de.Enigma.Util.EnumsTest;
import de.Enigma.Util.FileHandlerTest;
import de.Enigma.Util.UtilTest;


//Diese Klasse führt alle Tests nacheinander aus -> macht testen einfacher, da man nur diesen Test hier ausführen muss
@RunWith(Suite.class)
@SuiteClasses(
		{
			//Tests für das Algorithm Package
			AlgorithmTest.class, EnigmaConfigTest.class, MillTest.class,
			//Tests für das Core Package
			LogTest.class,
			//Tests für das Util Package
			EnumsTest.class, FileHandlerTest.class, UtilTest.class}
		)

public class AllTests {

}

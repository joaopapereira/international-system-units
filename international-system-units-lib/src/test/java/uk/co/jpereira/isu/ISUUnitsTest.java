package uk.co.jpereira.isu;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.Test;

public class ISUUnitsTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testRetrieveAllUnits() {
		Set<?> all = ISUUnits.retrieveAllUnits();
		System.out.println(all);
	}

}

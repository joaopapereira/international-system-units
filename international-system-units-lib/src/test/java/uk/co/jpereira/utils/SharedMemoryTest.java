package uk.co.jpereira.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.jpereira.isu.translators.MeterToKilometer;
import uk.co.jpereira.isu.units.Meter;
import uk.co.jpereira.isu.units.meter.KiloMeter;

public class SharedMemoryTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSharedMemory() {
		SharedMemory mem = new SharedMemory();
		MeterToKilometer translator = new MeterToKilometer();
		assertEquals("Bamm", translator, mem.findTranslators(Meter.class, KiloMeter.class));
	}

}

package uk.co.jpereira.isu.translators;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.jpereira.isu.units.Meter;
import uk.co.jpereira.isu.units.meter.KiloMeter;

public class MeterToKilometerTest {

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
	public void testConvertKiloMeter() {
		KiloMeter km = new KiloMeter(5);
		MeterToKilometer converter = new MeterToKilometer();
		assertEquals(5000, converter.convert(km).getAmount(), 0);
	}

	@Test
	public void testConvertMeter() {
		Meter meter = new Meter(5000);
		MeterToKilometer converter = new MeterToKilometer();
		assertEquals(5, converter.convert(meter).getAmount(), 0);
	}

}

package uk.co.jpereira.isu.translators;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.jpereira.isu.units.Meter;
import uk.co.jpereira.isu.units.meter.CentiMeter;
import uk.co.jpereira.isu.units.meter.KiloMeter;

public class MeterToCentimeterTest {

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
	public void testConvertCentiMeter() {
		Meter meter = new Meter(5);
		MeterToCentimeter converter = new MeterToCentimeter();
		assertEquals(500, converter.convert(meter).getAmount(), 0);
	}

	@Test
	public void testConvertMeter() {
		CentiMeter cm = new CentiMeter(5);
		MeterToCentimeter converter = new MeterToCentimeter();
		assertEquals(0.05, converter.convert(cm).getAmount(), 0);
	}

}

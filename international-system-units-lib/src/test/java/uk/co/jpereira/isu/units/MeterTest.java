package uk.co.jpereira.isu.units;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class MeterTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Test
	public void testBasicUnitConvertedToCentiMeters() {
		Meter m = new Meter();
		m.setAmount(10.);
		UnitModifier cm = UnitModifier.getEnum("c");
		assertEquals("Check the value", 1000, m.convertTo(cm), 0);
	}

	@Test
	public void testCentiMetersConvertedToMeters() {
		Meter cm = new Meter(UnitModifier.CENTI);
		cm.setAmount(-.8);
		assertEquals("Check the value", -.008, cm.convertTo(UnitModifier.Unit), 0);
	}
	
	@Test
	public void testYottaMeterConvertedToPetaMeters() {
		Meter m = new Meter(UnitModifier.YOTTA);
		m.setAmount(9.2);
		UnitModifier pm = UnitModifier.getEnum("P");
		assertEquals("Check the value", 9200000000., m.convertTo(pm), 0);
	}
	

	@Test
	public void testMicroMeterConvertedToCentiMeters() {
		Meter m = new Meter(UnitModifier.MICRO);
		m.setAmount(9.2);
		UnitModifier cm = UnitModifier.getEnum("c");
		assertEquals("Check the value", 0.00092, m.convertTo(cm), 0.0001);
	}

	@Test
	public void testPetaMeterConvertedToGigaMeters() {
		Meter pm = new Meter(UnitModifier.PETA);
		pm.setAmount(9.2);
		UnitModifier gm = UnitModifier.getEnum("G");
		assertEquals("Check the value", 9200000, pm.convertTo(gm), 0.0001);
	}
	

	@Test
	public void testSetAmount() {
		Meter m = new Meter();
		m.setAmount(9.2);
		assertEquals("Check ammount Meters", 9.2, m.getAmount(), 0);
		assertEquals("Check Meters", 9.2, m.getAmountToUnit(), 0);
		Meter km = new Meter(UnitModifier.KILO);
		km.setAmount(9.2);
		assertEquals("Check KiloMeters", 9.2, km.getAmount(), 0);
		assertEquals("Check Meters", 9200, km.getAmountToUnit(), 0);
		Meter cm = new Meter(UnitModifier.CENTI);
		cm.setAmount(504.);
		assertEquals("Check CentiMeters", 504, cm.getAmount(), 0);
		assertEquals("Check Meters", 5.04, cm.getAmountToUnit(), 0);
	}
}

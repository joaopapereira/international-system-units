package uk.co.jpereira.isu.units;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class KiloGramTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testBasicKg() {
		KiloGram kg = new KiloGram();
		kg.setAmount(10.);
		assertEquals("Amount set correctly", 10., kg.getAmount(), 0);
		assertEquals("Amount set in units correctly", 10000., kg.getAmountToUnit(), 0);
	}
	

	@Test
	public void testGram() {
		KiloGram g = new KiloGram(UnitModifier.Unit);
		g.setAmount(10.);
		assertEquals("Amount set correctly", 10., g.getAmount(), 0);
		assertEquals("Amount set in units correctly", 10., g.getAmountToUnit(), 0);
	}

	@Test
	public void testGram2KG() {
		KiloGram g = new KiloGram(UnitModifier.Unit);
		g.setAmount(10.);
		UnitModifier kg = UnitModifier.KILO;
		assertEquals("Check the value", 0.01, g.convertTo(kg), 0);
	}

	@Test
	public void testPg2Gg() {
		KiloGram pg = new KiloGram(UnitModifier.PETA);
		pg.setAmount(9.2);
		UnitModifier gg = UnitModifier.GIGA;
		assertEquals("Check the value", 9200000, pg.convertTo(gg), 0);
	}
}

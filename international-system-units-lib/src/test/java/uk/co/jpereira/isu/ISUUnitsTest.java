package uk.co.jpereira.isu;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.Test;

import uk.co.jpereira.isu.units.Ampere;
import uk.co.jpereira.isu.units.Meter;
import uk.co.jpereira.isu.units.UnitModifier;
import uk.co.jpereira.utils.RuleOfThree;

public class ISUUnitsTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testRetrieveAllUnits() {
	}
	@Test
	public void ruleOfThree(){
		
		Ampere am = new Ampere();
		Meter m = new Meter();
		
		double result = 
		ISUUnits.ruleOfThree(am, UnitModifier.KILO, 5, UnitModifier.GIGA, 11.5,
				              m, UnitModifier.DECI, 0.5, UnitModifier.KILO);
		assertEquals("The result should be 0.115", 115, result, .01);
	}
}

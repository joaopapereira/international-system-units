package uk.co.jpereira.isu.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RuleOfThreeTest {

	@Test(expected=IllegalArgumentException.class)
	public void testDivisionByZero() {
		RuleOfThree.calculate(0, 10, 12);
	}
	@Test
	public void testCorrectness(){
		assertEquals(0.5, RuleOfThree.calculate(100, 10, 5), 0);
		assertEquals(-330, RuleOfThree.calculate(0.5, -15, 11), 0);
	}

}

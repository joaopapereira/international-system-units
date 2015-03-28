package uk.co.jpereira.isu.translators;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.jpereira.isu.units_accepted.Liter;
import uk.co.jpereira.isu.units_accepted.liter.DecaLiter;

public class LiterToDecaLiterTest {

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
	public void testConvertDecaLiter() {
		Liter liter = new Liter(5);
		LiterToDecaLiter converter = new LiterToDecaLiter();
		assertEquals(50, converter.convert(liter).getAmount(), 0);
	}

	@Test
	public void testConvertLiter() {
		DecaLiter decaliter = new DecaLiter(5);
		LiterToDecaLiter converter = new LiterToDecaLiter();
		assertEquals(0.5, converter.convert(decaliter).getAmount(), 0);
	}
}

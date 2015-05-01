package uk.co.jpereira.isu.translators;

import org.junit.AfterClass;
import org.junit.Test;
import uk.co.jpereira.isu.exception.translator.InvalidUnit;
import uk.co.jpereira.isu.units.KiloGram;
import uk.co.jpereira.isu.units.Mole;
import uk.co.jpereira.isu.units.UnitModifier;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GramToMolTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}



	@Test
	public void testConvertGram2Mole() throws InvalidUnit {
		GramToMol converter = new GramToMol(10);
		KiloGram grams = new KiloGram(UnitModifier.Unit);
		grams.setAmount(3.);
		assertThat(converter.convert(grams), instanceOf(Mole.class));
		assertEquals("Convertion is incorrect", .3, (double)converter.convert(grams).getAmount(), 0);
	}
	@Test
	public void testConvertMole2Gram() throws InvalidUnit {
		GramToMol converter = new GramToMol(10);
		Mole mol = new Mole(UnitModifier.Unit);
		mol.setAmount(3.);
		assertThat(converter.reverse().convert(mol), instanceOf(KiloGram.class));
		assertEquals("Convertion is incorrect", 30, (double) converter.reverse().convert(mol).getAmount(), 0);
	}
}

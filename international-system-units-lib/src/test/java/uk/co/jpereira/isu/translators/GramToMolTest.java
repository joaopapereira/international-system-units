package uk.co.jpereira.isu.translators;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.AfterClass;
import org.junit.Test;

import uk.co.jpereira.isu.units.KiloGram;
import uk.co.jpereira.isu.units.Meter;
import uk.co.jpereira.isu.units.Mole;
import uk.co.jpereira.isu.units.UnitModifier;
import uk.co.jpereira.isue.exception.translator.InvalidUnit;

public class GramToMolTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testConvertInvalidUnit() {
		GramToMol converter = new GramToMol(10);
		try {
			converter.convert(new Meter());
		} catch (InvalidUnit e) {
			return;
		}catch(Exception e){
			e.printStackTrace();
		}
		fail("Exception not raised");
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
		assertThat(converter.convert(mol), instanceOf(KiloGram.class));
		assertEquals("Convertion is incorrect", 30, (double)converter.convert(mol).getAmount(), 0);
	}
}

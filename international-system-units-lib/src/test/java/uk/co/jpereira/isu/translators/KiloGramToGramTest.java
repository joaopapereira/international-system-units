package uk.co.jpereira.isu.translators;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.co.jpereira.isu.units.KiloGram;
import uk.co.jpereira.isu.units.kilogram.Gram;


public class KiloGramToGramTest {

	@Test
	public void testConvertGram() {
		KiloGram kg = new KiloGram(5);
		KiloGramToGram converter = new KiloGramToGram();
		assertEquals(5000, converter.convert(kg).getAmount(), 0);
	}

	@Test
	public void testConvertKiloGram() {
		Gram decaliter = new Gram(5);
		KiloGramToGram converter = new KiloGramToGram();
		assertEquals(0.005, converter.convert(decaliter).getAmount(), 0);
	}
}

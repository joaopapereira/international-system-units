package uk.co.jpereira.isu.translators;

import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.KiloGram;
import uk.co.jpereira.isu.units.kilogram.Gram;


@Translator
public class KiloGramToGram extends UnitTranslator<KiloGram, Gram> {

	/**
	 * Translator constructor
	 */
	public KiloGramToGram() {
		super(KiloGram.class, Gram.class);
	}

	/**
	 * Convert Grams to KiloGrams
	 * @param from Grams to be converted
	 * @return KiloGrams
	 */
	public KiloGram convert(Gram from) {
		return new KiloGram(from.getAmount()*Gram.toUnit);
	}

	/**
	 * Convert KiloGrams into Grams
	 * @param from KiloGrams to be converted
	 * @return Grams
	 */
	public Gram convert(KiloGram from) {
		return new Gram(from.getAmount()/Gram.toUnit);
	}

	

}

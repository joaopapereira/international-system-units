package uk.co.jpereira.isu.translators;

import uk.co.jpereira.isu.units.KiloGram;
import uk.co.jpereira.isu.units.kilogram.Gram;


@Translator
public class KiloGramToGram extends UnitTranslator<KiloGram, Gram> {

	public KiloGramToGram() {
		super(KiloGram.class, Gram.class);
	}

	public KiloGram convert(Gram from) {
		return new KiloGram(from.getAmount()*Gram.toUnit);
	}

	public Gram convert(KiloGram from) {
		return new Gram(from.getAmount()/Gram.toUnit);
	}

}

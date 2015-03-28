package uk.co.jpereira.isu.translators;

import uk.co.jpereira.isu.units_accepted.Liter;
import uk.co.jpereira.isu.units_accepted.liter.DecaLiter;

@Translator
public class LiterToDecaLiter extends UnitTranslator<Liter, DecaLiter> {

	public LiterToDecaLiter() {
		super(Liter.class, DecaLiter.class);
	}

	public DecaLiter convert(Liter from) {
		return new DecaLiter(from.getAmount()*DecaLiter.toUnit);
	}

	public Liter convert(DecaLiter from) {
		return new Liter(from.getAmount()/DecaLiter.toUnit);
	}

}

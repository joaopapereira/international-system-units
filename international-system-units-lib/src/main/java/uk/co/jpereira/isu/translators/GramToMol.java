package uk.co.jpereira.isu.translators;

import uk.co.jpereira.isu.units.KiloGram;
import uk.co.jpereira.isu.units.Mole;
import uk.co.jpereira.isu.units.UnitModifier;

@Translator(from = "Gram", to = "Mole")
public class GramToMol extends TranslatorBase<KiloGram, Mole> {
	private double molarMass;


	public GramToMol(double molarMass){
		this.molarMass = molarMass;
	}

	@Override
	protected Mole doForward(KiloGram kiloGram) {
		Mole result = new Mole();
		result.setAmount(kiloGram.convertTo(UnitModifier.Unit) / molarMass);
		return result;
	}

	@Override
	protected KiloGram doBackward(Mole mole) {
		KiloGram result = new KiloGram(UnitModifier.Unit);
		result.setAmount(mole.getAmount() * molarMass);
		return result;
	}
}

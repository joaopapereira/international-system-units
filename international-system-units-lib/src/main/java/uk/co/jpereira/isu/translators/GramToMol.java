package uk.co.jpereira.isu.translators;

import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.KiloGram;
import uk.co.jpereira.isu.units.Mole;
import uk.co.jpereira.isu.units.UnitModifier;
import uk.co.jpereira.isue.exception.translator.InvalidUnit;

@Translator(from = "Gram", to = "Mole")
public class GramToMol implements TranslationInterface {
	private double molarMass;
	public GramToMol(double molarMass){
		this.molarMass = molarMass;
	}
	@Override
	public ISUUnit convert(ISUUnit unit) throws InvalidUnit {
		if(unit instanceof KiloGram){
			Mole result = new Mole();
			result.setAmount((double)unit.convertTo(UnitModifier.Unit) / molarMass);
			return result;
		}else if(unit instanceof Mole){
			KiloGram result = new KiloGram(UnitModifier.Unit);
			result.setAmount((double)unit.getAmount() * molarMass);
			return result;
		}
		throw new InvalidUnit(this, unit);
	}

}

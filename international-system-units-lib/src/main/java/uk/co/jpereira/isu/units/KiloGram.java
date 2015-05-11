package uk.co.jpereira.isu.units;
/**
 * @author Joao Pereira
 * KiloGram representation class
 */
@Unit(dimension=ISDimension.MASS)
public class KiloGram extends ISUUnit<Double> {
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 * @param modifier Unit modifier
	 */
	public KiloGram(Double amount, UnitModifier modifier){
		super(amount, modifier);
	}
	/**
	 * ISUUnit constructor
	 * @param modifier Unit modifier
	 */
	public KiloGram(UnitModifier modifier){
		super(0., modifier);
	}
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 */
	public KiloGram(Double amount){
		super(amount, UnitModifier.KILO);
	}
	/**
	 * ISUUnit constructor
	 */
	public KiloGram(){
		super(0., UnitModifier.KILO);
	}

	/**
	 * Retrieve the name of the the Unit
	 * @return Name
	 */
	@Override
	public String name() {
		return new String("Gram");
	}
	/**
	 * Retrieve the abbreviated name of the unit
	 * @return Abbreviation of the name
	 */
	@Override
	public String smallName() {
		return new String("g");
	}
}

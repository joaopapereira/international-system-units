package uk.co.jpereira.isu.units;

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
	
	@Override
	public String name() {
		return new String("Gram");
	}
	@Override
	public String smallName() {
		return new String("g");
	}
}

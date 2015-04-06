package uk.co.jpereira.isu.units;

@Unit
public class KiloGram extends ISUUnit<Double> {
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
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
		super(UnitModifier.KILO);
	}
	
	@Override
	public String toString(){
		return new String("Gram");
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

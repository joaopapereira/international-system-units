package uk.co.jpereira.isu.units;

@Unit(unitType=UnitType.ELECTRIC_CURRENT)
public class Ampere extends ISUUnit<Double> {
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 * @param toUnit conversion factor
	 */
	public Ampere(Double amount, UnitModifier toUnit){
		super(amount, toUnit);
	}
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 */
	public Ampere(Double amount){
		super(amount, UnitModifier.Unit);
	}
	/**
	 * ISUUnit constructor
	 * @param toUnit conversion factor
	 */
	public Ampere(UnitModifier toUnit){
		super(0., toUnit);
	}
	/**
	 * ISUUnit constructor
	 * @param toUnit conversion factor
	 */
	public Ampere(){
		super(0., UnitModifier.Unit);
	}
	@Override
	public String name() {
		return new String("Ampere");
	}
	@Override
	public String smallName() {
		return new String("A");
	}
}

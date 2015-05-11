package uk.co.jpereira.isu.units;

@Unit(dimension=ISDimension.AMOUNT_OF_SUBSTANCE)
public class Candela extends ISUUnit<Double> {
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 * @param toUnit conversion factor
	 */
	public Candela(Double amount, UnitModifier toUnit){
		super(amount, toUnit);
	}
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 */
	public Candela(Double amount){
		super(amount, UnitModifier.Unit);
	}
	/**
	 * ISUUnit constructor
	 * @param toUnit conversion factor
	 */
	public Candela(UnitModifier toUnit){
		super(0., toUnit);
	}
	/**
	 * ISUUnit constructor
	 */
	public Candela(){
		super(0., UnitModifier.Unit);
	}
	/**
	 * Retrieve the name of the the Unit
	 * @return Name
	 */
	@Override
	public String name() {
		return new String("Candela");
	}
	/**
	 * Retrieve the abbreviated name of the unit
	 * @return Abbreviation of the name
	 */
	@Override
	public String smallName() {
		return new String("ca");
	}
}

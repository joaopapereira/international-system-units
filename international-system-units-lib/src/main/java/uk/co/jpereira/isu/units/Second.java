package uk.co.jpereira.isu.units;

@Unit(dimension=ISDimension.TIME)
public class Second extends ISUUnit<Double> {
	/**
	 * Second constructor
	 * @param amount Amount of unit
	 * @param toUnit conversion factor
	 */
	public Second(Double amount, UnitModifier toUnit){
		super(amount, toUnit);
	}
	/**
	 * Second constructor
	 * @param amount Amount of unit
	 */
	public Second(Double amount){
		super(amount, UnitModifier.Unit);
	}
	/**
	 * Second constructor
	 * @param toUnit conversion factor
	 */
	public Second(UnitModifier toUnit){
		super(0., toUnit);
	}

	/**
	 * Default constructor
	 */
	public Second(){
		super(0., UnitModifier.Unit);
	}
	/**
	 * Retrieve the name of the the Unit
	 * @return Name
	 */
	@Override
	public String name() {
		return new String("Second");
	}
	/**
	 * Retrieve the abbreviated name of the unit
	 * @return Abbreviation of the name
	 */
	@Override
	public String smallName() {
		return new String("s");
	}
}

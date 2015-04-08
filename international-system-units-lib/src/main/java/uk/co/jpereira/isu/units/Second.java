package uk.co.jpereira.isu.units;

@Unit(dimension=ISDimension.TIME)
public class Second extends ISUUnit<Double> {
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 * @param toUnit conversion factor
	 */
	public Second(Double amount, UnitModifier toUnit){
		super(amount, toUnit);
	}
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 */
	public Second(Double amount){
		super(amount, UnitModifier.Unit);
	}
	/**
	 * ISUUnit constructor
	 * @param toUnit conversion factor
	 */
	public Second(UnitModifier toUnit){
		super(0., toUnit);
	}
	/**
	 * ISUUnit constructor
	 * @param toUnit conversion factor
	 */
	public Second(){
		super(0., UnitModifier.Unit);
	}
	@Override
	public String name() {
		return new String("Second");
	}
	@Override
	public String smallName() {
		return new String("s");
	}
}

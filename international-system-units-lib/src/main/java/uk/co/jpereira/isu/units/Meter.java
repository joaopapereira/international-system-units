package uk.co.jpereira.isu.units;

@Unit
public class Meter extends ISUUnit<Double> {
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 * @param toUnit conversion factor
	 */
	public Meter(Double amount, UnitModifier toUnit){
		super(amount, toUnit);
	}
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 */
	public Meter(Double amount){
		super(amount, UnitModifier.Unit);
	}
	/**
	 * ISUUnit constructor
	 * @param toUnit conversion factor
	 */
	public Meter(UnitModifier toUnit){
		super(0., toUnit);
	}
	/**
	 * ISUUnit constructor
	 * @param toUnit conversion factor
	 */
	public Meter(){
		super(0., UnitModifier.Unit);
	}
	@Override
	public String name() {
		return new String("Meter");
	}
	@Override
	public String smallName() {
		return new String("m");
	}
}

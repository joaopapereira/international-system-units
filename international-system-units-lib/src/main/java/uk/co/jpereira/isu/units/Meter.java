package uk.co.jpereira.isu.units;

/**
 * @author Joao Pereira
 * Meter representation class
 */
@Unit(dimension=ISDimension.LENGTH)
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
	 */
	public Meter(){
		super(0., UnitModifier.Unit);
	}
	/**
	 * Retrieve the name of the the Unit
	 * @return Name
	 */
	@Override
	public String name() {
		return new String("Meter");
	}
	/**
	 * Retrieve the abbreviated name of the unit
	 * @return Abbreviation of the name
	 */
	@Override
	public String smallName() {
		return new String("m");
	}
}

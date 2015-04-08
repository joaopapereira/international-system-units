package uk.co.jpereira.isu.units;

@Unit(dimension=ISDimension.LUMINOUS_INTENSITY)
public class Mole extends ISUUnit<Double> {
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 * @param toUnit conversion factor
	 */
	public Mole(Double amount, UnitModifier toUnit){
		super(amount, toUnit);
	}
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 */
	public Mole(Double amount){
		super(amount, UnitModifier.Unit);
	}
	/**
	 * ISUUnit constructor
	 * @param toUnit conversion factor
	 */
	public Mole(UnitModifier toUnit){
		super(0., toUnit);
	}
	/**
	 * ISUUnit constructor
	 * @param toUnit conversion factor
	 */
	public Mole(){
		super(0., UnitModifier.Unit);
	}
	@Override
	public String name() {
		return new String("Mole");
	}
	@Override
	public String smallName() {
		return new String("mol");
	}
}

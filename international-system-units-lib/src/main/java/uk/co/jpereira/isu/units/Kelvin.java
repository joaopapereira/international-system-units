package uk.co.jpereira.isu.units;
/**
 * @author Joao Pereira
 * Kelvin Class
 */
@Unit(dimension=ISDimension.THERMODYNAMIC_TEMPERATURE)
public class Kelvin extends ISUUnit<Double> {
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 * @param toUnit conversion factor
	 */
	public Kelvin(Double amount, UnitModifier toUnit){
		super(amount, toUnit);
	}
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 */
	public Kelvin(Double amount){
		super(amount, UnitModifier.Unit);
	}
	/**
	 * ISUUnit constructor
	 * @param toUnit conversion factor
	 */
	public Kelvin(UnitModifier toUnit){
		super(0., toUnit);
	}
	/**
	 * ISUUnit constructor
	 */
	public Kelvin(){
		super(0., UnitModifier.Unit);
	}
	/**
	 * Retrieve the name of the the Unit
	 * @return Name
	 */
	@Override
	public String name() {
		return new String("Kelvin");
	}
	/**
	 * Retrieve the abbreviated name of the unit
	 * @return Abbreviation of the name
	 */
	@Override
	public String smallName() {
		return new String("K");
	}
}

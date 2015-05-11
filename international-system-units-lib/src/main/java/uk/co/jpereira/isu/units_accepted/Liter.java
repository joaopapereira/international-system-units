package uk.co.jpereira.isu.units_accepted;

import uk.co.jpereira.isu.units.ISDimension;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.Unit;
import uk.co.jpereira.isu.units.UnitModifier;

@Unit(dimension = ISDimension.VOLUME)
public class Liter extends ISUUnit<Double> {
	/**
	 * Liter constructor
	 * @param amount Amount of unit
	 * @param toUnit conversion factor
	 */
	public Liter(Double amount, UnitModifier toUnit){
		super(amount, toUnit);
	}
	/**
	 * Liter constructor
	 * @param amount Amount of unit
	 */
	public Liter(Double amount){
		super(amount, UnitModifier.Unit);
	}
	/**
	 * Liter constructor
	 * @param toUnit conversion factor
	 */
	public Liter(UnitModifier toUnit){
		super(0., toUnit);
	}
	/**
	 * Liter default constructor
	 */
	public Liter(){
		super(0., UnitModifier.Unit);
	}
	/**
	 * Retrieve the name of the the Unit
	 * @return Name
	 */
	@Override
	public String name() {
		return new String("Liter");
	}
	/**
	 * Retrieve the abbreviated name of the unit
	 * @return Abbreviation of the name
	 */
	@Override
	public String smallName() {
		return new String("l");
	}
}

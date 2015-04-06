package uk.co.jpereira.isu.units_accepted;

import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.Unit;
import uk.co.jpereira.isu.units.UnitModifier;

@Unit
public class Liter extends ISUUnit<Double> {
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 * @param toUnit conversion factor
	 */
	public Liter(Double amount, UnitModifier toUnit){
		super(amount, toUnit);
	}
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 */
	public Liter(Double amount){
		super(amount, UnitModifier.Unit);
	}
	/**
	 * ISUUnit constructor
	 * @param toUnit conversion factor
	 */
	public Liter(UnitModifier toUnit){
		super(0., toUnit);
	}
	/**
	 * ISUUnit constructor
	 * @param toUnit conversion factor
	 */
	public Liter(){
		super(0., UnitModifier.Unit);
	}
	@Override
	public String name() {
		return new String("Liter");
	}
	@Override
	public String smallName() {
		return new String("l");
	}
}

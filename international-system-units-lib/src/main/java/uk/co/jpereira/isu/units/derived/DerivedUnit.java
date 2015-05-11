package uk.co.jpereira.isu.units.derived;

import uk.co.jpereira.isu.units.BasicUnit;
/**
 * @author Joao Pereira
 * Parent class of all the derived Units
 */
public abstract class DerivedUnit extends BasicUnit<Double>{
	/**
	 * Class constructor
	 */
	public DerivedUnit(){
		super(0.);
	}

	/**
	 * Class constructor
	 * @param amount Amount of the Unit
	 */
	public DerivedUnit(double amount){
		super(amount);
	}

}

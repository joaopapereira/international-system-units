package uk.co.jpereira.isu.units.derived;

import uk.co.jpereira.isu.units.BasicUnit;

public abstract class DerivedUnit extends BasicUnit<Double>{
	public DerivedUnit(){
		super(0.);
	}
	public DerivedUnit(double amount){
		super(amount);
	}

}

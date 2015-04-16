package uk.co.jpereira.isu.units.derived;

import uk.co.jpereira.isu.units.BasicUnit;
import uk.co.jpereira.isue.exception.MissingParameters;

public abstract class DerivedUnit extends BasicUnit<Double>{
	public DerivedUnit(){
		super(0.);
	}
	public DerivedUnit(double amount){
		super(amount);
	}

	public abstract double calculateUnit() throws MissingParameters;
	public abstract String calculationFormulae();
}

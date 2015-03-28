package uk.co.jpereira.isu.units_accepted;

import uk.co.jpereira.isu.units.ISUUnit;

public class Liter extends ISUUnit {

	public Liter(double amount){
		super(amount, 1);
	}
	protected Liter(double amount, double toUnit){
		super(amount, 1);
	}
	@Override
	public String toString(){
		return new String("Liter");
	}
}

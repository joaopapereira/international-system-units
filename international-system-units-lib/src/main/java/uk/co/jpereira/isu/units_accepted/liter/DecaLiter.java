package uk.co.jpereira.isu.units_accepted.liter;

import uk.co.jpereira.isu.units_accepted.Liter;

public class DecaLiter extends Liter {
	public final static double toUnit = 10;
	public DecaLiter(){
		super(0, toUnit);
	}
	public DecaLiter(double amount){
		super(amount, toUnit);
	}
	@Override
	public String toString(){
		return new String("DecaLiter");
	}
}

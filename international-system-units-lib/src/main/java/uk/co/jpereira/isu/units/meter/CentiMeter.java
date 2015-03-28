package uk.co.jpereira.isu.units.meter;

import uk.co.jpereira.isu.units.Meter;

public class CentiMeter extends Meter {
	public final static double toUnit = 0.01;
	public CentiMeter() {
		super(0, toUnit);
	}
	public CentiMeter(double amount) {
		super(amount, toUnit);
	}
	@Override
	public String toString(){
		return new String("CentiMeter");
	}
}

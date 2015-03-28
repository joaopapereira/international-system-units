package uk.co.jpereira.isu.units.meter;

import uk.co.jpereira.isu.units.Meter;

public class KiloMeter extends Meter {
	public final static double toUnit = 1000;
	public KiloMeter(double amount) {
		super(amount, toUnit);
	}
}

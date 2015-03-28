package uk.co.jpereira.isu.units.kilogram;

import uk.co.jpereira.isu.units.KiloGram;

public class Gram extends KiloGram {
	public final static double toUnit = 0.001;
	public Gram() {
		super(0, toUnit);
	}
	public Gram(double amount) {
		super(amount, toUnit);
	}
	@Override
	public String toString(){
		return new String("Gram");
	}
}

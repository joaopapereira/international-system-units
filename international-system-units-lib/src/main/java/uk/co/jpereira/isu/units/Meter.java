package uk.co.jpereira.isu.units;

public class Meter extends ISUUnit {
	public Meter(){
		super(0, 1);
	}
	public Meter(double amount){
		super(amount, 1);
	}
	protected Meter(double amount, double toUnit){
		super(amount, 1);
	}
	@Override
	public String toString(){
		return new String("Meter");
	}
}

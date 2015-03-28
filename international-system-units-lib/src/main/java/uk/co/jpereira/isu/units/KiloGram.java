package uk.co.jpereira.isu.units;

public class KiloGram extends ISUUnit {
	public KiloGram(double amount){
		super(amount, 1);
	}
	protected KiloGram(double amount, double toUnit){
		super(amount, 1);
	}
	@Override
	public String toString(){
		return new String("KiloGram");
	}
}

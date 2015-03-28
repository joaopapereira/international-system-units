package uk.co.jpereira.isu.units;

public class ISUUnit {
	private double amount;
	private double toUnit;
	public ISUUnit(double amount, double toUnit){
		this.amount = amount;
		this.toUnit = toUnit;
	}
	public ISUUnit(){
		amount = 0;
	}
	public double getAmount(){
		return amount;
	}
	public void setAmount(double amount){
		this.amount = amount;
	}
	
	public double getAmountToUnit(){
		return amount*toUnit;
	}

	@Override
	public String toString(){
		return new String("ISUUnit");
	}
}

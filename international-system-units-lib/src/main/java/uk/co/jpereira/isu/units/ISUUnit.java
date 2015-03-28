package uk.co.jpereira.isu.units;

/**
 * @author Joao Pereira
 *
 */
public class ISUUnit {
	private double amount;
	private double toUnit;
	/**
	 * ISUUnit constructor
	 * @param amount Amount of units
	 * @param toUnit conversion factor
	 */
	public ISUUnit(double amount, double toUnit){
		this.amount = amount;
		this.toUnit = toUnit;
	}
	/**
	 * ISUUnit constructor
	 */
	public ISUUnit(){
		amount = 0;
	}
	
	/**
	 * Retrieve the amount of units in this object unit
	 * @return amount of units
	 */
	public double getAmount(){
		return amount;
	}

	/**
	 * Set the amount of units in this object unit
	 * @return amount of units
	 */
	public void setAmount(double amount){
		this.amount = amount;
	}

	/**
	 * Retrieve the amount of units in the ISU Unit
	 * @return amount of units
	 */
	public double getAmountToUnit(){
		return amount*toUnit;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return new String("ISUUnit");
	}
}

package uk.co.jpereira.isu.units;

import java.util.logging.Logger;

import uk.co.jpereira.isu.ISUUnits;
import uk.co.jpereira.isue.exception.MissingParameters;

public abstract class BasicUnit<Precision>  implements Comparable<BasicUnit<Precision>>{
	protected final Logger logger; 
	private Precision amountInUnits;
	/**
	 * BasicUnit constructor
	 * @param amount Amount of unit
	 */
	public BasicUnit(Precision amount){
		logger = Logger.getLogger(this.getClass().getPackage().getName());
		setAmount(amount);
	}
	/**
	 * BasicUnit constructor
	 */
	public BasicUnit(){
		logger = Logger.getLogger(this.getClass().getPackage().getName());
	}

	/**
	 * Set the amount of units in this object unit
	 * @return amount of units
	 */
	@SuppressWarnings("unchecked")
	public void setAmount(Precision amount){
		this.amountInUnits = amount;
	}
	/**
	 * Retrieve the amount of units in the ISU Unit
	 * @return amount of units
	 */
	public abstract Precision getAmount() throws MissingParameters ;
	/**
	 * Retrieve the amount of units in the ISU Unit
	 * @return amount of units
	 */
	public Precision getAmountToUnit(){
		return amountInUnits;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return new String(name());
	}

	public String getSmallName(){
		return new String(smallName());
	}
	public abstract String name();
	public abstract String smallName();

	/**
	 * Compare to units using the names
	 * @param otherUnit Other unit to compare to
	 */
	public int compareTo(BasicUnit otherUnit){
		return name().compareTo(otherUnit.name());
	}
	public Object clone(){
		try {
			BasicUnit unit = (BasicUnit)this.getClass().newInstance();
			unit.amountInUnits = amountInUnits;
			return unit;
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}

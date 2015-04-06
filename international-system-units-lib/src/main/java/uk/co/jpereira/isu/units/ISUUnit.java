package uk.co.jpereira.isu.units;

/**
 * @author Joao Pereira
 *
 */
@SuppressWarnings("rawtypes")
public abstract class ISUUnit<Precision> implements Comparable<ISUUnit<Precision>>{
	private Precision amountInUnits;
	private UnitModifier modififerFromUnit;
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 * @param toUnit conversion factor
	 */
	public ISUUnit(Precision amount, UnitModifier toUnit){
		if(toUnit == null)
			this.modififerFromUnit = UnitModifier.Unit;
		else
			this.modififerFromUnit = toUnit;
		setAmount(amount);
	}
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 */
	public ISUUnit(Precision amount){
		this.modififerFromUnit = UnitModifier.Unit;
		setAmount(amount);
	}
	/**
	 * ISUUnit constructor
	 * @param toUnit conversion factor
	 */
	public ISUUnit(UnitModifier toUnit){
		if(toUnit == null)
			this.modififerFromUnit = UnitModifier.Unit;
		else
			this.modififerFromUnit = toUnit;
	}
	/**
	 * ISUUnit constructor
	 */
	public ISUUnit(){
		this.modififerFromUnit = UnitModifier.Unit;
	}
	
	/**
	 * Retrieve the amount of units in this object unit
	 * @return amount of units
	 */
	@SuppressWarnings("unchecked")
	public Precision getAmount(){
		return (Precision)modififerFromUnit.convert((Double)amountInUnits);
	}

	/**
	 * Set the amount of units in this object unit
	 * @return amount of units
	 */
	@SuppressWarnings("unchecked")
	public void setAmount(Precision amount){
		if(modififerFromUnit == UnitModifier.Unit)
			this.amountInUnits = amount;
		else
			this.amountInUnits = (Precision)modififerFromUnit.convertToUnit((Double)amount);
	}
	public void setModifier(UnitModifier modifier){
		modififerFromUnit = modifier;
	}
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
		return new String(modififerFromUnit.toString()+name());
	}
	public abstract String name();
	public abstract String smallName();
	
	@SuppressWarnings("unchecked")
	public Precision convertTo(UnitModifier modifier){
		if(modififerFromUnit == UnitModifier.Unit){
			return (Precision)modifier.convert((Double)amountInUnits);
		}else if(modifier == UnitModifier.Unit){
			return getAmountToUnit();
		}
		Precision aux = getAmountToUnit();
		return (Precision)modifier.convert((Double)aux);
	}
	/**
	 * Compare to units using the names
	 * @param otherUnit Other unit to compare to
	 */
	public int compareTo(ISUUnit otherUnit){
		return otherUnit.name().compareTo(otherUnit.name());
	}
}

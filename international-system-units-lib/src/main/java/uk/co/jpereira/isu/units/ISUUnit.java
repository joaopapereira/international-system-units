package uk.co.jpereira.isu.units;

import org.json.simple.JSONObject;
import uk.co.jpereira.isu.exception.MissingParameters;

/**
 * @author Joao Pereira
 *
 */
@SuppressWarnings("rawtypes")
public abstract class ISUUnit<Precision> extends BasicUnit<Precision>{
	private UnitModifier modififerFromUnit;
	/**
	 * ISUUnit constructor
	 * @param amount Amount of unit
	 * @param toUnit conversion factor
	 */
	public ISUUnit(Precision amount, UnitModifier toUnit){
		super();
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
		super();
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
	@Override
	@SuppressWarnings("unchecked")
	public Precision getAmount(){
		if (super.getAmountToUnit() == null)
			return null;
		return (Precision)modififerFromUnit.convert((Double)super.getAmountToUnit());
	}


	/**
	 * Set the amount of units in this object unit
	 * @param amount Amount to be set
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void setAmount(Precision amount){
		if (modififerFromUnit == UnitModifier.Unit || amount == null)
			super.setAmount(amount);
		else
			super.setAmount((Precision)modififerFromUnit.convertToUnit((Double)amount));
	}
	public void setModifier(UnitModifier modifier){
		modififerFromUnit = modifier;
	}

	public UnitModifier getModifier() {
		return modififerFromUnit;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return new String(modififerFromUnit.toString()+name());
	}

	public String getSmallName(){
		return getSmallName(true);
	}

	public String getSmallName(boolean addModifier) {
		if (addModifier)
			return new String(modififerFromUnit.getSmallRepr() + smallName());
		return new String(smallName());
	}

	/**
	 * Retrieve the small name
	 *
	 * @return Small name
	 */
	public String getSmallNameMathML() {
		return new String("<mtext>" + getSmallName() + "</mtext>");
	}

	/**
	 * Function will convert the current unit using the modifier
	 * @param modifier Modifier that will be used to convert the current unit
	 * @return The amount already converted to the modifier
	 */
	@SuppressWarnings("unchecked")
	public Precision convertTo(UnitModifier modifier){
		if(modififerFromUnit == UnitModifier.Unit){
			return (Precision)modifier.convert((Double)super.getAmountToUnit());
		}else if(modifier == UnitModifier.Unit){
			return getAmountToUnit();
		}
		Precision aux = getAmountToUnit();
		return (Precision)modifier.convert((Double)aux);
	}
	/**
	 * Compare to units using the names
	 * @param otherUnit Other unit to compare to
	 * @return 0 If objects are equal
	 *         Bigger 0 If otherUnit is bigger then current object
	 *         Smaller 0 If current object is bigger then otherUnit
	 */
	public int compareTo(ISUUnit otherUnit){
		return name().compareTo(otherUnit.name());
	}
	@Override
	public Object clone(){
		ISUUnit unit = (ISUUnit)super.clone();
		unit.modififerFromUnit = modififerFromUnit;
		return unit;
	}

	/**
	 * Retrieve JSON representation of the Unit
	 *
	 * @return JSON Representation
	 */
	@Override
	@SuppressWarnings("unchecked")
	public JSONObject getRepresentation() {
		JSONObject obj = super.getRepresentation();
		obj.put("unit_mod", modififerFromUnit);
		return obj;
	}

	/**
	 * Retrieve JSON representation of the Unit
	 * @param object JSON Object that represents this unit
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void loadFromRepresentation(JSONObject object) {
		modififerFromUnit = (UnitModifier) object.get("unit_mod");
		super.loadFromRepresentation(object);
	}

	/**
	 * Function that will solve the formulae
	 * @return The result
	 * @throws MissingParameters If not enough parameters are present
	 */
	@Override
	public double solve() throws MissingParameters {
		return 0;
	}
}

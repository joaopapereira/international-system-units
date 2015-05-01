package uk.co.jpereira.isu.units;

import org.json.simple.JSONObject;
import uk.co.jpereira.isu.ISURepresentable;
import uk.co.jpereira.isu.exception.MissingParameters;

import java.rmi.server.UID;
import java.util.logging.Logger;

public abstract class BasicUnit<Precision> implements ISURepresentable<Precision>, Comparable<BasicUnit<Precision>> {
	protected final static UID classId = new UID();
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

	/**
	 * Retrieve the Unit Identifier
	 *
	 * @return Unit Identifier
	 */
	public UID uid() {
		return classId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return new String(name());
	}

	/**
	 * Retrieve the small name
	 *
	 * @return Small name
	 */
	public String getSmallName(){
		return new String(smallName());
	}

	/**
	 * Retrieve the small name
	 *
	 * @return Small name
	 */
	public String getSmallNameMathML() {
		return new String("<mtext>" + smallName() + "</mtext>");
	}

	/**
	 * Retrieve the name of the the Unit
	 * @return Name
	 */
	public abstract String name();

	/**
	 * Retrieve JSON representation of the Unit
	 *
	 * @return JSON Representation
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getRepresentation() {
		JSONObject obj = new JSONObject();
		obj.put("class", getClass());
		try {
			obj.put("amount", getAmount());
		} catch (MissingParameters e) {
			obj.put("amount", null);
		}
		return obj;
	}

	/**
	 * Retrieve JSON representation of the Unit
	 *
	 * @return JSON Representation
	 */
	public void loadFromRepresentation(JSONObject object) {
		setAmount((Precision) object.get("amount"));
	}

	/**
	 * Retrieve the abbreviated name of the unit
	 * @return
	 */
	public abstract String smallName();

	/**
	 * Retrieve the value and the
	 */
	public String getValueWithUnit() {
		try {
			return new String(getAmount() + "(" + smallName() + ")");
		} catch (MissingParameters e) {
			return new String("(" + smallName() + ")");
		}
	}

	/**
	 * Retrieve the value and the
	 */
	public String toMathML() {

		try {
			return new String("<mtext>" + getAmount() + " " + getSmallNameMathML() + "</mtext>");
		} catch (MissingParameters e) {
			return new String("<mtext>" + getSmallNameMathML() + "</mtext>");
		}
	}

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

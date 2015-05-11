package uk.co.jpereira.isu.units.derived;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import uk.co.jpereira.isu.exception.MissingParameters;
import uk.co.jpereira.isu.units.ISDimension;
import uk.co.jpereira.isu.units.KiloGram;
import uk.co.jpereira.isu.units.Mole;
import uk.co.jpereira.isu.units.Unit;

@Unit(dimension=ISDimension.COMPOSED)
public class MolarMass extends DerivedUnit{
	private KiloGram mass;
	private Mole mole;

	/**
	 * MolarMass constructor
	 */
	public MolarMass(){
		super(Double.NaN);
		mass = null;
		mole = null;
	}

	/**
	 * MolarMass constructor
	 * @param mass Mass of the Substance
	 * @param mole Amount of the Substance
	 */
	public MolarMass(KiloGram mass, Mole mole){
		super(Double.NaN);
		this.mass = mass;
		this.mole = mole;
	}

	/**
	 * MolarMass constructor
	 * @param mass Mass of the substance
	 */
	public MolarMass(KiloGram mass){
		super(Double.NaN);
		this.mass = mass;
		this.mole = null;
	}

	/**
	 * MolarMass constructor
	 * @param mole Amount of Substance
	 */
	public MolarMass(Mole mole){
		super(Double.NaN);
		this.mass = null;
		this.mole = mole;
	}
	@Override
	public String name() {
		if(mass == null || mole == null)
			return new String("MolarMass");
		return mass.name()+ "/" + mole.name();
	}

	@Override
	public String smallName() {
		if(mass == null || mole == null)
			return new String("MolarMass");
		return mass.getSmallName()+ "/" + mole.smallName();
	}
	@Override
	public double solve() throws MissingParameters {
		double result = Double.NaN;
		Double currentAmount = getAmountToUnit();
		if (currentAmount != null && !currentAmount.isNaN() && mass != null && mole != null){
			logger.finest("No need to calculate, values present!");
			return getAmountToUnit();
		} else if ((currentAmount == null || currentAmount.isNaN()) && mass != null && mole != null){
			logger.finest("Amount was missing!");
			setAmount(mass.getAmount() / mole.getAmount());
			result = getAmountToUnit();
		} else if (currentAmount != null && !getAmountToUnit().isNaN()){
			if(mass != null){
				logger.finest("Mole was missing!");
				mole = new Mole(mass.getAmount() / getAmountToUnit());
				result = mole.getAmount();
			}else if(mole != null){
				logger.finest("Mass was missing!");
				mass = new KiloGram(getAmountToUnit() * mole.getAmount());
				result = mass.getAmount();
			}
		}
		logger.finest("Current result is: "+ result);
		if(new Double(result).isNaN()){
			logger.finest("No calcules made...");
			if(mass == null)
				throw new MissingParameters("Mass");
			else if(mole == null)
				throw new MissingParameters("Mole");
		}
		return result;
	}
	public KiloGram getMass() throws MissingParameters {
		if(mass == null){
			solve();
		}
		return mass;
	}
	public void setMass(KiloGram mass) {
		this.mass = mass;
	}
	public Mole getMole() throws MissingParameters {
		if(mole == null){
			solve();
		}
		return mole;
	}
	public void setMole(Mole mole) {
		this.mole = mole;
	}

	@Override
	public Object clone() {
		MolarMass newObject = (MolarMass) super.clone();
		if (mole == null)
			newObject.mole = null;
		else
			newObject.mole = (Mole) mole.clone();
		if (mass == null)
			newObject.mass = null;
		else
			newObject.mass = (KiloGram) mass.clone();
		return newObject;
	}

	@Override
	public Double getAmount() throws MissingParameters {
		if (getAmountToUnit() == null)
			return null;
		//if(getAmountToUnit().isNaN()){
		solve();
		//}
		return getAmountToUnit();
	}

	/**
	 * Retrieve JSON representation of the Unit
	 *
	 * @return JSON Representation
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getRepresentation() {
		JSONObject obj = super.getRepresentation();
		JSONArray list = new JSONArray();
		KiloGram m = mass;
		Mole mo = mole;
		if (m == null)
			m = new KiloGram();
		if (mo == null)
			mo = new Mole();
		list.add(m.getRepresentation());
		list.add(mo.getRepresentation());
		obj.put("subunits", list);
		return obj;
	}

	/**
	 * Retrieve JSON representation of the Unit
	 * @param object JSON Object that represents this unit
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void loadFromRepresentation(JSONObject object) {
		super.loadFromRepresentation(object);
		for (Object obj : (JSONArray) object.get(SUBUNITS)) {
			JSONObject _obj = (JSONObject) obj;
			System.out.println(_obj);
			if (KiloGram.class == _obj.get(CLASS)) {
				mass = new KiloGram();
				mass.loadFromRepresentation(_obj);
				if (mass.getAmount() == null)
					mass = null;
			} else if (Mole.class == _obj.get(CLASS)) {
				mole = new Mole();
				mole.loadFromRepresentation(_obj);
				if (mole.getAmount() == null)
					mole = null;
			}
		}
	}
	@Override
	public String toMathML() {
		String molarMass, mass, mole;
		molarMass = getValueWithUnit();
		mass = "Mass";
		mole = "Mole";
		if (this.mass != null)
			mass = this.mass.getValueWithUnit();
		if (this.mole != null)
			mole = this.mole.getValueWithUnit();
		String formulae = new String();
		formulae = "<mrow>                " +
				"  <mtext>" + molarMass + "</mtext> " +
					"  <mo>=</mo>         "+
					"  <mfrac>            " +
				"    <mrow>           " +
				"      <mrow>         " +
				"        <mtext>" + mass + "</mtext>" +
					"      </mrow>        " +
				"    </mrow>          " +
				"    <mrow>           " +
				"      <mtext>" + mole + "</mtext>  " +
					"    </mrow>          "+
					"  </mfrac>           "+
					"</mrow>";

		return formulae;
	}

	/**
	 * Compare to units using the names
	 *
	 * @param otherUnit Other unit to compare to
	 * @return 0 If objects are equal
	 *         Bigger 0 If otherUnit is bigger then current object
	 *         Smaller 0 If current object is bigger then otherUnit
	 * @throws MissingParameters If not enough parameters are present
	 */
	public int compareTo(MolarMass otherUnit) throws MissingParameters {
		int res1 = name().compareTo(otherUnit.name());
		if (res1 == 0)
			return Double.compare(getAmount(), otherUnit.getAmount());
		return res1;
	}

	public boolean equals(MolarMass otherUnit) throws MissingParameters {
		return 0 == compareTo(otherUnit);
	}
}

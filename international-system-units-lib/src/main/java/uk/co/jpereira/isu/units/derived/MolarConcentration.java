package uk.co.jpereira.isu.units.derived;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import uk.co.jpereira.isu.exception.MissingParameters;
import uk.co.jpereira.isu.units.ISDimension;
import uk.co.jpereira.isu.units.Mole;
import uk.co.jpereira.isu.units.Unit;
import uk.co.jpereira.isu.units_accepted.Liter;

/**
 * Created by blue on 01/05/2015.
 */
@Unit(dimension = ISDimension.COMPOSED)
public class MolarConcentration extends DerivedUnit {
    Mole mole;
    Liter liter;

    /**
     * Default constructor
     */
    public MolarConcentration() {
        super(Double.NaN);
        mole = null;
        liter = null;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (liter == null || mole == null)
            return new String("MolarConcentration");
        return mole.name() + "/" + liter.name();
    }

    /**
     * Retrieve the name of the the Unit
     *
     * @return Name
     */
    @Override
    public String name() {
        if (liter == null || mole == null)
            return new String("MolarConcentration");
        return liter.name() + "/" + mole.name();
    }

    /**
     * Function that will solve the formulae
     * @return The result
     * @throws MissingParameters If not enough parameters are present
     */
    @Override
    public double solve() throws MissingParameters {
        double result = Double.NaN;
        Double currentAmount = getAmountToUnit();
        if (currentAmount != null && !currentAmount.isNaN() && liter != null && mole != null) {
            logger.finest("No need to calculate, values present!");
            return getAmountToUnit();
        } else if ((currentAmount == null || currentAmount.isNaN()) && liter != null && mole != null) {
            logger.finest("Amount was missing!");
            setAmount(mole.getAmount() / liter.getAmountToUnit());
            result = getAmountToUnit();
        } else if (currentAmount != null && !getAmountToUnit().isNaN()) {
            if (liter != null) {
                logger.finest("Mole was missing!");
                mole = new Mole(getAmountToUnit() * liter.getAmountToUnit());
                result = mole.getAmount();
            } else if (mole != null) {
                logger.finest("Liter was missing!");
                liter = new Liter(mole.getAmount() / getAmountToUnit());
                result = liter.getAmount();
            }
        }
        logger.finest("Current result is: " + result);
        if (new Double(result).isNaN()) {
            logger.finest("No calcules made...");
            if (liter == null)
                throw new MissingParameters("Liter");
            else if (mole == null)
                throw new MissingParameters("Mole");
        }
        return result;
    }


    /**
     * Retrieve JSON representation of the Unit
     *
     * @return JSON Representation
     */
    @Override
    public JSONObject getRepresentation() {
        JSONObject obj = super.getRepresentation();
        Liter l = liter;
        Mole mo = mole;
        if (l == null)
            l = new Liter();
        if (mo == null)
            mo = new Mole();
        obj = addSubUnit(obj, mo.getRepresentation());
        obj = addSubUnit(obj, l.getRepresentation());
        return obj;
    }

    /**
     * Function will load the object with the help of the
     * JSON representation
     *
     * @param object JSON Representaion of the object
     */
    @Override
    public void loadFromRepresentation(JSONObject object) {
        super.loadFromRepresentation(object);
        JSONArray subunits = getSubUnits(object);
        if (subunits == null) {
            mole = null;
            liter = null;
        }
        for (Object subobject : subunits) {
            JSONObject subunit = (JSONObject) subobject;
            if (Mole.class == subunit.get(CLASS)) {
                mole = new Mole();
                mole.loadFromRepresentation(subunit);
                if (mole.getAmount() == null)
                    mole = null;
            } else if (Liter.class == subunit.get(CLASS)) {
                liter = new Liter();
                liter.loadFromRepresentation(subunit);
                if (liter.getAmount() == null)
                    liter = null;
            }
        }
    }

    /**
     * Retrieve the abbreviated name of the unit
     * @return Abbreviation of the name
     */
    @Override
    public String smallName() {
        if (liter == null || mole == null)
            return new String("MolarMass");
        return mole.smallName() + "/" + liter.getSmallName(false);
    }

    /**
     * Function that will convert to MathML the expression
     *
     * @return MathML String format
     */
    @Override
    public String toMathML() {
        String result;
        try {
            solve();
        } catch (MissingParameters missingParameters) {
        }
        String moleName = "<mtext>Mole</mtext>";
        if (mole != null)
            moleName = mole.toMathML();
        String literName = "<mtext>Liter</mtext>";
        if (liter != null)
            literName = liter.toMathML();
        result = "<mrow>" +
                " <mn>" + getValueWithUnit() + "</mn>" +
                " <mo>=</mo>" +
                " <mfrac>" +
                "  <mrow>" +
                moleName +
                "  </mrow>" +
                "  <mrow>" +
                literName +
                "  </mrow>" +
                " </mfrac>" +
                "</mrow>";
        return result;
    }

    /**
     * Retrieve the amount of units in the ISU Unit
     *
     * @return amount of units
     */
    @Override
    public Double getAmount() throws MissingParameters {
        solve();
        return getAmountToUnit();
    }

    /**
     * Retrieve the volume in the concentration
     * @return Liters in concentration
     * @throws MissingParameters When do not have enough parameters to calculate
     */
    public Liter getVolume() throws MissingParameters {
        if (liter == null) {
            solve();
        }
        return liter;
    }

    /**
     * Set the volume in Liters
     * @param liter Amount of liters in the concentration
     */
    public void setVolume(Liter liter) {
        this.liter = liter;
    }

    /**
     * Retrieve the amount of moles in the concentration
     * @return Moles in the concentration
     * @throws MissingParameters When do not have enough parameters to calculate
     */
    public Mole getMole() throws MissingParameters {
        if (mole == null) {
            solve();
        }
        return mole;
    }

    /**
     * Set mole
     * @param mole Moles in the concentration
     */
    public void setMole(Mole mole) {
        this.mole = mole;
    }

    /**
     * Set Liter
     *
     * @param liter Liters in the concentration
     */
    public void setLiter(Liter liter) {
        this.liter = liter;
    }

    /**
     * Compare to units using the names
     * @param otherUnit Other unit to compare to
     * @return 0 If objects are equal
     *         Bigger 0 If otherUnit is bigger then current object
     *         Smaller 0 If current object is bigger then otherUnit
     * @throws MissingParameters If not enough parameters are present
     */
    public int compareTo(MolarConcentration otherUnit) throws MissingParameters {
        int res1 = toString().compareTo(otherUnit.toString());
        if (res1 == 0) {
            return Double.compare(getAmount(), otherUnit.getAmount());
        }
        return res1;
    }

    public boolean equals(MolarConcentration otherUnit) throws MissingParameters {
        return 0 == compareTo(otherUnit);
    }
}

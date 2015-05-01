package uk.co.jpereira.isu.chemistry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import uk.co.jpereira.isu.Formulae;
import uk.co.jpereira.isu.exception.MissingParameters;
import uk.co.jpereira.isu.units.Mole;
import uk.co.jpereira.isu.units_accepted.Liter;

/**
 * Created by blue on 01/05/2015.
 */
public class MolarConcentration extends Formulae<Double> {
    static final String packageName = "Chemistry";
    double amount;
    Mole mole;
    Liter liter;

    public MolarConcentration() {
        mole = null;
        liter = null;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (liter == null || mole == null)
            return new String("MolarMass");
        return mole.name() + "/" + liter.name();
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    @Override
    public double solve() throws MissingParameters {
        if (mole == null)
            throw new MissingParameters("Mole");
        if (liter == null)
            throw new MissingParameters("Liter");
        amount = mole.getAmountToUnit() / liter.getAmountToUnit();
        return amount;
    }

    /**
     * @return
     */
    @Override
    public String resultUnit() {
        String result = new String();
        if (mole == null)
            result += "Mole";
        else
            result += mole.getSmallName();
        result += "/";
        if (liter == null)
            result += "Liter";
        else
            result += liter.getSmallName();
        return result;

    }

    /**
     * Function will generate the JSON representation
     * of the object
     *
     * @return
     */
    @Override
    public JSONObject getRepresentation() {
        JSONObject obj = this.generateBaseObject();
        obj.put(AMOUNT, amount);
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
        if (object.containsKey(AMOUNT)) {
            amount = (Double) object.get(AMOUNT);
        }
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
                ;
                if (liter.getAmount() == null)
                    liter = null;
            }
        }
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
                " <mn>" + amount + " (" + resultUnit() + ")</mn>" +
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
     * Set the amount of units in this object unit
     *
     * @param amount
     * @return amount of units
     */
    @Override
    public void setAmount(Double amount) {
        if (amount == null)
            return;
        this.amount = amount;
    }

    /**
     * Retrieve the amount of units in the ISU Unit
     *
     * @return amount of units
     */
    @Override
    public Double getAmount() throws MissingParameters {
        return amount;
    }
}

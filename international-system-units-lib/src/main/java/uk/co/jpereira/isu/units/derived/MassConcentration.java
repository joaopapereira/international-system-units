package uk.co.jpereira.isu.units.derived;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import uk.co.jpereira.isu.exception.MissingParameters;
import uk.co.jpereira.isu.units.ISDimension;
import uk.co.jpereira.isu.units.KiloGram;
import uk.co.jpereira.isu.units.Unit;
import uk.co.jpereira.isu.units_accepted.Liter;

/**
 * @author Joao Pereira
 * This enum will represent all the Dimension available in the ISU
 */
@Unit(dimension = ISDimension.COMPOSED)
public class MassConcentration extends DerivedUnit {
    /**
     * KiloGram amount of Mass
     */
    KiloGram kiloGram;
    /**
     * Amount of liters
     */
    Liter liter;

    /**
     * Class constructor
     */
    public MassConcentration() {
        super(Double.NaN);
        kiloGram = null;
        liter = null;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (liter == null || kiloGram == null)
            return "MassConcentration";
        return kiloGram.name() + "/" + liter.name();
    }


    /**
     * Retrieve the name of the the Unit
     *
     * @return Name
     */
    @Override
    public String name() {
        if (liter == null || kiloGram == null)
            return "MassConcentration";
        return liter.name() + "/" + kiloGram.name();
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
        if (currentAmount != null && !currentAmount.isNaN() && liter != null && kiloGram != null) {
            logger.finest("No need to calculate, values present!");
            return getAmountToUnit();
        } else if ((currentAmount == null || currentAmount.isNaN()) && liter != null && kiloGram != null) {
            logger.finest("Amount was missing!");
            setAmount(kiloGram.getAmount() / liter.getAmountToUnit());
            result = getAmountToUnit();
        } else if (currentAmount != null && !getAmountToUnit().isNaN()) {
            if (liter != null) {
                logger.finest("KiloGram was missing!");
                kiloGram = new KiloGram(getAmountToUnit() * liter.getAmountToUnit());
                result = kiloGram.getAmount();
            } else if (kiloGram != null) {
                logger.finest("Liter was missing!");
                liter = new Liter(kiloGram.getAmount() / getAmountToUnit());
                result = liter.getAmount();
            }
        }
        logger.finest("Current result is: " + result);
        if (new Double(result).isNaN()) {
            logger.finest("No calcules made...");
            if (liter == null)
                throw new MissingParameters("Liter");
            else if (kiloGram == null)
                throw new MissingParameters("KiloGram");
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
        KiloGram kg = kiloGram;
        if (l == null)
            l = new Liter();
        if (kg == null)
            kg = new KiloGram();
        obj = addSubUnit(obj, kg.getRepresentation());
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
            kiloGram = null;
            liter = null;
        }
        for (Object subobject : subunits) {
            JSONObject subunit = (JSONObject) subobject;
            if (KiloGram.class == subunit.get(CLASS)) {
                kiloGram = new KiloGram();
                kiloGram.loadFromRepresentation(subunit);
                if (kiloGram.getAmount() == null)
                    kiloGram = null;
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
     * Retrieve the abbreviated name of the unit
     * @return Abbreviation of the name
     */
    @Override
    public String smallName() {
        if (liter == null || kiloGram == null)
            return new String("MolarMass");
        return kiloGram.smallName() + "/" + liter.getSmallName(false);
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
        String gramName = "<mtext>Gram</mtext>";
        if (kiloGram != null)
            gramName = kiloGram.toMathML();
        String literName = "<mtext>Liter</mtext>";
        if (liter != null)
            literName = liter.toMathML();
        result = "<mrow>" +
                " <mn>" + getValueWithUnit() + "</mn>" +
                " <mo>=</mo>" +
                " <mfrac>" +
                "  <mrow>" +
                gramName +
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
     * Set kilo grams
     *
     * @param kg KilogGrams to use in the concentration calc
     */
    public void setKiloGram(KiloGram kg) {
        kiloGram = kg;
    }
    /**
     * Get kilo grams
     * @return Kilograms on the concentration
     */
    public KiloGram getKiloGram() {
        return kiloGram;
    }

    /**
     * Set Liter
     *
     * @param liter Liters to use in the concentration calc
     */
    public void setLiter(Liter liter) {
        this.liter = liter;
    }

    /**
     * Get Liter
     * @return Liters on the concentration
     */
    public Liter getLiter() {
        return liter;
    }

    /**
     * Compare to units using the names
     * @param otherUnit Other unit to compare to
     * @return 0 If objects are equal
     *         Bigger 0 If otherUnit is bigger then current object
     *         Smaller 0 If current object is bigger then otherUnit
     * @throws MissingParameters thrown when not enough parameters are present to compare both
     */
    public int compareTo(MassConcentration otherUnit) throws MissingParameters {
        int res1 = toString().compareTo(otherUnit.toString());
        if (res1 == 0) {
            return Double.compare(getAmount(), otherUnit.getAmount());
        }
        return res1;
    }

    public boolean equals(MassConcentration otherUnit) throws MissingParameters {
        return 0 == compareTo(otherUnit);
    }
}

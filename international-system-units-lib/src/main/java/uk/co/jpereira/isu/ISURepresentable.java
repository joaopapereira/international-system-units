package uk.co.jpereira.isu;

import uk.co.jpereira.isu.exception.MissingParameters;

/**
 * Created by blue on 01/05/2015.
 */
public interface ISURepresentable<Precision> extends JSONRepresentation, MathMLClass {
    /**
     * Set the amount of units in this object unit
     *
     * @return amount of units
     */
    @SuppressWarnings("unchecked")
    void setAmount(Precision amount);

    /**
     * Retrieve the amount of units in the ISU Unit
     *
     * @return amount of units
     */
    Precision getAmount() throws MissingParameters;

    double solve() throws MissingParameters;

    default boolean isReversible() {
        return true;
    }
}

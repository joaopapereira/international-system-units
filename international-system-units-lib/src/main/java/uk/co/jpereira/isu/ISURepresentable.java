package uk.co.jpereira.isu;

import uk.co.jpereira.isu.exception.MissingParameters;

/**
 * Created by blue on 01/05/2015.
 */
public interface ISURepresentable<Precision> extends JSONRepresentation, MathMLClass {
    /**
     * Set the amount of units in this object unit
     * @param amount Amount to be set
     */
    @SuppressWarnings("unchecked")
    void setAmount(Precision amount);

    /**
     * Retrieve the amount of units in the ISU Unit
     *
     * @return amount of units
     * @throws MissingParameters If not enough parameters are present
     */
    Precision getAmount() throws MissingParameters;

    /**
     * Function that will solve the formulae
     * @return The result
     * @throws MissingParameters If not enough parameters are present
     */
    double solve() throws MissingParameters;

    /**
     * Check if we can calculate the formulae having the result present
     * x = y + 1
     * If the function is reversible we can assign a value to 'x' that it will calculate 'y'
     * If is not reversible the only option is to assign 'y' to find the value of 'x'
     * @return If the formulae is reversible or not
     */
    default boolean isReversible() {
        return true;
    }
}

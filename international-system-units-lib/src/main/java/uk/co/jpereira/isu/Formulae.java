package uk.co.jpereira.isu;

import java.util.logging.Logger;

/**
 * Created by blue on 01/05/2015.
 */
public abstract class Formulae<Precision> implements ISURepresentable<Precision>, Comparable<Formulae> {

    protected final Logger logger;

    /**
     * Formulae constructor
     */
    public Formulae() {
        logger = Logger.getLogger(this.getClass().getPackage().getName());
    }
    public abstract String getPackageName();

    /**
     * Name of the unit of the resulting formulae
     *
     * @return String with the unit representation
     */
    public abstract String resultUnit();

    /**
     * Compare to units using the names
     *
     * @param otherFormulae Other Formulae to compare to
     */
    public int compareTo(Formulae otherFormulae) {
        return getClass().getName().compareTo(otherFormulae.getClass().getName());
    }

    public boolean isReversible() {
        return false;
    }
}

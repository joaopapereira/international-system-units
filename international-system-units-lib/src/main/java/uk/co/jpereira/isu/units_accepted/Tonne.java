package uk.co.jpereira.isu.units_accepted;

import uk.co.jpereira.isu.units.ISDimension;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.Unit;
import uk.co.jpereira.isu.units.UnitModifier;

@Unit(dimension = ISDimension.MASS)
public class Tonne extends ISUUnit<Double> {
    /**
     * Tonne constructor
     *
     * @param amount Amount of unit
     * @param toUnit conversion factor
     */
    public Tonne(Double amount, UnitModifier toUnit) {
        super(amount, toUnit);
    }

    /**
     * Tonne constructor
     *
     * @param amount Amount of unit
     */
    public Tonne(Double amount) {
        super(amount, UnitModifier.Unit);
    }

    /**
     * Tonne constructor
     *
     * @param toUnit conversion factor
     */
    public Tonne(UnitModifier toUnit) {
        super(0., toUnit);
    }

    /**
     * Tonne default constructor
     */
    public Tonne() {
        super(0., UnitModifier.Unit);
    }

    /**
     * Retrieve the name of the the Unit
     * @return Name
     */
    @Override
    public String name() {
        return new String("Tonne");
    }

    /**
     * Retrieve the abbreviated name of the unit
     * @return Abbreviation of the name
     */
    @Override
    public String smallName() {
        return new String("t");
    }
}

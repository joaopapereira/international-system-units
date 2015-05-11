package uk.co.jpereira.isu.units_accepted;

import uk.co.jpereira.isu.units.ISDimension;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.Unit;
import uk.co.jpereira.isu.units.UnitModifier;

@Unit(dimension = ISDimension.AREA)
public class Hectare extends ISUUnit<Double> {
    /**
     * Hectare constructor
     *
     * @param amount Amount of unit
     * @param toUnit conversion factor
     */
    public Hectare(Double amount, UnitModifier toUnit) {
        super(amount, toUnit);
    }

    /**
     * Hectare constructor
     *
     * @param amount Amount of unit
     */
    public Hectare(Double amount) {
        super(amount, UnitModifier.Unit);
    }

    /**
     * Hectare constructor
     *
     * @param toUnit conversion factor
     */
    public Hectare(UnitModifier toUnit) {
        super(0., toUnit);
    }

    /**
     * Hectare default constructor
     */
    public Hectare() {
        super(0., UnitModifier.Unit);
    }

    /**
     * Retrieve the name of the the Unit
     * @return Name
     */
    @Override
    public String name() {
        return new String("Hectare");
    }

    /**
     * Retrieve the abbreviated name of the unit
     * @return Abbreviation of the name
     */
    @Override
    public String smallName() {
        return new String("ha");
    }
}

package uk.co.jpereira.isu.units_accepted;

import uk.co.jpereira.isu.units.ISDimension;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.Unit;
import uk.co.jpereira.isu.units.UnitModifier;

@Unit(dimension = ISDimension.MASS)
public class Tonne extends ISUUnit<Double> {
    /**
     * ISUUnit constructor
     *
     * @param amount Amount of unit
     * @param toUnit conversion factor
     */
    public Tonne(Double amount, UnitModifier toUnit) {
        super(amount, toUnit);
    }

    /**
     * ISUUnit constructor
     *
     * @param amount Amount of unit
     */
    public Tonne(Double amount) {
        super(amount, UnitModifier.Unit);
    }

    /**
     * ISUUnit constructor
     *
     * @param toUnit conversion factor
     */
    public Tonne(UnitModifier toUnit) {
        super(0., toUnit);
    }

    /**
     * ISUUnit constructor
     *
     * @param toUnit conversion factor
     */
    public Tonne() {
        super(0., UnitModifier.Unit);
    }

    @Override
    public String name() {
        return new String("Tonne");
    }

    @Override
    public String smallName() {
        return new String("t");
    }
}

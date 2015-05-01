package uk.co.jpereira.isu.units_accepted;

import uk.co.jpereira.isu.units.ISDimension;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.Unit;
import uk.co.jpereira.isu.units.UnitModifier;

@Unit(dimension = ISDimension.AREA)
public class Hectare extends ISUUnit<Double> {
    /**
     * ISUUnit constructor
     *
     * @param amount Amount of unit
     * @param toUnit conversion factor
     */
    public Hectare(Double amount, UnitModifier toUnit) {
        super(amount, toUnit);
    }

    /**
     * ISUUnit constructor
     *
     * @param amount Amount of unit
     */
    public Hectare(Double amount) {
        super(amount, UnitModifier.Unit);
    }

    /**
     * ISUUnit constructor
     *
     * @param toUnit conversion factor
     */
    public Hectare(UnitModifier toUnit) {
        super(0., toUnit);
    }

    /**
     * ISUUnit constructor
     *
     * @param toUnit conversion factor
     */
    public Hectare() {
        super(0., UnitModifier.Unit);
    }

    @Override
    public String name() {
        return new String("Hectare");
    }

    @Override
    public String smallName() {
        return new String("ha");
    }
}
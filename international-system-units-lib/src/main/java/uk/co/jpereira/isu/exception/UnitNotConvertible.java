package uk.co.jpereira.isu.exception;

import uk.co.jpereira.isu.units.ISUUnit;

public class UnitNotConvertible extends GenericException {
	public UnitNotConvertible(ISUUnit<?> unit){
		super("Unit " + unit + " cannot be converted!");
	}
}

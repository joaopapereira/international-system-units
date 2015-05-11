package uk.co.jpereira.isu.exception;

import uk.co.jpereira.isu.units.ISUUnit;
/**
 * @author Joao Pereira
 * Exception thrown when the Unit is not convertible
 */
public class UnitNotConvertible extends GenericException {
	/**
	 * Class constructor
	 * @param unit Unit that is being converted
	 */
	public UnitNotConvertible(ISUUnit<?> unit){
		super("Unit " + unit + " cannot be converted!");
	}
}

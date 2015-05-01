package uk.co.jpereira.isu.exception.translator;

import uk.co.jpereira.isu.exception.GenericException;
import uk.co.jpereira.isu.units.ISUUnit;

public class InvalidUnit extends GenericException {
	public InvalidUnit(Object translator, ISUUnit unit){
		super("The unit: " + unit + " cannot be translated with: " + translator);
	}
}

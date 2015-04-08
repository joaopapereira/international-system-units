package uk.co.jpereira.isue.exception.translator;

import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isue.exception.GenericException;

public class InvalidUnit extends GenericException {
	public InvalidUnit(Object translator, ISUUnit unit){
		super("The unit: " + unit + " cannot be translated with: " + translator);
	}
}

package uk.co.jpereira.isu.exception.translator;

import uk.co.jpereira.isu.exception.GenericException;
import uk.co.jpereira.isu.units.ISUUnit;
/**
 * @author Joao Pereira
 * Exception thrown when and invalid unit is being translater
 */
public class InvalidUnit extends GenericException {
	/**
	 * Class constructor
	 * @param translator Translator being used
	 * @param unit Unit being translated
	 */
	public InvalidUnit(Object translator, ISUUnit unit){
		super("The unit: " + unit + " cannot be translated with: " + translator);
	}
}

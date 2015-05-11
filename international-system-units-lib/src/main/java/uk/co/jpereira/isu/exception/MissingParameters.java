package uk.co.jpereira.isu.exception;
/**
 * @author Joao Pereira
 * Exception thrown when the formulae is missing some parameters in order to produce
 * a result
 */
public class MissingParameters extends GenericException {
	/**
	 * Class constructor
	 * @param parameterName Missing parameter
	 */
	public MissingParameters(String parameterName){
		super("Missing parameter: " + parameterName);
	}
}

package uk.co.jpereira.isu.exception;
/**
 * @author Joao Pereira
 * Generic exception of the application
 */
public class GenericException extends Exception {
	/**
	 * Generic Exception
	 * @param message Exception message
	 */
	public GenericException(String message){
		super(message);
	}

	/**
	 * Default constructor
	 */
	public GenericException(){
		super("Generic Exception");
	}
}

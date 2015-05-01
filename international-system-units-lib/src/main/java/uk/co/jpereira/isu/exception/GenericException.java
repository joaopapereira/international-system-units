package uk.co.jpereira.isu.exception;

public class GenericException extends Exception {
	/**
	 * Generic Exception
	 * @param message Exception message
	 */
	public GenericException(String message){
		super(message);
	}
	public GenericException(){
		super("Generic Exception");
	}
}

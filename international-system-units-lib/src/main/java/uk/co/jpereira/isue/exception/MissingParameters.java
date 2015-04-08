package uk.co.jpereira.isue.exception;

public class MissingParameters extends GenericException {
	public MissingParameters(String parameterName){
		super("Missing parameter: " + parameterName);
	}
}

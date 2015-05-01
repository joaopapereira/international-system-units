package uk.co.jpereira.isu.exception;

public class MissingParameters extends GenericException {
	public MissingParameters(String parameterName){
		super("Missing parameter: " + parameterName);
	}
}

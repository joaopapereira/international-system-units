package uk.co.jpereira.isu.units;

/**
 * @author Joao Pereira
 * This enum will represent all the Dimension available in the ISU
 */
public enum ISDimension {
	AREA("A"),
	AMOUNT_OF_SUBSTANCE("N"),
	COMPOSED("", false),
	DIMENSIONLESS(""),
	ELECTRIC_CURRENT("I"),
	LENGTH("L"),
	LUMINOUS_INTENSITY("J"),
	MASS("M"),
	THERMODYNAMIC_TEMPERATURE("Θ"),
	TIME("T"),
	VOLUME("V");
	/**
	 * Symbol representation as a string
	 */
	private String symbol;
	/**
	 * If the conversion using ISDimension is simple or not
	 */
	private boolean simpleConversion;

	/**
	 * Check if the dimension have a simple conversion or not
	 * @return Boolean true if can be converted false otherwise
	 */
	public boolean simpleConversion(){
		return simpleConversion;
	}

	/**
	 * Dimension constructor
	 * @param symbol String representation of the Dimension
	 */
	ISDimension(String symbol){
		this.symbol = symbol;
		this.simpleConversion = true;
	}

	/**
	 * Dimension constructor
	 * @param symbol String representation of the Dimension
	 * @param simpleConversion If is convertible or not
	 */
	ISDimension(String symbol, boolean simpleConversion){
		this.symbol = symbol;
		this.simpleConversion = simpleConversion;
	}

	/**
	 * Retrieve the String representation of the Dimension
	 * @return Dimension String representation
	 */
	public String getSymbol(){
		return symbol;
	}

	/**
	 * Returns the name of this enum constant, as contained in the
	 * declaration.  This method may be overridden, though it typically
	 * isn't necessary or desirable.  An enum type should override this
	 * method when a more "programmer-friendly" string form exists.
	 *
	 * @return the name of this enum constant
	 */
	@Override
	public String toString(){
		return toCamelCase(super.toString());
	}

	/**
	 * Helper function that will camelCase the name of the dimension
	 * @param toCamelize String to be camelized
	 * @return The Camelized version
	 */
	static String toCamelCase(String toCamelize){
        String[] parts = toCamelize.replace("_", " ").split(" ");
        String camelCaseString = "";
        for (String part : parts){
            if(part!=null && part.trim().length()>0)
            	camelCaseString = camelCaseString + toProperCase(part);
            else
                camelCaseString=camelCaseString+part+" ";   
        }
        return camelCaseString;
     }

	/**
	 * Convert to the proper case single letter
	 * @param toConvert String to convert
	 * @return String converted
	 */
	static String toProperCase(String toConvert) {
        String temp=toConvert.trim();
        String spaces="";
        if(temp.length()!=toConvert.length()){
	        int startCharIndex=toConvert.charAt(temp.indexOf(0));
	        spaces=toConvert.substring(0,startCharIndex);
        }
        temp=temp.substring(0, 1).toUpperCase() +
        spaces+temp.substring(1).toLowerCase()+" ";
        return temp;

    }
}

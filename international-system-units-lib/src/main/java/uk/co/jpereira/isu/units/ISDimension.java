package uk.co.jpereira.isu.units;


public enum ISDimension {
	AMOUNT_OF_SUBSTANCE("N"),
	COMPOSED("", false),
	DIMENSIONLESS(""),
	ELECTRIC_CURRENT("I"),
	LENGTH("L"),
	LUMINOUS_INTENSITY("J"),
	MASS("M"),
	THERMODYNAMIC_TEMPERATURE("Θ"),
	TIME("T");

	private String symbol;
	private boolean simpleConvertion;
	public boolean simpleConvertion(){
		return simpleConvertion;
	}
	ISDimension(String symbol){
		this.symbol = symbol;
		this.simpleConvertion = true;
	}
	ISDimension(String symbol, boolean simpleConvertion){
		this.symbol = symbol;
		this.simpleConvertion = simpleConvertion;
	}
	public String getSymbol(){
		return symbol;
	}
	public String toString(){
		return toCamelCase(super.toString());
	}
	static String toCamelCase(String s){
        String[] parts = s.replace("_", " ").split(" ");
        String camelCaseString = "";
        for (String part : parts){
            if(part!=null && part.trim().length()>0)
            	camelCaseString = camelCaseString + toProperCase(part);
            else
                camelCaseString=camelCaseString+part+" ";   
        }
        return camelCaseString;
     }
	static String toProperCase(String s) {
        String temp=s.trim();
        String spaces="";
        if(temp.length()!=s.length()){
	        int startCharIndex=s.charAt(temp.indexOf(0));
	        spaces=s.substring(0,startCharIndex);
        }
        temp=temp.substring(0, 1).toUpperCase() +
        spaces+temp.substring(1).toLowerCase()+" ";
        return temp;

    }
}

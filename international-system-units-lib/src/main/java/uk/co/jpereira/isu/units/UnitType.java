package uk.co.jpereira.isu.units;

import java.util.List;

public enum UnitType {
	AMOUNT_OF_SUBSTANCE,
	ELECTRIC_CURRENT,
	LENGTH,
	LUMINOUS_INTENSITY,
	MASS,
	THERMODYNAMIC_TEMPERATURE,
	TIME,
	NO_TYPE;
	
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

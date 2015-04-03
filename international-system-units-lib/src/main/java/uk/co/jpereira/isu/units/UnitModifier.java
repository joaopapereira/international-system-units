package uk.co.jpereira.isu.units;

public enum UnitModifier {
	YOTTA ("Y", 1e24), 
	ZETTA ("Z", 1e21), 
	EXA   ("E", 1e18),
	PETA  ("P", 1e15), 
	TERA  ("T", 1e12),
	GIGA  ("G", 1e9), 
	MEGA  ("M", 1e6),
	KILO  ("k", 1e3),  
	HECTO ("h", 1e2), 
	DECA  ("da", 1e1), 
	Unit  ("", 1e0), 
	DECI  ("d", 1e-1), 
	CENTI ("c", 1e-2), 
	MILLI ("m", 1e-3), 
	MICRO ("μ", 1e-6), 
	NANO  ("n", 1e-9), 
	PICO  ("p", 1e-12),  
	FEMTO ("f", 1e-15), 
	ATTO  ("a", 1e-18), 
	ZEPTO ("z", 1e-21), 
	YOCTO ("y", 1e-24);
	private String smallRepr;
	private double factor;
	/**
	 * Class constructor
	 * @param smallRepr Small name for the modifier
	 * @param factor Factor used to calculate the modification
	 */
	UnitModifier(String smallRepr, double factor){
		this.smallRepr = smallRepr;
		this.factor = factor;
	}
	/**
	 * Retrieve the factor of the modification
	 * @return Factor
	 */
	public double getFactor(){
		return factor;
	}
	/**
	 * Convert a value to with the current modifier
	 * @param value Value to convert
	 * @return Converted value
	 */
	public double convert(double value){
		return value * factor;
	}
	@Override
	public String toString(){
		return new String(this.name());
	}
	/**
	 * Retrieve the small representation
	 * @return String with the small representation
	 */
	public String getSmallRepr(){
		return smallRepr;
	}
	/**
	 * Retrieve the correct modifier
	 * Can use the full name or the small representation
	 * @param name Name of the modifier
	 * @return The Correct modifier
	 */
	public static UnitModifier getEnum(String name){
		try{
			return UnitModifier.valueOf(name.toUpperCase());
		}catch (IllegalArgumentException e) {
			for(UnitModifier modifier: UnitModifier.values()){
				if(name.equals(modifier.smallRepr)){
					return modifier;
				}
			}
		}
		throw new IllegalArgumentException("Unable to retrieve the modifier " + name);
	}
}

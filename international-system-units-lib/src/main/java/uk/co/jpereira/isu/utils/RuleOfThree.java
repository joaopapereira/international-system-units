package uk.co.jpereira.isu.utils;

public class RuleOfThree {
	public static double calculate(double a1, double a2, double b1){
		if(a1 == 0){
			throw new IllegalArgumentException("Division by Zero, a1 cannot be 0");
		}
		return a2 * b1 / a1;
	}
}

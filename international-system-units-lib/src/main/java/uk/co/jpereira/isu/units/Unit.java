package uk.co.jpereira.isu.units;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Unit {

	boolean isUnit() default true;

	ISDimension dimension() default ISDimension.DIMENSIONLESS;
}

package uk.co.jpereira.isu.units;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Unit {

	public boolean isUnit() default true;
}

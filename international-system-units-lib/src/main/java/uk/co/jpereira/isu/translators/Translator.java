package uk.co.jpereira.isu.translators;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Translator {

	public boolean isTranslator() default true;
	public String from();
	public String to();
}

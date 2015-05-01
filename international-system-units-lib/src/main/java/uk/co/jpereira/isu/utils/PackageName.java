package uk.co.jpereira.isu.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by blue on 01/05/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PackageName {
    String name();
}

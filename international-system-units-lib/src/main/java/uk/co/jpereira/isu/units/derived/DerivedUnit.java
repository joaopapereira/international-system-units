package uk.co.jpereira.isu.units.derived;

import uk.co.jpereira.isue.exception.MissingParameters;

public interface DerivedUnit{
	double calculateUnit()  throws MissingParameters;
}

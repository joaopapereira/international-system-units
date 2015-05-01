package uk.co.jpereira.isu.translators;

import uk.co.jpereira.isu.exception.translator.InvalidUnit;

public interface TranslationInterface<A, B> {
	B convert(A unit) throws InvalidUnit;
}

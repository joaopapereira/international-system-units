package uk.co.jpereira.isu.translators;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isue.exception.translator.InvalidUnit;
public interface TranslationInterface {
	public ISUUnit convert(ISUUnit unit)  throws InvalidUnit ;
}

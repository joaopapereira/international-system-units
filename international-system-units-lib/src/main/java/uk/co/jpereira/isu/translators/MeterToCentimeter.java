package uk.co.jpereira.isu.translators;

import uk.co.jpereira.isu.units.Meter;
import uk.co.jpereira.isu.units.meter.CentiMeter;

@Translator
public class MeterToCentimeter extends UnitTranslator<Meter, CentiMeter> {

	public MeterToCentimeter() {
		super(Meter.class, CentiMeter.class);
	}

	public Meter convert(CentiMeter from) {
		return new Meter(from.getAmount()*CentiMeter.toUnit);
	}

	public CentiMeter convert(Meter from) {
		return new CentiMeter(from.getAmount()/CentiMeter.toUnit);
	}

}

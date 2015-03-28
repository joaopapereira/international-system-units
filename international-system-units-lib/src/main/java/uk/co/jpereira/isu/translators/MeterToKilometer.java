package uk.co.jpereira.isu.translators;

import uk.co.jpereira.isu.units.Meter;
import uk.co.jpereira.isu.units.meter.KiloMeter;

@Translator
public class MeterToKilometer extends UnitTranslator<Meter, KiloMeter> {

	/**
	 * Translator constructor
	 */
	public MeterToKilometer() {
		super(Meter.class, KiloMeter.class);
	}

	public Meter convert(KiloMeter from) {
		return new Meter(from.getAmount()*KiloMeter.toUnit);
	}

	public KiloMeter convert(Meter from) {
		return new KiloMeter(from.getAmount()/KiloMeter.toUnit);
	}

}

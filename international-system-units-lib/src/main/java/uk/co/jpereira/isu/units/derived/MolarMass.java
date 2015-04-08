package uk.co.jpereira.isu.units.derived;

import uk.co.jpereira.isu.units.BasicUnit;
import uk.co.jpereira.isu.units.ISDimension;
import uk.co.jpereira.isu.units.KiloGram;
import uk.co.jpereira.isu.units.Mole;
import uk.co.jpereira.isu.units.Unit;
import uk.co.jpereira.isue.exception.MissingParameters;

@Unit(dimension=ISDimension.COMPOSED)
public class MolarMass extends BasicUnit<Double> implements DerivedUnit {
	private KiloGram mass;
	private Mole mole;
	public MolarMass(){
		super(Double.NaN);
		mass = null;
		mole = null;
	}
	public MolarMass(KiloGram mass, Mole mole){
		super(Double.NaN);
		this.mass = mass;
		this.mole = mole;
	}
	public MolarMass(KiloGram mass){
		super(Double.NaN);
		this.mass = mass;
		this.mole = null;
	}
	public MolarMass(Mole mole){
		super(Double.NaN);
		this.mass = null;
		this.mole = mole;
	}
	@Override
	public String name() {
		if(mass == null || mole == null)
			return new String("MolarMass");
		return mass.name()+ "/" + mole.name();
	}

	@Override
	public String smallName() {
		if(mass == null || mole == null)
			return new String("MolarMass");
		return mass.getSmallName()+ "/" + mole.smallName();
	}
	@Override
	public double calculateUnit() throws MissingParameters {
		double result = Double.NaN;
		if(!getAmountToUnit().isNaN() && mass != null && mole != null){
			logger.finest("No need to calculate, values present!");
			return getAmountToUnit();
		}else if(getAmountToUnit().isNaN() && mass != null && mole != null){
			logger.finest("Amount was missing!");
			setAmount(mass.getAmount() / mole.getAmount());
			result = getAmountToUnit();
		}else if(!getAmountToUnit().isNaN()){
			if(mass != null){
				logger.finest("Mole was missing!");
				mole = new Mole(mass.getAmount() / getAmountToUnit());
				result = mole.getAmount();
			}else if(mole != null){
				logger.finest("Mass was missing!");
				mass = new KiloGram(getAmountToUnit() * mole.getAmount());
				result = mass.getAmount();
			}
		}
		logger.finest("Current result is: "+ result);
		if(new Double(result).isNaN()){
			logger.finest("No calcules made...");
			if(mass == null)
				throw new MissingParameters("Mass");
			else if(mole == null)
				throw new MissingParameters("Mole");
		}
		return result;
	}
	public KiloGram getMass() throws MissingParameters {
		if(mass == null){
			calculateUnit();
		}
		return mass;
	}
	public void setMass(KiloGram mass) {
		this.mass = mass;
	}
	public Mole getMole() throws MissingParameters {
		if(mole == null){
			calculateUnit();
		}
		return mole;
	}
	public void setMole(Mole mole) {
		this.mole = mole;
	}
	@Override
	public Double getAmount() throws MissingParameters {
		if(getAmountToUnit().isNaN()){
			calculateUnit();
		}
		return getAmountToUnit();
	}

}

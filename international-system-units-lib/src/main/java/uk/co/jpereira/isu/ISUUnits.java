package uk.co.jpereira.isu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import org.reflections.Reflections;

import uk.co.jpereira.isu.units.BasicUnit;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.Unit;
import uk.co.jpereira.isu.units.UnitModifier;
import uk.co.jpereira.isu.units.ISDimension;
import uk.co.jpereira.isu.units.derived.DerivedUnit;
import uk.co.jpereira.isue.exception.MissingParameters;
import uk.co.jpereira.isue.exception.UnitNotConvertible;
import uk.co.jpereira.utils.Register;
import uk.co.jpereira.utils.RuleOfThree;
import uk.co.jpereira.utils.SharedMemory;
/**
 * Class that will expose to the world the Units and they translators
 * @author Joao Pereira
 *
 */
public class ISUUnits {
	private final static Logger LOGGER = Logger.getLogger(ISUUnits.class.getPackage().getName()); 
	public static Collection<ISUUnit> retrieveAllUnits(){
		LOGGER.fine("Retrieve All Units");
		return retrieveUnits(null);
	}
	public static Collection<ISUUnit> retrieveUnits(ISDimension type){
		LOGGER.fine("Retrieve All Units");
		Reflections reflections = new Reflections("uk.co.jpereira.isu.units");
		Collection allUnits = new ArrayList();

		LOGGER.info("Retrieve All Units");
		for(Class<?> unitType: reflections.getTypesAnnotatedWith(uk.co.jpereira.isu.units.Unit.class)){
			Unit unit = unitType.getAnnotation(uk.co.jpereira.isu.units.Unit.class);
			if(unit == null || (unit.dimension() != type && type != null)){
				continue;
			}
			LOGGER.finer("retrieved :"+ unitType);
			try {
				allUnits.add(unitType.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				LOGGER.severe(e.getMessage());
			}
		}
		List<ISUUnit> result = asSortedList(allUnits);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static double unitConvert(ISUUnit unit, UnitModifier from, UnitModifier to, double amount) throws UnitNotConvertible{
		LOGGER.fine("unitConverter(" + unit + ", "+from+", "+ to + ", "+ Double.toString(amount)+")");
		if(unit.getClass().getAnnotation(uk.co.jpereira.isu.units.Unit.class).dimension() == ISDimension.COMPOSED){
			throw new UnitNotConvertible(unit);
		}
		unit.setModifier(from);
		unit.setAmount(amount);
		return (double) unit.convertTo(to);
	}
	@SuppressWarnings("unchecked")
	public static double ruleOfThree(BasicUnit up, 
										UnitModifier upLeftMod, 
											  double upLeftAmount,
										UnitModifier upRightMod, 
										  	  double upRightAmount,
								  	BasicUnit down, 
										UnitModifier downLeftMod, 
											  double downLeftAmount,
										UnitModifier downRightMod){
		LOGGER.fine("ruleOfThree(" + up + ", "+upLeftMod+", "+ upLeftAmount + ", "+
										  ", "+upRightMod+", "+ upRightAmount + ", "+
								     down + ", "+downLeftMod+", "+ downLeftAmount + ", "+
										  ", " + downRightMod +")");
		double _upLeftAmount, _upRightAmount, _downLeftAmount;
		BasicUnit unit = (BasicUnit)up.clone();
		if(unit instanceof ISUUnit)
			((ISUUnit)unit).setModifier(upLeftMod);
		unit.setAmount(upLeftAmount);
		_upLeftAmount = (double)unit.getAmountToUnit();
		if(unit instanceof ISUUnit)
			((ISUUnit)unit).setModifier(upRightMod);
		unit.setAmount(upRightAmount);
		_upRightAmount = (double)unit.getAmountToUnit();
		unit = (ISUUnit)down.clone();
		if(unit instanceof ISUUnit)
			((ISUUnit)unit).setModifier(downLeftMod);
		unit.setAmount(downLeftAmount);
		_downLeftAmount = Double.NaN;
		try {
			_downLeftAmount = (double)unit.getAmount();
		} catch (MissingParameters e) {
			// TODO Auto-generated catch block
		}
		double result = RuleOfThree.calculate(_upLeftAmount, _upRightAmount, _downLeftAmount);
		unit.setAmount(result);
		if(unit instanceof ISUUnit){
			return (double) ((ISUUnit)unit).convertTo(downRightMod);
		}
		return result;
	}
	
	static{
		
		Register.start_thread();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		    	Register.stop_thread();
		    }
		}));
	}
	public static
	<T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
	  List<T> list = new ArrayList<T>(c);
	  java.util.Collections.sort(list);
	  return list;
	}
	public static Collection<BasicUnit> retrieveDerived(){
		LOGGER.fine("Retrieve All Derived Units");
		Reflections reflections = new Reflections("uk.co.jpereira.isu.units.derived");
		Collection allUnits = new ArrayList();

		for(Class<?> unitType: reflections.getSubTypesOf(uk.co.jpereira.isu.units.derived.DerivedUnit.class)){
			LOGGER.finer("retrieved :"+ unitType);
			try {
				allUnits.add(unitType.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				LOGGER.severe(e.getMessage());
			}
		}
		List<BasicUnit> result = asSortedList(allUnits);
		return result;
	}
}

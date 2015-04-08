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

import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.Unit;
import uk.co.jpereira.isu.units.UnitModifier;
import uk.co.jpereira.isu.units.ISDimension;
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
	public static double unitConvert(ISUUnit unit, UnitModifier from, UnitModifier to, double amount){
		LOGGER.fine("unitConverter(" + unit + ", "+from+", "+ to + ", "+ Double.toString(amount)+")");
		unit.setModifier(from);
		unit.setAmount(amount);
		return (double) unit.convertTo(to);
	}
	@SuppressWarnings("unchecked")
	public static double ruleOfThree(ISUUnit up, 
										UnitModifier upLeftMod, 
											  double upLeftAmount,
										UnitModifier upRightMod, 
										  	  double upRightAmount,
								  	 ISUUnit down, 
										UnitModifier downLeftMod, 
											  double downLeftAmount,
										UnitModifier downRightMod){
		LOGGER.fine("ruleOfThree(" + up + ", "+upLeftMod+", "+ upLeftAmount + ", "+
										  ", "+upRightMod+", "+ upRightAmount + ", "+
								     down + ", "+downLeftMod+", "+ downLeftAmount + ", "+
										  ", " + downRightMod +")");
		double _upLeftAmount, _upRightAmount, _downLeftAmount;
		ISUUnit unit = (ISUUnit)up.clone();
		unit.setModifier(upLeftMod);
		unit.setAmount(upLeftAmount);
		_upLeftAmount = (double)unit.getAmountToUnit();
		unit.setModifier(upRightMod);
		unit.setAmount(upRightAmount);
		_upRightAmount = (double)unit.getAmountToUnit();
		unit = (ISUUnit)down.clone();
		unit.setModifier(downLeftMod);
		unit.setAmount(downLeftAmount);
		_downLeftAmount = (double)unit.getAmount();
		double result = RuleOfThree.calculate(_upLeftAmount, _upRightAmount, _downLeftAmount);
		unit.setAmount(result);
		return (double) unit.convertTo(downRightMod);
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
}

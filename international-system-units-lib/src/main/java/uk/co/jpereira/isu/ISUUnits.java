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
import uk.co.jpereira.utils.Register;
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
		Reflections reflections = new Reflections("uk.co.jpereira.isu.units");
		Collection allUnits = new ArrayList();

		LOGGER.info("Retrieve All Units");
		for(Class<?> unitType: reflections.getTypesAnnotatedWith(uk.co.jpereira.isu.units.Unit.class)){
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

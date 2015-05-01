package uk.co.jpereira.isu;

import org.reflections.Reflections;
import uk.co.jpereira.isu.exception.MissingParameters;
import uk.co.jpereira.isu.exception.UnitNotConvertible;
import uk.co.jpereira.isu.units.*;
import uk.co.jpereira.isu.utils.Register;
import uk.co.jpereira.isu.utils.RuleOfThree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
/**
 * Class that will expose to the world the Units and they translators
 * @author Joao Pereira
 *
 */
public class ISUUnits {
	private final static Logger LOGGER = Logger.getLogger(ISUUnits.class.getPackage().getName());
	private final static Reflections allUnitsReflection = new Reflections("uk.co.jpereira.isu.units");
	private final static Reflections allDerivedUnitsReflection = new Reflections("uk.co.jpereira.isu.units.derived");
	private final static Reflections allClassesReflection = new Reflections("uk.co.jpereira");
	public static Collection<ISUUnit> retrieveAllUnits(){
		LOGGER.fine("Retrieve All Units");
		return retrieveUnits(null);
	}
	public static Collection<ISUUnit> retrieveUnits(ISDimension type){
		LOGGER.fine("Retrieve All Units");
		Reflections reflections = allUnitsReflection;
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

				LOGGER.severe("retrieved :" + ((BasicUnit) unitType.newInstance()).uid());
				LOGGER.severe("retrieved :" + ((BasicUnit) unitType.newInstance()).uid());
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
		Reflections reflections = allDerivedUnitsReflection;
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

	public static HashMap<String, List<Formulae>> getFormulae() {
		Reflections reflections = allClassesReflection;
		HashMap<String, List<Formulae>> result = new HashMap<String, List<Formulae>>();

		for (Class<?> formulae : reflections.getSubTypesOf(Formulae.class)) {
			LOGGER.severe("Formulae :" + formulae);
			try {
				LOGGER.severe("t0");
				Formulae form = (Formulae) formulae.newInstance();
				LOGGER.severe("t1");
				String pckName = form.getPackageName();
				LOGGER.severe("t2");
				if (!result.containsKey(pckName)) {
					result.put(pckName, new ArrayList<Formulae>());
				}
				LOGGER.severe("t3");
				result.get(pckName).add(form);
			} catch (InstantiationException | IllegalAccessException e) {
				LOGGER.severe(e.getMessage());
			}
		}
		for (String key : result.keySet()) {
			result.put(key, asSortedList(result.get(key)));
		}
		return result;
	}
}

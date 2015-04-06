package uk.co.jpereira.isu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.Unit;
import uk.co.jpereira.utils.Register;
import uk.co.jpereira.utils.SharedMemory;
/**
 * Class that will expose to the world the Units and they translators
 * @author Joao Pereira
 *
 */
public class ISUUnits {
	public static Collection<ISUUnit> retrieveAllUnits(){
		Reflections reflections = new Reflections("uk.co.jpereira.isu.units");
		Collection allUnits = new ArrayList();
		for(Class<?> unitType: reflections.getTypesAnnotatedWith(uk.co.jpereira.isu.units.Unit.class)){
			try {
				allUnits.add(unitType.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<ISUUnit> l = asSortedList(allUnits);
		return l;
	}
	
	static{
		System.out.println("Doing stuff");
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

package uk.co.jpereira.isu;

import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;

import uk.co.jpereira.isu.translators.UnitTranslator;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.utils.Register;
import uk.co.jpereira.utils.SharedMemory;
/**
 * Class that will expose to the world the Units and they translators
 * @author Joao Pereira
 *
 */
public class ISUUnits {
	public static Set<Class<? extends ISUUnit>> retrieveAllUnits(){
		Reflections reflections = new Reflections("uk.co.jpereira.isu.units");
		Set<Class<? extends ISUUnit>> allUnits = reflections.getSubTypesOf(ISUUnit.class);
		return allUnits;
	}
	public static UnitTranslator<?, ?> retrieveTranslator(Class class1, Class class2){
		SharedMemory mem = new SharedMemory();
		return mem.findTranslators(class1, class2);
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
}

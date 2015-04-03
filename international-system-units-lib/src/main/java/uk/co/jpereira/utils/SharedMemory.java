package uk.co.jpereira.utils;

import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;

import uk.co.jpereira.isu.translators.UnitTranslator;

class Registry{
	private static Registry instance = null;
	private static Set <UnitTranslator<?,?>> registry = new HashSet<UnitTranslator<?,?>>(); 
	protected Registry() {
		// Exists only to defeat instantiation.
	}
	public static Registry getInstance() {
		if(instance == null) {
			instance = new Registry();
		}
		return instance;
	}
	public SharedMemoryObject find (Class<?> c1, Class<?> c2) {

		for(SharedMemoryObject c: registry){
			System.out.println("Current hash: "+c.hashCode()+" == " + (c1.hashCode()+c2.hashCode()));
			if(c.hashCode() == (c1.hashCode()+c2.hashCode())){
				return c;
			}
		}
		throw new IllegalArgumentException ("No registered translation from " + c1 +" to "+c2);
	}
	public void add(UnitTranslator<?,?> obj){
		registry.add(obj);
	}
}
public class SharedMemory {
	public SharedMemory(){
		Registry.getInstance();
		Reflections reflections = new Reflections("uk.co.jpereira.isu.translators");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(uk.co.jpereira.isu.translators.Translator.class);
		for(Class<?> translator: annotated){
			try {
				System.out.println("Adding: "+translator.newInstance().toString() + " -> " + translator.newInstance().hashCode());

				System.out.println("Adding: "+translator.newInstance().toString() + " -> " + translator.newInstance().hashCode());
				Registry.getInstance().add( (UnitTranslator<?, ?>) translator.newInstance());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public UnitTranslator<?, ?> findTranslators(Class<?> class1, Class<?> class2){
		return (UnitTranslator<?, ?>) Registry.getInstance().find(class1, class2);
	}
}

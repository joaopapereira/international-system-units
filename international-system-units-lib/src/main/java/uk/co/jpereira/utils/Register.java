package uk.co.jpereira.utils;

import java.util.Hashtable;

public class Register extends Thread {
	@SuppressWarnings("rawtypes")
	public static synchronized void register(String a, Class obj){
		hash.put(a, obj);
	}
	public static synchronized Object get(String option1, String option2){
		try {
			if(hash.containsKey(option1))
				return hash.get(option1).newInstance();
			else if(hash.containsKey(option2))
				return hash.get(option2).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void start_thread(){
		register.start();
	}
	public static void stop_thread(){
		synchronized (register) {
			register.keep_running = false;
			register.notifyAll();
		}
	}
	@SuppressWarnings("rawtypes")
	private static Hashtable<String, Class> hash;
	private static boolean keep_running;
	private static Register register = new Register();
	@SuppressWarnings("rawtypes")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		hash = new Hashtable<String, Class>();
		keep_running = true;
		while(keep_running){
			System.out.println("Running");
			try {
				synchronized (register) {
					register.wait(5000);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Out");
	}
}

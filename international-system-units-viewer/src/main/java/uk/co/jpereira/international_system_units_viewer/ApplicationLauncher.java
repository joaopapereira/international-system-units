package uk.co.jpereira.international_system_units_viewer;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

import uk.co.jpereira.views.MainView;

public class ApplicationLauncher {
	
	public static void main(String[] args) {
	    try {
	    	
			FileInputStream fis =  new FileInputStream("src/main/resources/logging.properties");
			LogManager.getLogManager().readConfiguration(fis);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainView main = new MainView();
				main.setVisible(true);
			}
		});
	}

}

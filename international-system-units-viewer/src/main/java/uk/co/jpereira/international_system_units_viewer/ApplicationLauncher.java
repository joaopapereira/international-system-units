package uk.co.jpereira.international_system_units_viewer;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

import uk.co.jpereira.views.MainView;

public class ApplicationLauncher {
	
	public static void main(String[] args) {
		ApplicationLauncher launcher = new ApplicationLauncher();
		launcher.launch();
	}
	private void launch(){
		try {
			FileInputStream fis = new FileInputStream(getClass().getClassLoader().getResource("logging.properties").getFile());
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

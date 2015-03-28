package uk.co.jpereira.international_system_units_viewer;

import java.awt.EventQueue;

import uk.co.jpereira.views.MainView;

public class ApplicationLauncher {

	public static void main(String[] args) {
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

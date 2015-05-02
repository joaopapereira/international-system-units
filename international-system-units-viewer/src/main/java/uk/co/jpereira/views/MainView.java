package uk.co.jpereira.views;

import uk.co.jpereira.views.panels.FormulaePanel;
import uk.co.jpereira.views.panels.RuleOfThreePanel;
import uk.co.jpereira.views.panels.UnitConverterPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MainView extends JFrame implements Observer{
	public static final Dimension contentSize = new Dimension(600, 430);
	public static final int borderSize = 5;
	public static final Rectangle fullSize = new Rectangle(100, 100, 
														(int)contentSize.getWidth() + borderSize * 2, 
														(int)contentSize.getHeight() + borderSize * 2+19);
	/**
	 * Create the frame.
	 */
	public MainView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(fullSize);
		getContentPane().setLayout(null);
		getContentPane().setPreferredSize(contentSize);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(contentSize);
		tabbedPane.setSize(contentSize);
		getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("Rule of Three", new RuleOfThreePanel());
		tabbedPane.addTab("Unit Convertion", new UnitConverterPanel());
		tabbedPane.addTab("Formulae", new FormulaePanel());
		
		
	}
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}

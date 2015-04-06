package uk.co.jpereira.views;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.Timer;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import uk.co.jpereira.isu.ISUUnits;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.Meter;
import uk.co.jpereira.isu.units.UnitModifier;
import uk.co.jpereira.views.panels.RuleOfThreePanel;
import uk.co.jpereira.views.panels.UnitConverterPanel;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

public class MainView extends JFrame implements Observer{
	public static final Dimension contentSize = new Dimension(600, 430);
	public static final int borderSize = 5;
	public static final Rectangle fullSize = new Rectangle(100, 100, 
														(int)contentSize.getWidth() + borderSize * 2, 
														(int)contentSize.getHeight() + borderSize * 2);
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
		
		
	}
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}

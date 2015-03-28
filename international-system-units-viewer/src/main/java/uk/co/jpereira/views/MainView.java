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

import uk.co.jpereira.isu.units.Meter;

public class MainView extends JFrame implements Observer{
	public static final Dimension contentSize = new Dimension(600, 430);
	public static final int borderSize = 5;
	public static final Rectangle fullSize = new Rectangle(100, 100, 
														(int)contentSize.getWidth() + borderSize * 2, 
														(int)contentSize.getHeight() + borderSize * 2);
	private JPanel contentPane;
	private Set<JPanel> all_panels;
	private Timer timer;
	private boolean timerActive = false;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Create the frame.
	 */
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(33, 68, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(296, 68, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(129, 68, 106, 20);
		contentPane.add(comboBox);
		comboBox.addItem(new Meter(1));
		
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(392, 68, 102, 20);
		contentPane.add(comboBox_1);
		this.setResizable(false);
		
		timer = new Timer();
		
	}
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}

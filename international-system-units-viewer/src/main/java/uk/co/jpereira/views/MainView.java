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
import uk.co.jpereira.isu.translators.UnitTranslator;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.Meter;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
class ComboItem
{
    private String key;
    private Class value;

    public ComboItem(String key, Class value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public Class getValue()
    {
        return value;
    }
}
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
		JComboBox comboBox_1 = new JComboBox();
		comboBox.setBounds(129, 68, 111, 20);
		contentPane.add(comboBox);
		for(Class<? extends ISUUnit> unit: ISUUnits.retrieveAllUnits()){
			ISUUnit obj;
			try {
				obj = unit.newInstance();
				comboBox.addItem(new ComboItem(obj.toString(), unit));
				comboBox_1.addItem(new ComboItem(obj.toString(), unit));
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		comboBox_1.setBounds(392, 68, 102, 20);
		contentPane.add(comboBox_1);
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().length() == 0){
					return;
				}
				float f = Float.parseFloat(textField.getText());
				Class cls1, cls2;
				cls1 = ((ComboItem)comboBox.getSelectedItem()).getValue();
				cls2 = ((ComboItem)comboBox_1.getSelectedItem()).getValue();
				System.out.println("Selected stuff is: " + cls1 + " -> " + cls2);
				UnitTranslator<?, ?> converter = ISUUnits.retrieveTranslator(cls1, cls2);
				ISUUnit unit = null;
				try {
					unit = (ISUUnit) ((ComboItem)comboBox.getSelectedItem()).getValue().newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				unit.setAmount(f);
				textField_1.setText(converter.convert(unit).toString());
				
			}
		});
		btnConvert.setBounds(304, 123, 89, 23);
		contentPane.add(btnConvert);
		this.setResizable(false);
		
		timer = new Timer();
		
	}
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}

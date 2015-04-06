package uk.co.jpereira.views.panels;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uk.co.jpereira.isu.ISUUnits;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.UnitModifier;
import uk.co.jpereira.views.utils.ComboBoxItem;
import javax.swing.JLabel;

public class UnitConverterPanel extends Panel {
	private JTextField fromText, toText;
	private JComboBox typeBox, unitBox, fromBox, toBox;
	private JLabel lblType;
	public UnitConverterPanel(){
		super();
		setLayout(null);
		fromText = new JTextField();
		fromText.setBounds(135, 140, 86, 20);
		add(fromText);
		fromText.setColumns(10);
		
		toText = new JTextField();
		toText.setEditable(false);
		toText.setBounds(135, 172, 86, 20);
		add(toText);
		toText.setColumns(10);
		
		typeBox = new JComboBox();
		typeBox.setBounds(135, 41, 111, 20);
		unitBox = new JComboBox<ComboBoxItem<ISUUnit>>();
		unitBox.setBounds(137, 78, 109, 20);
		add(unitBox);
		add(typeBox);
		for(ISUUnit unit: ISUUnits.retrieveAllUnits()){
				unitBox.addItem(new ComboBoxItem<ISUUnit>(unit.toString(), unit));
		}
		fromBox = new JComboBox<UnitModifier>();
		toBox = new JComboBox<UnitModifier>();
		for(UnitModifier mod: UnitModifier.values()){
			fromBox.addItem(mod);
			toBox.addItem(mod);
		}
		
		fromBox.setBounds(231, 140, 80, 20);
		add(fromBox);

		toBox.setBounds(231, 172, 80, 20);
		add(toBox);
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fromText.getText().length() == 0){
					return;
				}
				double f = Double.parseDouble(fromText.getText());
				UnitModifier fromUnit, toUnit;
				fromUnit = (UnitModifier)fromBox.getSelectedItem();
				toUnit = (UnitModifier)toBox.getSelectedItem();
				System.out.println("Selected stuff is: " + fromUnit + " -> " + toUnit);
				ISUUnit myUnit = ((ComboBoxItem<ISUUnit>)unitBox.getSelectedItem()).getValue();
				myUnit.setModifier(fromUnit);
				myUnit.setAmount(f);
				myUnit.convertTo(toUnit);
				toText.setText(myUnit.convertTo(toUnit).toString());
				
			}
		});
		btnConvert.setBounds(219, 219, 71, 23);
		add(btnConvert);
		
		JLabel lblUnit = new JLabel("Unit");
		lblUnit.setBounds(81, 81, 46, 14);
		add(lblUnit);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(79, 143, 46, 14);
		add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(81, 175, 46, 14);
		add(lblTo);
		
		lblType = new JLabel("Type");
		lblType.setBounds(79, 44, 46, 14);
		add(lblType);
	}
}

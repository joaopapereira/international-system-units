package uk.co.jpereira.views.panels;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

		fromBox = new JComboBox<ComboBoxItem<UnitModifier>>();
		toBox = new JComboBox<ComboBoxItem<UnitModifier>>();
		unitBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					ISUUnit item = ((ComboBoxItem<ISUUnit>)event.getItem()).getValue();
					fromBox.removeAllItems();
					toBox.removeAllItems();
					loadModifiersComboBox(item);
		        }
			}
		});

		loadModifiersComboBox(((ComboBoxItem<ISUUnit>)unitBox.getSelectedItem()).getValue());
		
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
				fromUnit = ((ComboBoxItem<UnitModifier>)fromBox.getSelectedItem()).getValue();
				toUnit = ((ComboBoxItem<UnitModifier>)toBox.getSelectedItem()).getValue();
				System.out.println("Selected stuff is: " + fromUnit + " -> " + toUnit);
				ISUUnit myUnit = ((ComboBoxItem<ISUUnit>)unitBox.getSelectedItem()).getValue();
				toText.setText(Double.toString(ISUUnits.unitConvert(myUnit, fromUnit, toUnit, f)));
			}
		});
		btnConvert.setBounds(219, 219, 92, 23);
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
	private void loadModifiersComboBox(ISUUnit<?> unit){

		for(UnitModifier mod: UnitModifier.values()){
			unit.setModifier(mod);
			fromBox.addItem(new ComboBoxItem<UnitModifier>(unit.getSmallName(), mod));
			toBox.addItem(new ComboBoxItem<UnitModifier>(unit.getSmallName(), mod));
		}
	}
}

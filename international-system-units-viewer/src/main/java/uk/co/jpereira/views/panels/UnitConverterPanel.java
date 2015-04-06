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
import uk.co.jpereira.isu.units.UnitType;
import uk.co.jpereira.views.MainView;
import uk.co.jpereira.views.utils.ComboBoxItem;

import javax.swing.JLabel;

public class UnitConverterPanel extends Panel {
	private JTextField fromText, toText;
	private JComboBox unitBox, fromBox, toBox;
	private JComboBox<UnitType> typeBox;
	private JLabel lblType;
	JButton btnConvert;
	public UnitConverterPanel(){
		super();

		setSize(MainView.contentSize);
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
		
		typeBox = new JComboBox<UnitType>();
		typeBox.setBounds(135, 41, 111, 20);
		for(UnitType ut: UnitType.values()){
			typeBox.addItem(ut);
		}
		typeBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					loadUnitComboBox();
				}
			}
		});
		unitBox = new JComboBox<ComboBoxItem<ISUUnit>>();
		unitBox.setBounds(137, 78, 109, 20);
		add(unitBox);
		add(typeBox);
		for(ISUUnit unit: ISUUnits.retrieveUnits((UnitType) typeBox.getSelectedItem())){
				unitBox.addItem(new ComboBoxItem<ISUUnit>(unit.toString(), unit));
		}

		fromBox = new JComboBox<ComboBoxItem<UnitModifier>>();
		toBox = new JComboBox<ComboBoxItem<UnitModifier>>();
		unitBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					ISUUnit item = ((ComboBoxItem<ISUUnit>)event.getItem()).getValue();
					loadModifiersComboBox(item);
		        }
			}
		});
		if(unitBox.getItemCount() == 0){
			unitBox.setEnabled(false);
		}
		
		
		fromBox.setBounds(231, 140, 80, 20);
		add(fromBox);

		toBox.setBounds(231, 172, 80, 20);
		add(toBox);
		
		btnConvert = new JButton("Convert");
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
		
		

		loadUnitComboBox();
		
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
		fromBox.removeAllItems();
		toBox.removeAllItems();
		if(unit == null){
			fromBox.setEnabled(false);
			toBox.setEnabled(false);
			btnConvert.setEnabled(false);
			return;
		}
		fromBox.setEnabled(true);
		toBox.setEnabled(true);
		btnConvert.setEnabled(true);
		for(UnitModifier mod: UnitModifier.values()){
			unit.setModifier(mod);
			fromBox.addItem(new ComboBoxItem<UnitModifier>(unit.getSmallName(), mod));
			toBox.addItem(new ComboBoxItem<UnitModifier>(unit.getSmallName(), mod));
		}
	}
	private void loadUnitComboBox(){
		unitBox.removeAllItems();
		for(ISUUnit unit: ISUUnits.retrieveUnits((UnitType) typeBox.getSelectedItem())){
			unitBox.addItem(new ComboBoxItem<ISUUnit>(unit.toString(), unit));
		}
		if(unitBox.getItemCount() == 0){
			unitBox.setEnabled(false);
			loadModifiersComboBox(null);
		}else{
			unitBox.setEnabled(true);
			loadModifiersComboBox(((ComboBoxItem<ISUUnit>)unitBox.getSelectedItem()).getValue());
		}
	}
}

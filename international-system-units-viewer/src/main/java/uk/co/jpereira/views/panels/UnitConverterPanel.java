package uk.co.jpereira.views.panels;

import uk.co.jpereira.isu.ISUUnits;
import uk.co.jpereira.isu.exception.UnitNotConvertible;
import uk.co.jpereira.isu.units.ISDimension;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.UnitModifier;
import uk.co.jpereira.views.MainView;
import uk.co.jpereira.views.utils.BasicUnitComboBox;
import uk.co.jpereira.views.utils.ComboBoxItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class UnitConverterPanel extends Panel {
	private JTextField fromText, toText;
	private BasicUnitComboBox fromBox, toBox;
	private JComboBox<ComboBoxItem<ISUUnit>> unitBox;
	private JComboBox<ISDimension> typeBox;
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
		
		typeBox = new JComboBox<ISDimension>();
		typeBox.setBounds(135, 41, 176, 20);
		for(ISDimension ut: ISDimension.values()){
			if(ut.simpleConvertion())
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
		unitBox.setBounds(137, 78, 174, 20);
		add(unitBox);
		add(typeBox);
		for(ISUUnit unit: ISUUnits.retrieveUnits((ISDimension) typeBox.getSelectedItem())){
			unitBox.addItem(new ComboBoxItem<>(unit.name(), unit));
		}

		fromBox = new BasicUnitComboBox();
		toBox = new BasicUnitComboBox();
		unitBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					ISUUnit item = ((ComboBoxItem<ISUUnit>)event.getItem()).getValue();
					//ISUUnit item = (ISUUnit)event.getItem();
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
					JOptionPane.showMessageDialog(null, "Missing value in from field!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				double f = Double.parseDouble(fromText.getText());
				UnitModifier fromUnit, toUnit;
				fromUnit = fromBox.getSelectedModifier();
				toUnit = toBox.getSelectedModifier();
				System.out.println("Selected stuff is: " + fromUnit + " -> " + toUnit);
				ISUUnit myUnit = ((ISUUnit) unitBox.getSelectedItem());
				try {
					toText.setText(Double.toString(ISUUnits.unitConvert(myUnit, fromUnit, toUnit, f)));
				} catch (UnitNotConvertible e) {
					JOptionPane.showMessageDialog(null, "The unit "+ myUnit +" cannot be converted!");
					return;
				}
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
			fromBox.addItem(unit);
			toBox.addItem(unit);
		}
	}
	private void loadUnitComboBox(){
		unitBox.removeAllItems();
		for(ISUUnit unit: ISUUnits.retrieveUnits((ISDimension) typeBox.getSelectedItem())){
			unitBox.addItem(new ComboBoxItem<>(unit.name(), unit));
		}
		if(unitBox.getItemCount() == 0){
			unitBox.setEnabled(false);
			loadModifiersComboBox(null);
		}else{
			unitBox.setEnabled(true);
			loadModifiersComboBox((ISUUnit) ((ComboBoxItem) unitBox.getSelectedItem()).getValue());
		}
	}
}

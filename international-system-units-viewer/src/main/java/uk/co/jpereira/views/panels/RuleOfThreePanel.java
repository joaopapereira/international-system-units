package uk.co.jpereira.views.panels;

import uk.co.jpereira.isu.ISUUnits;
import uk.co.jpereira.isu.units.BasicUnit;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.UnitModifier;
import uk.co.jpereira.isu.units.derived.DerivedUnit;
import uk.co.jpereira.views.MainView;
import uk.co.jpereira.views.utils.BasicUnitComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RuleOfThreePanel extends Panel {
	private JTextField upLeftText;
	private JTextField lowLeftText;
	private JTextField upRightText;
	private JTextField lowRightText;
	private JComboBox<BasicUnit> upperUnit, lowerUnit;
	BasicUnitComboBox upLeftUnitModifier, lowLeftUnitModifier, upRightUnitModifier, lowRightUnitModifier;
	public RuleOfThreePanel(){
		super();
		createInterface();
		loadComboBoxes();
	}
	private void loadComboBoxes(){
		BasicUnit[] theUnits = loadUnitCombo();
		loadUpperUnitModifiers(theUnits[0]);
		loadLowerUnitModifiers(theUnits[1]);
	}
	private BasicUnit[] loadUnitCombo(){
		BasicUnit[] result = new BasicUnit[2];
		lowerUnit.removeAllItems();
		upperUnit.removeAllItems();
		for(BasicUnit unit: ISUUnits.retrieveAllUnits()){
			lowerUnit.addItem((BasicUnit)unit.clone());
			upperUnit.addItem((BasicUnit)unit.clone());
		}
		result[0] = (BasicUnit)upperUnit.getSelectedItem();
		result[1] = (BasicUnit)lowerUnit.getSelectedItem();
		return result;
	}
	private void loadUpperUnitModifiers(final BasicUnit<?> unit){
		for(Class i: unit.getClass().getInterfaces()){
			if(i == DerivedUnit.class){
				upLeftUnitModifier.setVisible(false);
				upRightUnitModifier.setVisible(false);
				return;
			}
		}

		upLeftUnitModifier.setVisible(true);
		upRightUnitModifier.setVisible(true);
		upLeftUnitModifier.removeAllItems();
		upRightUnitModifier.removeAllItems();
		ISUUnit _unit = (ISUUnit)unit.clone();
		for(UnitModifier mod: UnitModifier.values()){
			_unit.setModifier(mod);
			//upLeftUnitModifier.addItem(new ComboBoxItem<UnitModifier>(_unit.getSmallName(), mod));
			upLeftUnitModifier.addItem(_unit);
			//upRightUnitModifier.addItem(new ComboBoxItem<UnitModifier>(_unit.getSmallName(), mod));
			upRightUnitModifier.addItem(_unit);
		}
	}
	private void loadLowerUnitModifiers(final BasicUnit<?> unit){
		for(Class i: unit.getClass().getInterfaces()){
			if(i == DerivedUnit.class){
				lowLeftUnitModifier.setVisible(false);
				lowRightUnitModifier.setVisible(false);
				return;
			}
		}
		lowLeftUnitModifier.setVisible(true);
		lowRightUnitModifier.setVisible(true);
		lowLeftUnitModifier.removeAllItems();
		lowRightUnitModifier.removeAllItems();
		ISUUnit _unit = (ISUUnit)unit.clone();
		for(UnitModifier mod: UnitModifier.values()){
			_unit.setModifier(mod);
			//lowLeftUnitModifier.addItem(new ComboBoxItem<UnitModifier>(_unit.getSmallName(), mod));
			//lowRightUnitModifier.addItem(new ComboBoxItem<UnitModifier>(_unit.getSmallName(), mod));
			lowLeftUnitModifier.addItem(_unit);
			lowRightUnitModifier.addItem(_unit);
		}
	}
	
	private void createInterface(){
		setSize(MainView.contentSize);
		setLayout(null);
		
		upperUnit = new JComboBox<BasicUnit>();
		upperUnit.setBounds(10, 135, 111, 20);

		upperUnit.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					loadUpperUnitModifiers((BasicUnit)event.getItem());
				}
			}
		});
		add(upperUnit);
		
		lowerUnit = new JComboBox<BasicUnit>();
		lowerUnit.setBounds(10, 183, 111, 20);
		add(lowerUnit);
		lowerUnit.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					loadLowerUnitModifiers((BasicUnit)event.getItem());
				}
			}
		});
		
		upLeftText = new JTextField();
		upLeftText.setColumns(10);
		upLeftText.setBounds(136, 135, 86, 20);
		add(upLeftText);
		
		lowLeftText = new JTextField();
		lowLeftText.setColumns(10);
		lowLeftText.setBounds(136, 183, 86, 20);
		add(lowLeftText);
		
		upRightText = new JTextField();
		upRightText.setColumns(10);
		upRightText.setBounds(405, 135, 86, 20);
		add(upRightText);
		
		lowRightText = new JTextField();
		lowRightText.setEnabled(false);
		lowRightText.setColumns(10);
		lowRightText.setBounds(405, 183, 86, 20);
		add(lowRightText);

		upLeftUnitModifier = new BasicUnitComboBox();
		upLeftUnitModifier.setBounds(224, 135, 80, 20);
		add(upLeftUnitModifier);

		lowLeftUnitModifier = new BasicUnitComboBox();
		lowLeftUnitModifier.setBounds(224, 183, 80, 20);
		add(lowLeftUnitModifier);

		upRightUnitModifier = new BasicUnitComboBox();
		upRightUnitModifier.setBounds(492, 135, 80, 20);
		add(upRightUnitModifier);

		lowRightUnitModifier = new BasicUnitComboBox();
		lowRightUnitModifier.setBounds(492, 183, 80, 20);
		add(lowRightUnitModifier);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(405, 240, 89, 23);
		add(btnCalculate);
		btnCalculate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				double leftUp, rightUp, leftDown;
				if(upLeftText.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "Need to fill upper left variable!");
					upLeftText.requestFocus();
					return;
				}
				try{
					leftUp = Double.parseDouble(upLeftText.getText());
				}catch( NumberFormatException exp){
					JOptionPane.showMessageDialog(null, "Upper left variable must be a number!");
					upLeftText.requestFocus();
					return;
				}
				if(upRightText.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "Need to fill upper right variable!");
					upRightText.requestFocus();
					return;
				}
				try{
					rightUp = Double.parseDouble(upRightText.getText());
				}catch( NumberFormatException exp){
					JOptionPane.showMessageDialog(null, "Upper right variable must be a number!");
					upRightText.requestFocus();
					return;
				}
				if(lowLeftText.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "Need to fill lower left variable!");
					lowLeftText.requestFocus();
					return;
				}
				try{
					leftDown = Double.parseDouble(lowLeftText.getText());
				}catch( NumberFormatException exp){
					JOptionPane.showMessageDialog(null, "Lower left variable must be a number!");
					lowLeftText.requestFocus();
					return;
				}
				lowRightText.setText(Double.toString(
						ISUUnits.ruleOfThree((BasicUnit) upperUnit.getSelectedItem(),
								upLeftUnitModifier.getSelectedModifier(), leftUp,
								upRightUnitModifier.getSelectedModifier(), rightUp,
								(BasicUnit) lowerUnit.getSelectedItem(),
								lowLeftUnitModifier.getSelectedModifier(), leftDown,
								lowRightUnitModifier.getSelectedModifier())));
			}
		});
	}
}

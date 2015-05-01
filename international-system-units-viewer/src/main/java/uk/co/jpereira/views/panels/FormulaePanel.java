package uk.co.jpereira.views.panels;

import net.sourceforge.jeuclid.swing.JMathComponent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import uk.co.jpereira.isu.Formulae;
import uk.co.jpereira.isu.ISURepresentable;
import uk.co.jpereira.isu.ISUUnits;
import uk.co.jpereira.isu.JSONRepresentation;
import uk.co.jpereira.isu.exception.MissingParameters;
import uk.co.jpereira.isu.units.BasicUnit;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.UnitModifier;
import uk.co.jpereira.views.MainView;
import uk.co.jpereira.views.utils.BasicUnitComboBox;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class ArgumentClass extends JPanel {
	private ISURepresentable unit;
	BasicUnitComboBox unitModifierCombo;
	JTextField amountField;

	public ArgumentClass(JPanel parent, int yStart, JSONObject unit) {
		super();
		JLabel label = new JLabel("Amount");
		amountField = new JTextField();
		JLabel lblUnitName = new JLabel(unit.toString());
		unitModifierCombo = new BasicUnitComboBox();
		unitModifierCombo.setPreferredSize(new Dimension(70, 20));
		add(label);

		add(amountField);
		amountField.setColumns(10);
		amountField.setText("");
		add(lblUnitName);

		try {
			this.unit = (ISURepresentable) ((Class) unit.get(JSONRepresentation.CLASS)).newInstance();
			this.unit.loadFromRepresentation(unit);
			lblUnitName.setText(this.unit.toString());
			if (this.unit instanceof ISUUnit) {
				ISUUnit k = (ISUUnit) ((ISUUnit) this.unit).clone();
				int i = 0;
				for (UnitModifier mod : UnitModifier.values()) {
					k.setModifier(mod);
					unitModifierCombo.addItem(k);
					if (((ISUUnit) this.unit).getModifier() == k.getModifier()) {
						unitModifierCombo.setSelectedIndex(i);
					}
					i++;
				}
				add(unitModifierCombo);
			}
			setLocation(0, yStart);
			parent.add(this);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ISURepresentable getUnit() {
		if ((this.unit instanceof ISUUnit)) {
			((ISUUnit) unit).setModifier(unitModifierCombo.getSelectedModifier());
		}
		if (amountField.getText().length() > 0)
			unit.setAmount(Double.parseDouble(amountField.getText()));
		else
			unit.setAmount(null);
		return this.unit;
	}
}


public class FormulaePanel extends JPanel {
	private JTree tree;
	JMathComponent formulae;
	JPanel formulaeAttributes;
	private JTextField textField_1;
	private List<ArgumentClass> arguments;

	public FormulaePanel() {
		setLayout(null);
		setSize(MainView.contentSize);
		arguments = new ArrayList();

		tree = new JTree();
		tree.setBounds(0, 0, 150, (int) MainView.contentSize.getHeight());

		tree.setPreferredSize(new Dimension(150, (int) MainView.contentSize.getHeight() - 50));
		JScrollPane allFormulae = new JScrollPane(tree, 
												JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
												JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		allFormulae.setBounds(0, 0, 150, (int) MainView.contentSize.getHeight());
		allFormulae.setPreferredSize(new Dimension(150, (int) MainView.contentSize.getHeight() - 50));
		JPanel formulaeShowPanel = new JPanel();
		formulaeShowPanel.setBounds(tree.getWidth(), 0, (int) MainView.contentSize.getWidth() - tree.getWidth(), (int) MainView.contentSize.getHeight() / 4);
		add(formulaeShowPanel);
		formulae = new JMathComponent();
		formulae.setContent("<math xmlns=\"http://www.w3.org/1998/Math/MathML\">BAMM</math>");
		formulae.setBounds(0, 0, formulaeShowPanel.getWidth(), 100);
		formulae.setVisible(true);
		formulaeShowPanel.setLayout(null);
		formulaeShowPanel.add(formulae);
		loadTree();
		add(allFormulae);

		formulaeAttributes = new JPanel();
		formulaeAttributes.setBounds(0,
				0,
				(int) MainView.contentSize.getWidth() - formulaeShowPanel.getX(),
				(int) MainView.contentSize.getHeight());
		JScrollPane attributesPanel = new JScrollPane(formulaeAttributes);
		formulaeAttributes.setLayout(null);

		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(178, 11, 89, 23);
		formulaeAttributes.add(btnCalculate);
		btnCalculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JSONObject obj = null;
				int i = 0;
				for (ArgumentClass c : arguments) {
					if (obj == null) {
						obj = c.getUnit().getRepresentation();
						System.out.println("My representation: " + obj);
						if (null != obj.get(JSONRepresentation.SUBUNITS))
							((JSONArray) obj.get(JSONRepresentation.SUBUNITS)).clear();
					} else {
						((JSONArray) obj.get(JSONRepresentation.SUBUNITS)).add(c.getUnit().getRepresentation());
						i++;
					}
				}
				arguments.get(0).getUnit().loadFromRepresentation(obj);
				try {
					ISURepresentable currentUnit = (ISURepresentable) arguments.get(0).getUnit();
					currentUnit.solve();
					formulae.setContent("<math xmlns=\"http://www.w3.org/1998/Math/MathML\">" + currentUnit.toMathML() + "</math>");
				} catch (MissingParameters e) {
					JOptionPane.showMessageDialog(null, "Missing arguments, please add more arguments so that the application can calculate the values!", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});


		attributesPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		attributesPanel.setBounds(formulaeShowPanel.getX(),
				formulaeShowPanel.getY() + formulaeShowPanel.getHeight(),
				(int) MainView.contentSize.getWidth() - formulaeShowPanel.getX(),
				(int) MainView.contentSize.getHeight() - formulaeShowPanel.getHeight());
		attributesPanel.setPreferredSize(new Dimension(150, (int) MainView.contentSize.getHeight() - 50));
		add(attributesPanel);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
	                       tree.getLastSelectedPathComponent();

			    if (node == null)
			    //Nothing is selected.     
			    return;
		
			    Object nodeInfo = node.getUserObject();
			    if (node.isLeaf()) {
					formulaeAttributes.setVisible(false);
					ISURepresentable unit = (ISURepresentable) nodeInfo;
					formulae.setContent("<math xmlns=\"http://www.w3.org/1998/Math/MathML\">" + unit.toMathML() + "</math>");
					JSONObject repr = unit.getRepresentation();
					for (ArgumentClass arg : arguments) {
						formulaeAttributes.remove(arg);
						arg.setVisible(false);
					}
					arguments.clear();
					int i = 0;
					ArgumentClass arg;
					arg = new ArgumentClass(formulaeAttributes, 27, repr);
					if (unit.isReversible()) {
						arg.setBounds(0, 40, formulaeAttributes.getWidth(), 50);
						formulaeAttributes.add(arg);
						i++;
					}
					arguments.add(arg);

					for (Object _obj : (JSONArray) repr.get("subunits")) {
						arg = new ArgumentClass(formulaeAttributes, 27, (JSONObject) _obj);
						arg.setBounds(0, 40 + 50 * i, formulaeAttributes.getWidth(), 50);
						arguments.add(arg);
						i++;
					}

					formulaeAttributes.setVisible(true);
				}
			}
		});
	}
	private void loadTree(){
		DefaultMutableTreeNode top =
		        new DefaultMutableTreeNode("Formulae");

		DefaultMutableTreeNode units =
		        new DefaultMutableTreeNode("Units");
		top.add(units);
		for(BasicUnit unit: ISUUnits.retrieveDerived()){
			units.add(new DefaultMutableTreeNode(unit));
		}
		DefaultMutableTreeNode formulae;
		HashMap<String, List<Formulae>> allFormulae = ISUUnits.getFormulae();
		for (String nodeName : allFormulae.keySet()) {
			formulae = new DefaultMutableTreeNode(nodeName);
			top.add(formulae);
			for (Formulae form : allFormulae.get(nodeName)) {
				formulae.add(new DefaultMutableTreeNode(form));
			}
		}

		tree.setModel(new DefaultTreeModel(top));
	}
}

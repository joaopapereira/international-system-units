package uk.co.jpereira.views.panels;

import java.awt.Dimension;

import javax.swing.JPanel;

import net.sourceforge.jeuclid.swing.JMathComponent;

import javax.swing.JTree;

import uk.co.jpereira.isu.ISUUnits;
import uk.co.jpereira.isu.units.BasicUnit;
import uk.co.jpereira.isu.units.derived.DerivedUnit;
import uk.co.jpereira.isu.units.derived.MolarMass;
import uk.co.jpereira.views.MainView;

import javax.swing.JScrollPane;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.border.MatteBorder;
import java.awt.Color;
public class FormulaePanel extends JPanel {
	private JTree tree;
	JMathComponent formulae;
	public FormulaePanel() {
		setLayout(null);
		setSize(MainView.contentSize);

		tree = new JTree();
		tree.setBounds(0, 0, 150, (int)MainView.contentSize.getHeight());

		tree.setPreferredSize(new Dimension(150, (int)MainView.contentSize.getHeight()-50));
		JScrollPane scrollPane = new JScrollPane(tree, 
												JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
												JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 150, (int)MainView.contentSize.getHeight());
		scrollPane.setPreferredSize(new Dimension(150, (int)MainView.contentSize.getHeight()-50));
		JPanel panel = new JPanel();
		panel.setBounds(tree.getWidth(), 0, (int)MainView.contentSize.getWidth()-tree.getWidth(), (int)MainView.contentSize.getHeight());
		add(panel);
		formulae = new JMathComponent();
		formulae.setContent("<math xmlns=\"http://www.w3.org/1998/Math/MathML\">BAMM</math>");
		formulae.setBounds(0, 0, panel.getWidth(), 100);
		formulae.setVisible(true);
		panel.setLayout(null);
		panel.add(formulae);
		loadTree();
		add(scrollPane);
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
			    	DerivedUnit unit = (DerivedUnit)nodeInfo;
			    	formulae.setContent("<math xmlns=\"http://www.w3.org/1998/Math/MathML\">" + unit.calculationFormulae() + "</math>");
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
		tree.setModel(new DefaultTreeModel(top));
	}
}

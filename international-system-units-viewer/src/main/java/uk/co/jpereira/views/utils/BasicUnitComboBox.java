package uk.co.jpereira.views.utils;

import uk.co.jpereira.isu.units.BasicUnit;
import uk.co.jpereira.isu.units.ISUUnit;
import uk.co.jpereira.isu.units.UnitModifier;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Joao Pereira on 01/05/2015.
 */
public class BasicUnitComboBox extends JComboBox<BasicUnit> {
    private ArrayList<BasicUnit> units;
    private BasicUnitRenderer renderer;

    public BasicUnitComboBox() {
        super();
        renderer = new BasicUnitRenderer();
        renderer.setPreferredSize(new Dimension(50, 50));
        units = new ArrayList<>();
        setRenderer(renderer);
    }

    public BasicUnit getSelectedItem() {
        return (BasicUnit) super.getSelectedItem();
    }

    public UnitModifier getSelectedModifier() {
        if (getSelectedItem() instanceof ISUUnit)
            return ((ISUUnit) getSelectedItem()).getModifier();
        return null;
    }

    @Override
    public void setPreferredSize(Dimension dim) {
        super.setPreferredSize(dim);
        renderer.setPreferredSize(dim);
    }

    @Override
    public void addItem(BasicUnit unit) {
        units.add((BasicUnit) unit.clone());
        super.addItem((BasicUnit) unit.clone());
    }

    class BasicUnitRenderer
            extends JLabel
            implements ListCellRenderer {
        private Font uhOhFont;

        public BasicUnitRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        /**
         * Return a component that has been configured to display the specified
         * value. That component's <code>paint</code> method is then called to
         * "render" the cell.  If it is necessary to compute the dimensions
         * of a list because the list cells do not have a fixed size, this method
         * is called to generate a component on which <code>getPreferredSize</code>
         * can be invoked.
         *
         * @param list         The JList we're painting.
         * @param value        The value returned by list.getModel().getElementAt(index).
         * @param index        The cells index.
         * @param isSelected   True if the specified cell was selected.
         * @param cellHasFocus True if the specified cell has the focus.
         * @return A component whose paint() method will render the specified value.
         * @see JList
         * @see ListSelectionModel
         * @see ListModel
         */
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            BasicUnit unit = (BasicUnit) value;
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            if (unit == null) {
                setUhOhText("No unit",
                        list.getFont());
                setIcon(null);
            } else {
                setIcon(new ImageIcon(MathML2Image.convert(unit.getSmallNameMathML())));
                setUhOhText("",
                        list.getFont());
            }
            return this;
        }

        //Set the font and text when no image was found.
        protected void setUhOhText(String uhOhText, Font normalFont) {
            if (uhOhFont == null) { //lazily create this font
                uhOhFont = normalFont.deriveFont(Font.ITALIC);
            }
            setFont(uhOhFont);
            setText(uhOhText);
        }
    }
}

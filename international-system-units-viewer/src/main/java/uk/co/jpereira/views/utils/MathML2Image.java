package uk.co.jpereira.views.utils;

import net.sourceforge.jeuclid.swing.JMathComponent;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by blue on 01/05/2015.
 */
public class MathML2Image {
    public static BufferedImage convert(String mathmlText) {
        JMathComponent formulae1 = new JMathComponent();
        formulae1.setContent("<math xmlns=\"http://www.w3.org/1998/Math/MathML\">" + mathmlText + "</math>");
        formulae1.setBounds(0, 0, 89, 23);
        formulae1.setVisible(true);
        net.sourceforge.jeuclid.converter.Converter c = net.sourceforge.jeuclid.converter.Converter.getInstance();
        try {
            return c.render(formulae1.getDocument(), formulae1.getParameters());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

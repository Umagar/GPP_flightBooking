package flightbooking;

import javax.swing.*;
import javax.swing.text.*;

/**
 * 
 * @author Jesper Bengtson
 *
 */
public class NumberField extends JTextField {
	
	public NumberField(int def, int size, int min, int max) {
		super(Integer.toString(def), size);
		((AbstractDocument)getDocument()).setDocumentFilter(new NumericFilter(min, max));
	}
}


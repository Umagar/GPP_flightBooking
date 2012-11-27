package flightbooking;

import javax.swing.text.*;

/**
 * 
 * @author Jesper Bengtson
 *
 */
public class NumericFilter extends DocumentFilter {

    private int min = 0; 
    private int max = -1;
    
    public NumericFilter(int min, int max) {
        super();
        this.min = min;
        this.max = max;
    }
    
    private String getText(FilterBypass fb) 
                   throws BadLocationException {
        Document doc = fb.getDocument();
        return doc.getText(0, doc.getLength());
    }
    
    private void setText(FilterBypass fb, String s, AttributeSet attr) 
                 throws BadLocationException {
        fb.replace(0, fb.getDocument().getLength(), s, attr); 
    }
    
    private void adjustRange(FilterBypass fb, String revert, AttributeSet attr) 
                   throws BadLocationException {
        String numString = getText(fb);
        if(numString.isEmpty())
            setText(fb, revert, attr);
        else {
            int n = Integer.parseInt(numString);
    
            if((max >= 0 && n > max) || n < min)
               setText(fb, revert, attr);
            else
               setText(fb, Integer.toString(n), attr);
        }
        
    }

    public void insertString(FilterBypass fb, 
                             int offset, String text, AttributeSet attr) 
                             throws BadLocationException {  
       String revert = getText(fb);
       fb.insertString(offset, text.replaceAll("[^0-9]+", ""), attr);
       adjustRange(fb, revert, attr);
    }      

    public void replace(FilterBypass fb, int offset, int length,  
                        String text, AttributeSet attr)
                        throws BadLocationException {  
       String revert = getText(fb);
       fb.replace(offset, length, text.replaceAll("[^0-9]+", ""), attr);  
       adjustRange(fb, revert, attr);
    }  
}

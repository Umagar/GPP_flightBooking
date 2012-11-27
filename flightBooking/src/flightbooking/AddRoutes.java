package flightbooking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddRoutes {
	private JFrame frame;
	
	private NumberField from = new NumberField(1, 5, 1, 100);
	private NumberField to = new NumberField(1, 5, 1, 100);
	private static AddRoutes instance;
	
	private AddRoutes(){
		makeFrame();
	}
	
	public static AddRoutes getInstance(){
		if(instance != null)
			return instance;
		else {
			instance = new AddRoutes();
			return instance;
		}
	}
	
	private void makeFrame() {
		frame = new JFrame("Add Route");
		
		makeContent();
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void makeContent() {
		Container contentPane = frame.getContentPane();
		frame.setLayout(new BorderLayout());
		
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		
		JPanel labels = new JPanel();
		labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
		
		JLabel labelFrom = new JLabel("From airport id:", JLabel.LEFT);
		JLabel labelTo = new JLabel("To airport id:", JLabel.LEFT);
		
		labels.add(labelFrom);
		labels.add(labelTo);
		
		JPanel fields = new JPanel();
		fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
		
		fields.add(from);
		fields.add(to);
		
		content.add(labels);
		content.add(fields);

        JButton bcancel = new JButton("Cancel");
        JButton bnew = new JButton("Create route");
        
        bcancel.addActionListener(new CancelActionListener());
        bnew.addActionListener(new NewActionListener());

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(bcancel);
        buttons.add(bnew);
        
        contentPane.add(content, BorderLayout.NORTH);
        contentPane.add(buttons, BorderLayout.SOUTH);
	}


	class NewActionListener implements ActionListener {
		@Override
	    public void actionPerformed(ActionEvent event) {
	        try {
	        	if(to != from){
		        	Database dbFromTo = new Database();
		        	dbFromTo.execute("INSERT INTO route (startPoint, destination)VALUES(" +
		        			Integer.parseInt(from.getText()) + ", " + Integer.parseInt(to.getText()) +
		        			")");
		        	dbFromTo.close();
		        	Database dbToFrom = new Database();
		        	dbToFrom.execute("INSERT INTO route (startPoint, destination)VALUES(" +
		        			Integer.parseInt(to.getText()) + ", " + Integer.parseInt(from.getText()) +
		        			")");
		        	dbFromTo.close();
	        	}
	        } catch (NumberFormatException e) {
	            System.out.println(e);
	        } catch (SQLException e) {
	            System.out.println(e);
			}
	    }
	}
	
	class CancelActionListener implements ActionListener {
		@Override
	    public void actionPerformed(ActionEvent event) {
	        frame.dispose();
	    }
	}

}
package flightbooking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddPlanes {
	private JFrame frame;
	
	private NumberField type = new NumberField(1, 5, 1, 100);
	private NumberField number = new NumberField(1, 5, 1, 10000);
	private NumberField airline = new NumberField(1, 5, 1, 100);
	
	private static AddPlanes instance;
	
	private AddPlanes(){
		makeFrame();
	}

	public static AddPlanes getInstance(){
		if(instance != null)
			return instance;
		else {
			instance = new AddPlanes(); 
			return instance;
		}
	}
	
	private void makeFrame() {
		frame = new JFrame("Add Plane");
		
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
		
		JLabel labelType = new JLabel("Plane type:", JLabel.LEFT);
		JLabel labelNumber = new JLabel("Plane number:", JLabel.LEFT);
		JLabel labelAirline = new JLabel("Airline:", JLabel.LEFT);
		
		labels.add(labelType);
		labels.add(labelNumber);
		labels.add(labelAirline);
		
		JPanel fields = new JPanel();
		fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
		
		fields.add(type);
		fields.add(number);
		fields.add(airline);
		
		content.add(labels);
		content.add(fields);

        JButton bcancel = new JButton("Cancel");
        JButton bnew = new JButton("Create plane");
        
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
	        	Database db = new Database();
	        	ResultSet rs = db.execute("SELECT * FROM flight WHERE flightNumber = " + Integer.parseInt(number.getText()));
	        	if(!rs.next()){
		        	Database dbInsert = new Database();
		        	dbInsert.execute("INSERT INTO flight (flightType, flightNumber, airline)VALUES(" +
		        			Integer.parseInt(type.getText()) + ", " + Integer.parseInt(number.getText()) +
		        			", " + Integer.parseInt(airline.getText()) + ")");
		        	dbInsert.close();
	        	}
	        	else {
	        		System.out.println("Already exists");
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
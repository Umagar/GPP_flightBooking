package flightbooking;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Welcome {

	public static JPanel makeWelcome() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel image = new JLabel(new ImageIcon("ressources/tokama.png"));
		imagePanel.add(image);
		panel.add(imagePanel);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel label = new JLabel("Welcome to ToKaMa Flight Booking 42 PRO");
		labelPanel.add(label);
		panel.add(labelPanel);

		Border border = BorderFactory.createEmptyBorder(15,15,15,15);
		panel.setBorder(border);
		return panel;
	}
}

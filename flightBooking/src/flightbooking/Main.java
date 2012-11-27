package flightbooking;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.*;


public class Main {

	private JFrame frame;
    Container contentPane;
    JPanel centerScreen = Welcome.makeWelcome();
    
    
	private static Main instance;

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		//TestDatabase tdb = new TestDatabase();
		Main.getInstance();
		/* tdb.getAllRoutes();
		 * This method will write a file routes.txt containing all routes
		 */
	}
	
	/*
	 * Constructor for class Main
	 * Makes the frame
	 */
	private Main(){
		makeFrame();
	}
	
	/**
	 * Return the instance of the Main
	 * object. If no instance is found
	 * create a new instance.
	 * @return The Main object
	 */
	public static Main getInstance(){
		if(instance != null)
			return instance;
		else {
			instance = new Main();
			return instance;
		}	
	}
	
	/**
	 * Creates the frame and populates
	 * it with a menu and a contentpane
	 */
	private void makeFrame(){
		frame = new JFrame("ToKaMa Flight Booking 42 PRO");
		
		makeMenu();
		fillContentPane();
		
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Creates a menubar and fills it with
	 * menus. The menus are then filled with
	 * menu items each assigned a hotkey
	 */
	private void makeMenu(){
		final int SHORT_CUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenu adminMenu = new JMenu("Admin");
		menuBar.add(adminMenu);
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		makeMenuItem(fileMenu, "Quit", new QuitActionListener(), KeyEvent.VK_Q, SHORT_CUT_MASK);
		
        makeMenuItem(adminMenu, "Add Routes", new RouteActionListener(), KeyEvent.VK_R, SHORT_CUT_MASK);
        makeMenuItem(adminMenu, "Add Planes", new PlaneActionListener(), KeyEvent.VK_P, SHORT_CUT_MASK);
        
        makeMenuItem(helpMenu, "About ToKaMa Flight Booking", new AboutActionListener(), KeyEvent.VK_A, SHORT_CUT_MASK);
	}
	
	/**
	 * Creates a menu item
	 * @param menu The menu to put the item into
	 * @param name The name of the item
	 * @param listener The ActionListener to listen to the item
	 * @param keyEvent The KeyEvent associated to the hotkey
	 * @param shortCut The SHORT_CUT_MASK
	 */
	private void makeMenuItem(JMenu menu, String name, ActionListener listener, int keyEvent, int shortCut) {
        JMenuItem item = new JMenuItem(name);
        item.setAccelerator(KeyStroke.getKeyStroke(keyEvent, shortCut));
        item.addActionListener(listener);
        menu.add(item);
    }
	
	/**
	 * Fills the contentPane with the content for the
	 * program
	 */
	private void fillContentPane(){
		//Sets one size for all buttons in the contentPane
		Dimension buttonSize = new Dimension(140,20);
		
		contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
		
        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout(FlowLayout.CENTER));
        footer.setBackground(Color.WHITE);
        JLabel footerText = new JLabel("ToKaMa Flight Booking by Tobias Kristiansen," +
        		" Kasper Stig and Simon Malone");
        footer.add(footerText);
        
        contentPane.add(makeToolbar(buttonSize), BorderLayout.WEST);
        contentPane.add(footer, BorderLayout.SOUTH);
        contentPane.add(centerScreen, BorderLayout.CENTER);
        
	}
	
	/**
	 * Creates the left hand side toolbar
	 * @param buttonSize The size of the buttons in the toolbar
	 * @return The toolbar component as a JPanel
	 */
	private JPanel makeToolbar(Dimension buttonSize){
		
		JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(0, 1, 0, 3));

		JButton welcome = new JButton("Home");
		welcome.setPreferredSize(buttonSize);
		welcome.addActionListener(new WelcomeActionListener());
		JButton newBooking = new JButton("New booking");
		newBooking.setPreferredSize(buttonSize);
		newBooking.addActionListener(new BookingActionListener());
		JButton changeBooking = new JButton("Change booking");
		changeBooking.setPreferredSize(buttonSize);
		
		
		toolbar.add(welcome);
        toolbar.add(newBooking);
        toolbar.add(changeBooking);
        
        JPanel flow = new JPanel();
        flow.add(toolbar);
        
        return flow;
	}
	
	
	public Dimension getPreferredSize(){
		return new Dimension(100,100);
	}
	
	public Dimension getMinimumSize(){
		return getPreferredSize();
	}
	
	
	//--- --- --- ActionListeners from here --- --- ---//
	
	class QuitActionListener implements ActionListener {
		@Override
	    public void actionPerformed(ActionEvent event) {
			frame.dispose();
	    }
	}
	
	class RouteActionListener implements ActionListener {
		@Override
	    public void actionPerformed(ActionEvent event) {
			AddRoutes.getInstance();
	    }
	}
	
	class PlaneActionListener implements ActionListener {
		@Override
	    public void actionPerformed(ActionEvent event) {
			AddPlanes.getInstance();
	    }
	}

	class AboutActionListener implements ActionListener {
		@Override
	    public void actionPerformed(ActionEvent event) {
			JOptionPane.showMessageDialog(frame, "ToKaMa Flight Booking is the premier Flight Booking system in the world and the universe and beyond");
	    }
	}
	
	class BookingActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event){
			contentPane.remove(centerScreen);
			centerScreen = NewBooking.makeBooking();
			contentPane.add(centerScreen, BorderLayout.CENTER);
			frame.pack();
		}
	}
	
	class WelcomeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event){
			contentPane.remove(centerScreen);
			centerScreen = Welcome.makeWelcome();
			contentPane.add(centerScreen, BorderLayout.CENTER);
			frame.pack();
		}
	}
}
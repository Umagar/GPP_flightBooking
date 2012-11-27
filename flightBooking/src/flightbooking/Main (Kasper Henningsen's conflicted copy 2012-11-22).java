
package flightbooking;

import java.sql.*;

public class Main {
	private final Connection connection;
	private final Statement dbStatement;
	
	public Main() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException exn) {
			System.out.println("Could not find the search path: " + exn);
		}
		connection = DriverManager.getConnection("jdbc:mysql://mysql.itu.dk/gppBachelorProjekt", "tokama", "qwert1234");
		dbStatement = connection.createStatement();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Luder");
		

	}

}

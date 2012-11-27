
package flightbooking;

import java.sql.*;

public class Database {
	private final Connection connection;
	private final Statement dbStatement;
	
	public Database() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException exn) {
		}
		connection = DriverManager.getConnection("jdbc:mysql://mysql.itu.dk/gppBachelorProjekt", "tokama", "qwert1234");
		dbStatement = connection.createStatement();
	}
	
	public ResultSet execute(String cmd) throws SQLException {
		boolean ok = dbStatement.execute(cmd);
		if(ok) {
			return dbStatement.getResultSet();
		}
		else {
			return null;
		}
	}
	
	public void close() throws SQLException {
		if(connection != null){
			connection.close();
		}
	}

}

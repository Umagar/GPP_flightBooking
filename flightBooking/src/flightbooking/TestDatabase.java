package flightbooking;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class TestDatabase {

	Database db;
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public TestDatabase() throws SQLException {	
		db = new Database();	
	}
	
	public void getAllRoutes() throws SQLException{
		ResultSet rs = db.execute("SELECT * FROM route AS route, location AS " +
				"fromLocation, location AS toLocation WHERE route.startPoint = fromLocation.id AND route.destination = toLocation.id ORDER BY route.id");
		String text = "";
		while(rs.next()){
			text += "Route " + rs.getString("route.id") + " goes from " + rs.getString("fromLocation.city") +
					"(" + rs.getString("fromLocation.longName") + ") to " + rs.getString("toLocation.city") +
					"(" + rs.getString("toLocation.longName") + ")\r\n";
		}
		db.close();
		

	    try {
	        FileWriter writer = new FileWriter("../routes.txt");
	        writer.write(text);
	        writer.close();
	    }
	    catch(FileNotFoundException e) {System.out.println(e.getMessage());
	    }
	    catch(IOException e) {System.out.println(e.getMessage());
	    }
	}
	
	public void getAllPlanes() throws SQLException{

		ResultSet rs = db.execute("SELECT * FROM airlines, flight, flightType " +
				"WHERE airlines.id = flight.airline AND flight.flightType = flightType.id");
		while(rs.next()){
			System.out.println(rs.getString("code") + rs.getString("flightNumber") + " is a " +
					rs.getString("producent") + " " + rs.getString("model") + " owned by " +
					rs.getString("name") + " from " + rs.getString("country"));
		}
		db.close();
	}

}

package live.goob.project.connectionutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
	
	public static Connection getConnection() throws SQLException {
		/*
		 * The getConnection method of the DriverManager
		 * uses the following syntax:
		 * getConnection(db_url, user name, password);
		 * 
		 * url syntax: "jdbc:postgresql://[host_location]:[port]/[db_name]"
		 */
		String url = ConnectionCredentials.link;
		String user = ConnectionCredentials.username;
		String pass = ConnectionCredentials.password;
		return DriverManager.getConnection(url, user, pass);
	}

	// This main method is used to test our connection!
		public static void main(String[] args) {
			// try with resources allows you to pass a resource argument to a try statement
			// in this case the
			try (Connection conn = ConnectionUtility.getConnection()) {
				System.out.println("The Connection was successful!"); // if this works, our connection was established
				conn.close();
			} catch(SQLException e) {
				System.out.println("An Error Occured");
				e.printStackTrace();
			}
			
		}
		
}

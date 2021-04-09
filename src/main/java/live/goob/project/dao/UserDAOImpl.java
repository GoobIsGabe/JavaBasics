package live.goob.project.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import live.goob.project.model.User;
import live.goob.project.connectionutil.ConnectionUtility;

public class UserDAOImpl implements UserDAO{
	
	@Override
	public User selectUser(String name) {
		User usr = new User();
		try {
			//selecting the entries from the database with your name...

			//More like TRY to just call connection class
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Harper12");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM project.users WHERE user_name =?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			// Look at the values from the result set, and use the set methods to
			// populate the appropriate fields
			while (rs.next()) {
				// When we use the get methods for our result set
				// we reference the column associated from our table
				// in our case:
				// emp_id is column 1
				// emp_name is column 2
				// emp_salary is column 3
				// emp_title is column 4
				usr.setUser_id(rs.getInt(1));
				usr.setUser_name(rs.getString(2));
				usr.setUser_pass(rs.getString(3));
				usr.setUser_level(rs.getString(4));
			}
		}
		catch (SQLException e) {
			System.out.println("Something went wrong when trying to contact DB");
			e.printStackTrace();
			return null;
		}		
		// We want to return the created employee at the end of the method...
		return usr;
	}

	
	@Override
	public User selectUser(Integer id) {
		// This is our model that represents the data from our database
				User usr = null;
				
				// The ResultSet is a representation of the data from our DB
				ResultSet rs = null;
				
				// JDBC offers 3 statements, Simple, Prepared and Callable. We use
				// PreparedStatements when we want to pass parameters to the statement itself
				PreparedStatement ps = null;
				try (Connection conn = ConnectionUtility.getConnection()) {
					// SELECT * FROM examples.employees WHERE emp_id = 1000;
					ps = conn.prepareStatement("SELECT * FROM project.users WHERE user_id=?");
					ps.setInt(1, id);
					rs = ps.executeQuery();

					while (rs.next()) {
						usr = new User(
								rs.getInt(1), 
								rs.getString(2), 
								rs.getString(3),
								rs.getString(4)
								);
						
					}

				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
				return usr;
		
	}



	public static void updateTable() throws SQLException{
		Connection con = ConnectionUtility.getConnection();
		//update table
		String viewTable = "SELECT * FROM project.users";
		ResultSet rs = null;
		
		UserDAOImpl user = new UserDAOImpl();
		
		try (PreparedStatement viewStuff = con.prepareStatement(viewTable)){
			//updateTitle.executeQuery();
			//System.out.println(updateTitle);
			//con.commit();
			
						
			
			rs = viewStuff.executeQuery();
			
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue + " " + rsmd.getColumnName(i));
			    }
			    System.out.println("");
			}
			
			
			//Prints out the database location and then memory address,
			//not the actual values I guess we need metadata from snippet above somehow
			System.out.println(rs);
			
			
		}catch (SQLException e) {
			System.out.println("An Error Occured");
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) throws SQLException {
		updateTable();
			
	}

	
	@Override
	public Boolean insertIntoUsers(User usr) {
		System.out.println("No entries exist with your name, let's enter your name into  the database!");
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			ps = conn.prepareStatement("INSERT INTO project.users (user_name, user_pass, user_level)"
					+ "VALUES(?,?,'t')");//VALUES( same # of inputs as columns)
			
			// INSERT INTO employees VALUES (emp_id, emp_name, emp_salary, emp_title);
			// INSERT INTO employees VALUES (14, "John", 7604190.00, "Worker");
//			ps.setInt(1, usr.getUser_id());
			ps.setString(1, usr.getUser_name());
			ps.setString(2, usr.getUser_pass());
			
			//BAM SQL has been sent from prepared statement down below
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

	@Override
	public List<User> selectAllUsers() {
		// regular statements should not be used if you need data
				// from a user location to prevent SQL Injection!
		System.out.println("We're selecting all Users!");
				Statement stmt = null;
				ResultSet rs = null;
				ArrayList<User> users = new ArrayList<User>();
				// We use try-with-resources to establish a connection
				try (Connection conn = ConnectionUtility.getConnection()) {
					// If the connection was successful:
					String query = "SELECT * FROM project.users";
					stmt = conn.createStatement();
					// Result Set is the information obtained from the query
					// A Set is a collection that does not contain duplicates!
					rs = stmt.executeQuery(query);
					/*
					 * rs.next() will return true if there is still information from our result set
					 * we have not looked through. Each time next() is called, it will look at the
					 * 'next' piece of information inside of our collection of data.
					 */
					while (rs.next()) {
						User emp = new User();
//						emp.setUser_id(rs.getInt(1));
						emp.setUser_name(rs.getString(1));
						emp.setUser_pass(rs.getString(2));
			
						users.add(emp);
						System.out.println(emp);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
				return users;
	}

	@Override
	public Boolean removeUser(Integer id) {
		System.out.println("We're removing a user now...");
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection();){
			String sql = "DELETE FROM project.users WHERE user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			/*
			 * the executeUpdate method will return a numerical value associated
			 * with how many rows were effected by an insert/update/delete
			 * operation
			 *
			 * i.e: if 15 rows were effected, then 15 will be returned by executeUpdate()
			 *
			 * If No rows were effected, this would indicate the query was either
			 * not successful, or information was not correctly provided
			 */
			if (ps.executeUpdate() == 0)
				return false;
			else
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			}
	}
	@Override
	public Boolean addUser(Integer id) {
		System.out.println("We're removing a user now...");
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection();){
			String sql = "DELETE FROM project.users WHERE user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			/*
			 * the executeUpdate method will return a numerical value associated
			 * with how many rows were effected by an insert/update/delete
			 * operation
			 *
			 * i.e: if 15 rows were effected, then 15 will be returned by executeUpdate()
			 *
			 * If No rows were effected, this would indicate the query was either
			 * not successful, or information was not correctly provided
			 */
			if (ps.executeUpdate() == 0)
				return false;
			else
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			}
	}
	@Override
	public Boolean updateUser(User usr) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Boolean updateUser(Integer id) {
		// This is our model that represents the data from our database
				User usr = null;
				
				// The ResultSet is a representation of the data from our DB
				ResultSet rs = null;
				
				// JDBC offers 3 statements, Simple, Prepared and Callable. We use
				// PreparedStatements when we want to pass parameters to the statement itself
				PreparedStatement ps = null;
				try (Connection conn = ConnectionUtility.getConnection()) {
					// SELECT * FROM examples.employees WHERE emp_id = 1000;
					ps = conn.prepareStatement("UPDATE project.users SET user_level = 'u' WHERE user_id= ?");
					rs = ps.executeQuery();

					System.out.println("Success");

					if (ps.executeUpdate() == 0)
						return false;
					else
						return true;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
				
			}
	


	@Override
	public User selectTemp(String Temps) {
		// This is our model that represents the data from our database
		User usr = null;
		
		// The ResultSet is a representation of the data from our DB
		ResultSet rs = null;
		
		// JDBC offers 3 statements, Simple, Prepared and Callable. We use
		// PreparedStatements when we want to pass parameters to the statement itself
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			// SELECT * FROM examples.employees WHERE emp_id = 1000;
			ps = conn.prepareStatement("SELECT * FROM project.users WHERE user_level='t'");
			rs = ps.executeQuery();

			while (rs.next()) {

				usr = new User(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3),
						rs.getString(4)
						);
				System.out.println("userID: " + rs.getInt(1) + "\t UserName: " + rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return usr;
	}

	@Override
	public User selectTempAndUser() {
		// This is our model that represents the data from our database
		User usr = null;
		
		// The ResultSet is a representation of the data from our DB
		ResultSet rs = null;
		
		// JDBC offers 3 statements, Simple, Prepared and Callable. We use
		// PreparedStatements when we want to pass parameters to the statement itself
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			// SELECT * FROM examples.employees WHERE emp_id = 1000;
			ps = conn.prepareStatement("SELECT * FROM project.users WHERE user_level='t' OR user_level='u'");
			rs = ps.executeQuery();

			while (rs.next()) {

				usr = new User(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3),
						rs.getString(4)
						);
				System.out.println("userID: " + rs.getInt(1) + "\t UserName: " + rs.getString(2) + "\t UserLevel: " + rs.getString(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return usr;
	}

	
	@Override
	public Boolean makeUser(Integer id) {
		System.out.println("We're updating a user now...");
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection();){
			String sql = "UPDATE project.users SET user_level = 'u' WHERE user_id= ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			if (ps.executeUpdate() == 0)
				return false;
			else
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			}
}


	@Override
	public Boolean alterUser(String user_level, Integer id ) {
		User usr = null;
		System.out.println("We're updating a user now...");
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection();){
			String sql = "UPDATE project.users SET user_level = ? WHERE user_id= ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_level);
			ps.setInt(2, id);

			System.out.println(id + " " + user_level);
			
				
			if (ps.executeUpdate() == 0)
				return false;
			else
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			}
	}
	}

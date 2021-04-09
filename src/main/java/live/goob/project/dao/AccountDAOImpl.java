package live.goob.project.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import live.goob.project.model.Account;
import live.goob.project.model.User;
import live.goob.project.connectionutil.ConnectionUtility;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public Account selectAccount(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insertIntoUsers(Account usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> selectAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account deposit(Double dAmount, String user) {
		// This is our model that represents the data from our database
		User usr = null;
		
		// The ResultSet is a representation of the data from our DB
		ResultSet rs = null;
		
		// JDBC offers 3 statements, Simple, Prepared and Callable. We use
		// PreparedStatements when we want to pass parameters to the statement itself
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			// Inserts account if not already
			ps = conn.prepareStatement("INSERT INTO project.paccount (user_name) SELECT ? WHERE NOT EXISTS (SELECT user_name FROM project.paccount WHERE user_name = ?)");
			ps.setString(1, "'"+ user + "'");
			ps.setString(2,"'"+ user + "'");
			rs = ps.executeQuery();
			
			ps = conn.prepareStatement("UPDATE paccount SET funds = funds + ? WHERE (user_name = '?')");
			
			ps.setDouble(1, dAmount);
			ps.setString(2,"'"+ user + "'");
			rs = ps.executeQuery();

			/*while (rs.next()) {

				usr = new User(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3),
						rs.getString(4)
						);
				System.out.println("userID: " + rs.getInt(1) + "\t UserName: " + rs.getString(2));
			}*/

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return selectAccount(user);
	}

	@Override
	public Account withdrawl(Double id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateUser(Account usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

package live.goob.project.welcome;

import live.goob.project.dao.UserDAOImpl;
import live.goob.project.model.User;

public class AdminActions{
	
	public static Boolean deleteExistingUser(int user_id){
		UserDAOImpl eDaoP = new UserDAOImpl(); // connects to the database location
		System.out.println(user_id);
		Boolean test = eDaoP.removeUser(user_id); // checking if input exists on table
		//int user = test.getUser_id(); // getting the user ID from that field
	//	Boolean check = user_id.equals(user); // checking if field is equal to the input
		return true;
		
	}
	
	public static Boolean approveExistingUser(int user_id){
		UserDAOImpl eDaoP = new UserDAOImpl(); // connects to the database location
		System.out.println(user_id);
		Boolean test = eDaoP.makeUser(user_id); // checking if input exists on table
		//int user = test.getUser_id(); // getting the user ID from that field
	//	Boolean check = user_id.equals(user); // checking if field is equal to the input
		return true;
		
	}
	
	public static String changeUserLevel(String user_level, Integer user_id) {
		UserDAOImpl eDaoP = new UserDAOImpl(); // connects to the database location
		//System.out.println(user_level); just to make sure user level is recognized
		Boolean changed = eDaoP.alterUser(user_level, user_id);
		
		
		return user_level;
	}
}

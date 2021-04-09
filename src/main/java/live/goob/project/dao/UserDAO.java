package live.goob.project.dao;

import java.util.List;

import live.goob.project.model.User;

public interface UserDAO {
	public User selectUser(Integer id);
	public User selectUser(String name);
	public User selectTemp(String Temps);
	public Boolean alterUser(String user_level, Integer id);
	public User selectTempAndUser();
	public Boolean insertIntoUsers(User usr);
	public List<User> selectAllUsers();
	public Boolean addUser(Integer id);
	public Boolean removeUser(Integer id);
	public Boolean makeUser(Integer id);
	public Boolean updateUser(User usr);
	public Boolean updateUser(Integer id);
}

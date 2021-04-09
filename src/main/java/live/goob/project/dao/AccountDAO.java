package live.goob.project.dao;

import java.util.List;

import live.goob.project.model.Account;

public interface AccountDAO {
	public Account selectAccount(String name);
	public Boolean insertIntoUsers(Account usr);
	public List<Account> selectAllUsers();
	public Boolean removeUser(Integer id);
	public Account deposit(Double dAmount, String user);
	public Account withdrawl(Double id);
	public Boolean updateUser(Account usr);
	public Boolean updateUser(Integer id);
}

package live.goob.project.model;

/*
 * Welcome to my User
 * 			Model
 */

public class User {
	private Integer user_id;
	private String user_name;
	private String user_pass;
	private String user_level;
	public User() {
		super();
	}
	
	public User(Integer userid, String user_name, String user_pass, String user_level) {
		super();
		this.user_id=userid;
		this.user_name=user_name;
		this.user_pass=user_pass;
		this.user_level=user_level;
		}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public String getUser_level() {
		return user_level;
	}

	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		result = prime * result + ((user_level == null) ? 0 : user_level.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
		result = prime * result + ((user_pass == null) ? 0 : user_pass.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		if (user_level == null) {
			if (other.user_level != null)
				return false;
		} else if (!user_level.equals(other.user_level))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		if (user_pass == null) {
			if (other.user_pass != null)
				return false;
		} else if (!user_pass.equals(other.user_pass))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\tuserID: " + user_id + "\tUser Name: " + user_name +  "\t User Level: " + user_level + "";
	}
	

	
	
	
	
}

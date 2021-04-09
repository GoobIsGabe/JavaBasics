package live.goob.project.model;

/*
 * Welcome to the Account
 * 			Model
 */

public class Account {
	private String user_name;
	private Double funds;
	private Double pendingtransfer;
	public Account() {
		super();
	}	
	
	public Account(String user_name, Double funds, Double pendingtransfer) {
		super();
		this.user_name = user_name;
		this.funds = funds;
		this.pendingtransfer = pendingtransfer;
	}

	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Double getFunds() {
		return funds;
	}
	public void setFunds(Double funds) {
		this.funds = funds;
	}
	public Double getPendingtransfer() {
		return pendingtransfer;
	}
	public void setPendingtransfer(Double pendingtransfer) {
		this.pendingtransfer = pendingtransfer;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((funds == null) ? 0 : funds.hashCode());
		result = prime * result + ((pendingtransfer == null) ? 0 : pendingtransfer.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
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
		Account other = (Account) obj;
		if (funds == null) {
			if (other.funds != null)
				return false;
		} else if (!funds.equals(other.funds))
			return false;
		if (pendingtransfer == null) {
			if (other.pendingtransfer != null)
				return false;
		} else if (!pendingtransfer.equals(other.pendingtransfer))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [user_name=" + user_name + ", funds=" + funds + ", pendingtransfer=" + pendingtransfer + "]";
	}
	
}

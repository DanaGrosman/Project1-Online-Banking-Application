package ajbcolionebankinkapp.users;

import java.util.Objects;

public class Cerdetianls {
	protected String username;
	protected String password;
	
	public Cerdetianls(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerdetianls other = (Cerdetianls) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
}

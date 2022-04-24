package ajbcolionebankinkapp.users;

public class Cerdetianls {
	protected String username;
	protected String password;
	
	public Cerdetianls(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	
	public void setUsername(String username) {
		// TODO check validation
		this.username = username;
	}
	
	public void setPassword(String password) {
		// TODO check validation
		this.password = password;
	}
}

package BookShopSystem;
import java.util.List;
import java.util.ArrayList;


import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {
	
	@PrimaryKey
	private String login;
	private String password;
	private String fullName;
	

	public User(String login, String password, String fullName) {
		this.login = login;
		this.password = password;
		this.fullName = fullName;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	
}
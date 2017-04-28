package server.data;


import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import javax.jdo.annotations.*;


@PersistenceCapable(detachable="true")
public class User{
	
	@PrimaryKey
	private int id_user;
	private String email;
	private String password;
	private String name;
	private String address;
	private boolean role;        //true --> admin
								 //false --> user
	
	public User(int id_user, String email, String password, String name, String address, boolean role) {
		this.id_user = id_user;
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.role = role;
	}

	public int getId_user() {
		return id_user;
	}
	
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
	       this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
	       this.password = password;
	}
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
	       this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
	       this.address = address;
	}

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}
	
	
}

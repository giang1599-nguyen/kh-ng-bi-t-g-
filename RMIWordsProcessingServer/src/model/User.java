package model;

import java.io.Serializable;

public class User implements Serializable{
	private String name;
	private String password;

	public User(String user, String password) {
		this.name = user;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setUser(String user) {
		this.name = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
	}

}

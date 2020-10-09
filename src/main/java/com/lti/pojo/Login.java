/**
 * @author Parnab
 * @version 1.0
 */

/*simple login pojo class*/

package com.lti.pojo;

public class Login {
	
	private String username; //stores username
	private String password; // stores password
	
	public Login(String username, String password) { // parameterized constructor
		this.username = username;
		this.password = password;
	}
	
	//Getters and Setters
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

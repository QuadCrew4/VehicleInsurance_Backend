/**
 * @author Parnab 
 * @version 1.0
 */

package com.lti.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/* This class represents an entity for User */

@Entity
@Table (name = "user_details")
@NamedQuery (name = "login", query = "FROM User WHERE username=:uname AND password=: psw")
public class User {
	@Id
	@Column (length = 20)
	private String username; //stores username, primary key
	
	@Column (length = 20) // stores name
	private String name;
	
	@Column (length = 20) // stores password, in encrypted format
	private String password;
	
	@Column (length = 20)
	private String dob; // stores date of birth
	
	@Column (length = 30)
	private String email; // email
	
	@Column (length = 40)
	private String address; // address
	
	@Column (length = 20)
	private String mobile; // mobile number
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Policy> policies = new ArrayList<Policy>(); // One to Many mapping, one user has multiple policies
	
	//Getters and Setters

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", password=" + password + ", dob=" + dob + ", email="
				+ email + ", address=" + address + ", mobile=" + mobile + ", policies=" + policies + "]";
	}
	
}

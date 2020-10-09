/**
 * @author Parnab
 * @version 1.0
 */

/* user repository implementation class */

package com.lti.repo;

import java.util.Base64;

import java.util.Base64.Encoder;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.stereotype.Repository;

import com.lti.entity.User;
import com.lti.pojo.Login;


@Repository
public class UserRepoImpl implements UserRepo {

	@PersistenceContext
	private EntityManager em;
	
	public void saveUser(User u) { // method to register an user
		String pwd = u.getPassword();
		Encoder enc= Base64.getEncoder();
		u.setPassword(new String(enc.encode(pwd.getBytes())));
		em.persist(u);
	}
	
	public void updatePassword(String uname, String password) { //method to reset an user's password
		User u = em.find(User.class, uname);
		Encoder enc= Base64.getEncoder();
		u.setPassword(new String(enc.encode(password.getBytes())));
		em.merge(u);
	}
	
	public User fetchUser(String uname) { // method to fetch an user
		User u = em.find(User.class, uname);
		return u;
	}
	
	public List<User> fetchAll(){ // method to fetch all users
		return em.createQuery("from User").getResultList();
	}
	
	public User authenticate(Login login) { // method to validate login details
		String pwd = login.getPassword();
		Encoder enc= Base64.getEncoder();
		login.setPassword(new String(enc.encode(pwd.getBytes())));
		
		Query query = em.createNamedQuery("login");
		query.setParameter("uname", login.getUsername());
		query.setParameter("psw", login.getPassword());
		return (User)query.getSingleResult();
	}

	public void updateUser(User u) { // method to update an user
		em.merge(u);
	}
}
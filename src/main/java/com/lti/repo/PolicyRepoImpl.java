/**
 * @author Shilpi
 * @version 1.0
 */

/* policy repository implementation class */


package com.lti.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


import com.lti.entity.Policy;
import com.lti.entity.User;
import com.lti.entity.Vehicle;

@Repository
public class PolicyRepoImpl implements PolicyRepo {

	@PersistenceContext
	private EntityManager em;

	public void savePolicy(Policy p) { //method to save a policy
		em.persist(p);
	}

	public Policy fetchPolicy(String pno) { //method to fetch a policy
		Policy p = em.find(Policy.class, pno);
		return p;
	}

	public List<Policy> fetchPolicies(String uname) { // method to fetch policies for an user
		User u = em.find(User.class, uname);
		return u.getPolicies();
		/* return em.createQuery("from Policy").getResultList(); */
	}
	
	public List<Policy> fetchAllPolicies() { // method to fetch all policies

		return em.createQuery("from Policy").getResultList();
	}
	
	public void updatePolicy(Policy policy) { // method to update a policy
		em.merge(policy);
	}

	public void addUserPolicies(String uname,String regNo,Policy policy) { // method to add policy to an user
		User u=em.find(User.class,uname);
		Vehicle v =em.find(Vehicle.class, regNo);
		policy.setVehicle(v);
		u.getPolicies().add(policy);
		em.merge(u);
	}

	public void removePolicy(String policyNo) { // method to remove a policy
		Policy p =em.find(Policy.class,policyNo);
		em.remove(p);
	}

	public void renewUserpolicy(String uname, String policyNo, String expDate) { // method to renew a user's policy
		User u = em.find(User.class, uname);
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals(policyNo))
				p.setExpDate(expDate);
		}
		em.merge(u);		
	}

	public void removeUserPolicies(String uname) { // method to remove an user's policies
		User u =em.find(User.class,uname);
		u.getPolicies().removeAll(u.getPolicies());
		em.merge(u);
	}

}
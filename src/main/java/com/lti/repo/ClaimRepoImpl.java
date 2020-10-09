/**
 * @author Saiteja
 * @version 1.0
 */

/* claim repository implementation class */

package com.lti.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lti.entity.Claim;
import com.lti.entity.Policy;
import com.lti.entity.User;


@Repository
public class ClaimRepoImpl implements ClaimRepo {

	@PersistenceContext
	private EntityManager em;

	public void saveClaim(Claim c) { //method to store a claim
		em.persist(c);
	}

	public Claim fetchClaim(String cno) { //method to fetch a claim
		Claim c = em.find(Claim.class, cno);
		return c;
	}
	
	public void addUserPolicyClaim(String uname, String policyNo, Claim c) { //method to add a claim to an User's Policy
		User u = em.find(User.class, uname);
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals(policyNo))
				addPolicyClaim(policyNo, c.getClaimId());
		}
		em.merge(u);
	}

	public List<Claim> fetchClaims() { //method to fetch all claims
		return em.createQuery("from Claim").getResultList();
	}

	public void removeClaim(String claimId) { //method to remove a claim
		Claim c = em.find(Claim.class, claimId);
		em.remove(c);
	}
	
	public void addPolicyClaim(String policyNo, String claimId) { // method to a claim to a Policy
		Policy p = em.find(Policy.class, policyNo);
		Claim c = em.find(Claim.class, claimId);
		p.setClaim(c);
	}

	public User setUserClaim(String uname, String policyNo, Double amount, String status) { //method to set a claim status and amount for an user's claim
		User u = em.find(User.class, uname);
		Claim c = new Claim();
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if (p.getPolicyNo().equals(policyNo)) {
				c = p.getClaim();
				c.setAmount(amount);
				c.setStatus(status);
			}
		}
		em.merge(u);
		return u;
	}

}
/**
 * @author Saiteja
 * @version 1.0
 */

/* claim service implementation class */
package com.lti.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Claim;
import com.lti.entity.User;
import com.lti.repo.ClaimRepo;

@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	private ClaimRepo claimrepo;

	@Transactional(value = TxType.REQUIRED)
	public void persistClaim(Claim c) {
		claimrepo.saveClaim(c);
	}

	public Claim findClaim(String claimId) {
		return claimrepo.fetchClaim(claimId);
	}

	public List<Claim> listClaims() {
		return claimrepo.fetchClaims();
	}

	@Transactional(value = TxType.REQUIRED)
	public void deleteClaim(String claimId) {
		claimrepo.removeClaim(claimId);
	}

	@Transactional(value = TxType.REQUIRED)
	public User editUserClaim(String uname, String policyNo, Double amount, String status) {
		return claimrepo.setUserClaim(uname, policyNo, amount, status);
	}

	
	@Transactional(value=TxType.REQUIRED)
	public void editUserPolicyClaim(String uname, String policyNo, Claim claim) {

		claimrepo.addUserPolicyClaim(uname, policyNo, claim);

	}

}

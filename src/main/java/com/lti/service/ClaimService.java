/**
 * @author Saiteja
 * @version 1.0
 */

/* claim service interface */

package com.lti.service;

import java.util.List;

import com.lti.entity.Claim;
import com.lti.entity.User;


public interface ClaimService {

	void persistClaim(Claim c);

	Claim findClaim(String claimId);

	List<Claim> listClaims();

	void deleteClaim(String claimId);

	User editUserClaim(String uname, String policyNo, Double amount, String status);
	
	void editUserPolicyClaim(String uname, String policyNo, Claim claim);

}

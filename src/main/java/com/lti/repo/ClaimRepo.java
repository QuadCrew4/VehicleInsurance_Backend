/**
 * @author Saiteja
 * @version 1.0
 */

/* claim repo interface */
package com.lti.repo;

import java.util.List;


import com.lti.entity.Claim;
import com.lti.entity.User;

public interface ClaimRepo {

	void saveClaim(Claim c);
	Claim fetchClaim(String cno);
	List<Claim> fetchClaims();
	void removeClaim(String claimId);
	User setUserClaim(String uname, String policyNo, Double amount, String status);
	void addPolicyClaim(String policyNo, String claimId);
	void addUserPolicyClaim(String uname, String policyNo, Claim c);
}

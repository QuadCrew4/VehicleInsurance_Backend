/**
 * @author Shilpi
 * @version 1.0
 */

/* policy service interface */

package com.lti.service;

import java.util.List;

import com.lti.entity.Policy;


public interface PolicyService {

	void persistPolicy(Policy p);
	Policy findPolicy(String policyNo);
	List<Policy> listPolicies(String uname);
	List<Policy> listAllPolicies();
	void editPolicy(Policy policy);
	void editUserPolicies(String uname,String regNo,Policy policy);
	void editRenewPolicy(String uname, String policyNo, String expDate);
	void deleteUserPolicies(String uname);
	void deletePolicy(String policyNo);
	
}

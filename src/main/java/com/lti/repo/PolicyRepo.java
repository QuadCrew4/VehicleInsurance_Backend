/**
 * @author Shilpi
 * @version 1.0
 */

/* policy repo interface */

package com.lti.repo;

import java.util.List;


import com.lti.entity.Policy;



public interface PolicyRepo {

	void savePolicy(Policy p);
	Policy fetchPolicy(String pno);
	List<Policy> fetchPolicies(String username);
	void updatePolicy(Policy policy);
	void removePolicy(String policyNo);
	List<Policy> fetchAllPolicies();
	void addUserPolicies(String uname,String regNo,Policy policy);
	void renewUserpolicy(String uname, String policyNo,String expDate);
	void removeUserPolicies(String uname);

}

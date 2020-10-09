/**
 * @author Priyam
 * @version 1.0
 */

/* vehicle service interface */

package com.lti.service;


import com.lti.entity.Vehicle;



public interface VehicleService {

	void persistVehicle(Vehicle v);

	Vehicle findVehicle(String regNo);

	void editVehiclePolicy(String policyNo,String regNo);
		
	void editUserPolicyVehicle(String uname, String policyNo, String regNo);

}

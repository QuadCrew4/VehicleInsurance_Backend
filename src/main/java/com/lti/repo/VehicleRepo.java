/**
 * @author Priyam
 * @version 1.0
 */

/* vehicle repo interface */

package com.lti.repo;


import com.lti.entity.Vehicle;


public interface VehicleRepo {

	void saveVehicle(Vehicle v);

	Vehicle fetchVehicle(String regno);

	void addUserPolicyVehicle(String uname, String policyNo, String regNo);

	void addVehiclePolicy(String policyNo, String regNo);
}

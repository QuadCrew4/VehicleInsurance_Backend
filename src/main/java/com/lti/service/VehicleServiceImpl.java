/**
 * @author Priyam
 * @version 1.0
 */

/* vehicle service implementation class */

package com.lti.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Policy;
import com.lti.entity.Vehicle;
import com.lti.repo.UserRepo;
import com.lti.repo.VehicleRepo;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleRepo vehiclerepo;

	@Transactional(value = TxType.REQUIRED)
	public void persistVehicle(Vehicle v) {
		vehiclerepo.saveVehicle(v);
	}

	public Vehicle findVehicle(String regNo) {
		return vehiclerepo.fetchVehicle(regNo);
	}

	@Transactional(value = TxType.REQUIRED)
	public void editVehiclePolicy(String policyNo,String regNo) {
		vehiclerepo.addVehiclePolicy(policyNo, regNo);
	}

	@Transactional(value = TxType.REQUIRED)
	public void editUserPolicyVehicle(String uname, String policyNo, String regNo) {
		vehiclerepo.addUserPolicyVehicle(uname, policyNo, regNo);
	}


}
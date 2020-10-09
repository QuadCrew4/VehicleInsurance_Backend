/**
 * @author parnab
 * @version 1.0
 */

/* controller class for vehicle rest services */

package com.lti.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Vehicle;
import com.lti.service.VehicleService;

@CrossOrigin
@RestController
public class VehicleRestController {

	@Autowired
	private VehicleService vehicleservice;

	/*
	 * url: http://localhost:8080/Insurance_projectGladiator/rest/addvehicle and set body with vehicle details
	 */
	@PostMapping(value = "/addvehicle", consumes = "application/json")
	public String addVehicle(@RequestBody Vehicle vehicle) {
		vehicleservice.persistVehicle(vehicle);
		return "Vehicle added successfully";
	}
	
	/*
	 * url: http://localhost:8080/Insurance_projectGladiator/rest/fetchvehicle/registration number
	 */
	@GetMapping(value = "/fetchvehicle/{regNo}", produces = "application/json")
	public Vehicle fetchvehicle(@PathVariable String regNo) {
		return vehicleservice.findVehicle(regNo);
	}
	
	/*
	 * url: http://localhost:8080/Insurance_projectGladiator/rest/addpolicyvehicle/policy number, registration number
	 */
	@PutMapping(value = "/addpolicyvehicle/{policyNo},{regNo}", consumes = "application/json")
	public String addPolicyVehicle(@PathVariable String policyNo, @PathVariable String regNo) {
		vehicleservice.editVehiclePolicy(policyNo, regNo);
		return "Vehicle added to Policy successfully";
	}

	/*
	 * url: http://localhost:8080/Insurance_projectGladiator/rest/adduserpolicyvehicle/username, policy number, registration number
	 */
	@PutMapping(value = "/adduserpolicyvehicle/{uname},{policyNo},{regNo}", consumes = "application/json")
	public String addUserPolicyVehicle(@PathVariable String uname, @PathVariable String policyNo,
			@PathVariable String regNo) {
		vehicleservice.editUserPolicyVehicle(uname, policyNo, regNo);
		return "Vehicle added to an User's Policy successfully";
	}

}
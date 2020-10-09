/**
 * @author Shilpi, Parnab
 * @version 1.1
 */

/* controller class for policy rest services */

package com.lti.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Policy;
import com.lti.service.PolicyService;


@CrossOrigin
@RestController
public class PolicyRestController {

	@Autowired
	private PolicyService service;
	
	@Autowired
	private MailSender mailSender;
	
	/*
	 * url: http:/localhost:9090/addpolicy/username, registration number and set body with policy details
	 */	
	@PostMapping(value="/addpolicy/{uname},{regNo},{email},{price}",consumes="application/json")
	public String addPolicy(@PathVariable String uname,@PathVariable String regNo,@PathVariable String email,@PathVariable double price,@RequestBody Policy policy) {
		service.persistPolicy(policy);
		addUserPolicy(uname,regNo,policy);
		double roundedDouble = Math.round(price * 100.0) / 100.0;
		String s=String.valueOf(roundedDouble);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("quadcrewz@gmail.com");
		message.setTo(email);
		message.setSubject("Policy Successfully Bought");
		message.setText("Hello "+ uname +",\n\nCongratulations, you have successfully insured your vehicle with VehiSure.\n" + 
				"Stay connected with us for all the future plans and further offers\n\nYour Policy No: "+policy.getPolicyNo()+"\nPrice: ₹ "+s+"\nVehicle: "+policy.getVehicle().getManufacturer()+" "+policy.getVehicle().getModel()+"\n\nFrom VehiSure Team");
		System.out.println("Mail Sent");
		mailSender.send(message);
		return "User Policy added successfully";
	}
	
	/*
	 * url: http:/localhost:9090/adduserpolicy/username, registration number and set body with policy details
	 */	
	  @PutMapping(value="/adduserpolicy/{uname}",consumes="application/json") 
	  public String addUserPolicy(@PathVariable String uname,String regNo,Policy policy) { 
		  service.editUserPolicies(uname,regNo,policy); 
		  return "Policy added to User successfully"; 
	}
	 
	  /*
		 * url: http:/localhost:9090/fetchpolicy/policy number
		 */	
	@GetMapping(value="/fetchpolicy/{policyNo}",produces="application/json")
	public Policy fetchPolicy(@PathVariable String policyNo) {
		return service.findPolicy(policyNo);
	}
	
	/*
	 * url: http:/localhost:9090/fetchallpolicies/username
	 */	
	  @GetMapping(value="/fetchallpolicies/{uname}",produces="application/json")
	  public List<Policy> fetchPolicies(@PathVariable String uname){ 
		  return service.listPolicies(uname);
	}
	  /*
		 * url: http:/localhost:9090/fetchallpolicies
		 */	
	  @GetMapping(value="/fetchallpolicies",produces="application/json")
	  public List<Policy> fetchAllPolicies(){ 
		  return service.listAllPolicies();
	}
	 
	/*
	* url: http:/localhost:9090/renewpolicy/username,policy number, expiry Date
	*/	
	@PutMapping(value="/renewpolicy/{username},{policyNo}",consumes="application/json")
	public String setRenewedDate(@PathVariable String username, @PathVariable String policyNo)
	{
		service.editRenewPolicy(username, policyNo,"10/09/2025");
		return "New Expiry Date set for an User's Policy successfully";
	}
	/*
	 * url: http:/localhost:9090/del/username
	 */
	@DeleteMapping("/del/{uname}")
	public String delUserPolicies(@PathVariable String uname) {
		service.deleteUserPolicies(uname);
		return "User policies deleted successfully";
	}
	/*
	 * url: http:/localhost:9090/editpolicy and set policy details as body
	 */	
	@PutMapping(value="/editpolicy",consumes="application/json")
	public String editPolicy(@RequestBody Policy policy) {
		service.editPolicy(policy);
		return "Policy updated successfully";
	}
	
}
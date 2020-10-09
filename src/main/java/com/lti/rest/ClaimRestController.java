/**
 * @author Saiteja
 * @version 1.0
 */

/* controller class for claim rest services */

package com.lti.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Claim;
import com.lti.entity.User;
import com.lti.service.ClaimService;

@CrossOrigin
@RestController
public class ClaimRestController {

	@Autowired
	private ClaimService service;
	
	@Autowired
	private MailSender mailSender;
	
	/*
	 * url: http://localhost:9090/addclaim/username, policy number
	 */	
	@PostMapping(value="/addclaim/{username},{policyNo},{email},{amount}",consumes="application/json")
	public String addClaim(@PathVariable String username,@PathVariable String policyNo,@PathVariable String email,@PathVariable double amount,@RequestBody Claim claim) {
		service.persistClaim(claim);
		addUserPolicyClaim(username,policyNo,claim);
		double roundedDouble = Math.round(amount * 100.0) / 100.0;
		String s=String.valueOf(roundedDouble); 
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("quadcrewz@gmail.com");
		message.setTo(email);
		message.setSubject("Claim Submitted");
		message.setText("Hello "+ username +",\n\nYour Claim has been successfully submitted.\nWe are in the process of looking into your request.\n" + 
				"We will revert back to you as soon as possible. \n\nYour Claim No: "+claim.getClaimId()+"\nAmount: ₹ "+s+"\n\nFrom VehiSure Team");
		System.out.println("Mail Sent");
		mailSender.send(message);
		return "User Claim added successfully";
	}
	/*
	 * url: http://localhost:9090/addclaim/username, policy number
	 */	
	@PostMapping(value="/adduserpolicyclaim/{uname},{policyNo}",consumes="application/json")
	public String addUserPolicyClaim(@PathVariable String uname,@PathVariable String policyNo,@RequestBody Claim claim)
	{	service.persistClaim(claim);
		service.editUserPolicyClaim(uname, policyNo, claim);
		return "Claim added to an User's Policy successfully";
	}
	
	/*
	 * url: http://localhost:9090/rest/setuserclaim/username, policy number, amount, status
	 */	
	@PutMapping(value="/setuserclaim/{uname},{policyNo},{amount},{status}",consumes="application/json")
	public User setUserClaim(@PathVariable String uname,@PathVariable String policyNo,@PathVariable Double amount,@PathVariable String status)
	{
		return service.editUserClaim(uname, policyNo, amount, status);
		/* return "Claim amount and Status set for an User's Policy successfully"; */
	}	
}
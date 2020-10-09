/**
 * @author Parnab
 * @version 1.1
 */

/* controller class for user rest services */

package com.lti.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.lti.entity.User;
import com.lti.pojo.Login;
import com.lti.service.UserService;

@CrossOrigin
@RestController
public class UserRestController {

	@Autowired
	private UserService service;
	
	@Autowired
	private MailSender mailSender;
	
	/*
	 * url: http://localhost:8080/Insurance_projectGladiator/rest/register set body with user details
	 */	
	@PostMapping(value="/register",consumes="application/json")
	public String addUser(@RequestBody User user) {
		service.persistUser(user);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("quadcrewz@gmail.com");
		message.setTo(user.getEmail());
		message.setSubject("Registration Successful");
		message.setText("Hello "+ user.getUsername() +",\n\nWelcome to VehiSure. Thanks for registering with us.\n" + 
				"We pledge to provide you the best insurance and claim services to protect your vehicle.\n\nRegards,\n\nVehiSure Team");
		
		System.out.println("Mail Sent");
		mailSender.send(message);
		return "User added successfully";
		
	}
	
	@GetMapping(value = "/login", produces = "application/json")
	public User login(@RequestParam("username") String username, @RequestParam("password") String password) {
		Login login = new Login(username, password);
		User user = service.validate(login);
		System.out.println(user.getUsername()+ "\t" + user.getPassword());
		return user;
	}
	/*
	 * url: http://localhost:8080/Insurance_projectGladiator/rest/list
	 */
	@GetMapping(value="/list",produces="application/json")
	public List<User> listUsers() {
		return service.list();
	}
	/*
	 * url: http://localhost:8080/Insurance_projectGladiator/fetch/username
	 */
	@GetMapping(value="/fetch/{uname}",produces="application/json")
	public User fetchUser(@PathVariable String uname) {
		return service.findUser(uname);
	}
	/*
	 * url: http://localhost:8080/Insurance_projectGladiator/edituser set body with user details
	 */
	@PutMapping(value="/edituser",consumes="application/json")
	public String editUser(@RequestBody User user) {
		service.editUser(user);
		return "User updated successfully";
	}
	
	@PutMapping(value="/resetpassword/{uname},{password},{email}")
	public String resetPassword(@PathVariable String uname, @PathVariable String password, @PathVariable String email) {
		service.editPassword(uname, password);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("quadcrewz@gmail.com");
		message.setTo(email);
		message.setSubject("Password Reset Successfully");
		message.setText("Hello "+ uname +",\nYour Password has been successfully reset \n\nNew Password: "+password+"\n\nFrom VehiSure Team");
		System.out.println("Mail Sent");
		mailSender.send(message);
		return "User password reset successfully";
	}

}
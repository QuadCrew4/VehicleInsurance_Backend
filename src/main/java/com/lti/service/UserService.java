/**
 * @author Parnab
 * @version 1.0
 */

/* user service interface */

package com.lti.service;

import java.util.List;


import com.lti.entity.User;
import com.lti.pojo.Login;


public interface UserService {
	void persistUser(User u);
	void editPassword(String uname, String password);
	User findUser(String uname);
	void editUser(User u);
	List<User> list();
	User validate(Login login);
}

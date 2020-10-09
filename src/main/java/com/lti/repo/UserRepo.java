/**
 * @author Parnab
 * @version 1.0
 */

/* user repo interface */

package com.lti.repo;

import java.util.List;


import com.lti.entity.User;
import com.lti.pojo.Login;



public interface UserRepo {

	void saveUser(User u);
	void updateUser(User u);
	void updatePassword(String uname, String password);
	User authenticate(Login login);
	User fetchUser(String uname);
	List<User> fetchAll();

}

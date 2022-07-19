package com.tryit.services.user.authendicationHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import com.tryit.services.taskmanagement.models.User;
import com.tryit.services.taskmanagement.repository.UserRepository;
@Component
public class TryitAuthenticationManger {

	
	@Autowired
	private UserRepository userRepository;
	
	public void authenticateUser(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		User user = userRepository.findUserByname(username);
		boolean isUserExist = user!=null ? true : false;
		boolean isValidCredential = user!=null ? true: false;
		if(!isUserExist) {
			throw new BadCredentialsException("Username or Password incorrect");
		}else if(!isValidCredential) {
			throw new Exception("User not exist");
		}
	}

}

package com.tryit.services.user.authendicationModels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tryit.services.taskmanagement.models.User;
import com.tryit.services.taskmanagement.repository.UserRepository;
@Service
public class TryitUserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	public User loadUserByUsername(String username) {
//		System.err.println(userRepository.findUserByname(username).toString());
		return userRepository.findUserByname(username);
//		return null;
	}

}

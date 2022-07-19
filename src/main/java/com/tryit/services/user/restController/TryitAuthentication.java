package com.tryit.services.user.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tryit.services.taskmanagement.models.User;
import com.tryit.services.taskmanagement.security.JwtResponse;
import com.tryit.services.user.authendicationHandler.TryitAuthenticationManger;
import com.tryit.services.user.authendicationHandler.TryitTokenGeneratorUtil;
import com.tryit.services.user.authendicationModels.TryitRequest;
import com.tryit.services.user.authendicationModels.TryitResponse;
import com.tryit.services.user.authendicationModels.TryitUserDetailsService;

@RestController
public class TryitAuthentication {
	@Autowired
	TryitAuthenticationManger authenticationManager;
	
	@Autowired
	TryitUserDetailsService userDetailsService;
	
	@Autowired
	TryitTokenGeneratorUtil tryitTokenUtil;
	
	@RequestMapping(value = "/authenticates", method = RequestMethod.POST)
	public ResponseEntity<?> checkLogin(@RequestBody TryitRequest request) throws Exception {
		authenticateUser(request.getUsername(), request.getPassword());
		
		final User user = userDetailsService
				.loadUserByUsername(request.getUsername());
		
		final String token = tryitTokenUtil.generateToken(user);

		return ResponseEntity.ok(new TryitResponse(token));
	}

	private void authenticateUser(String username, String password) throws Exception {
		try {
			authenticationManager.authenticateUser(username, password);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}

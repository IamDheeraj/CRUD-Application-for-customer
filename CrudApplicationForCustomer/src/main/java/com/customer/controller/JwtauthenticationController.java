package com.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.entity.JwtRequest;
import com.customer.entity.JwtResponse;
import com.customer.jwt.JwtHelper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
public class JwtauthenticationController {
		
	private UserDetailsService userDetailsService;
	
	private AuthenticationManager manager;
	
	private JwtHelper helper;
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
		this.doAuthenticate(request.getLoginId(), request.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLoginId());
		String token = this.helper.generateToken(userDetails);
		JwtResponse response = JwtResponse.builder()
				.jwtToken(token)
				.loginId(userDetails.getUsername()).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	private void doAuthenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
		try {
			manager.authenticate(authentication);
		} catch(BadCredentialsException e) {
			throw new BadCredentialsException("Credentials Invalid !!");
		}
	}
}

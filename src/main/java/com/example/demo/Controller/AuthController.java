package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Security.JwtRequest;
import com.example.demo.Security.JwtResponse;
import com.example.demo.Services.CustomUserDetailsService;
import com.example.demo.Utils.JwtUtils;

@RestController
@RequestMapping(value= "/auth")
public class AuthController {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping(value="/token")
public ResponseEntity<?> GenerateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
	
		System.out.println(jwtRequest);
		try {
		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( jwtRequest.getUsername(),jwtRequest.getPassword()));
		}
		catch (UsernameNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("BadCred");
		}
	UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

	String token = this.jwtUtils.generateToken(userDetails);
	
	System.out.println("JWT"+ token);
	
	
	return ResponseEntity.ok(new JwtResponse(token));	}	
}

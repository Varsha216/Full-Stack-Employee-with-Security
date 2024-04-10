package com.varsha.identityprovider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.varsha.identityprovider.exception.AdminNotFoundException;
import com.varsha.identityprovider.model.AdminProfile;
import com.varsha.identityprovider.model.AuthRequest;
import com.varsha.identityprovider.model.AuthResponse;
import com.varsha.identityprovider.service.AdminDetailsService;
import com.varsha.identityprovider.service.AdminService;
import com.varsha.identityprovider.service.JwtService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@Slf4j
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AdminDetailsService adminDetailsService;
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private JwtService jwtService;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Authentication service...";
	}
	
	@PostMapping("/register")
	public ResponseEntity<AdminProfile> registerAdmin(@RequestBody AdminProfile admin){
		log.info("Registering new Admin...");
		return new ResponseEntity<>(adminService.saveAdmin(admin), HttpStatus.CREATED);
		
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> generateJwt(@RequestBody AuthRequest request) throws AdminNotFoundException {
		ResponseEntity<String> response = null;
		
		// authenticating the User-Credentials
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		if(authenticate.isAuthenticated()) {
			final AdminProfile adminProfile = adminDetailsService.loadUserByUsername(request.getUsername());
		
			final String jwt = jwtService.generateToken(adminProfile);
		
			// test
			log.info("Authenticated User :: {}", adminProfile);
			response = new ResponseEntity<String>(jwt, HttpStatus.OK);
		}
		else{
			throw new AdminNotFoundException("Not authorized Customer");
		}
		
		return response;
	}
	
	
	@GetMapping("/validate")
	public ResponseEntity<AuthResponse> validateJwt(@RequestHeader("Authorization") String jwt) {

		ResponseEntity<AuthResponse> response = null;
		AuthResponse authResponse = new AuthResponse((long) 0,"", false);

		// first remove Bearer from Header
		jwt = jwt.substring(7);
		
		// check the jwt is proper or not
		final AdminProfile adminProfile = adminDetailsService.loadUserByUsername(jwtService.extractUsername(jwt));
	
		try {
			if (jwtService.validateToken(jwt, adminProfile)){
				authResponse.setId(adminProfile.getId());
				authResponse.setName(adminProfile.getName());
				authResponse.setValid(true);
				response = new ResponseEntity<>(authResponse, HttpStatus.OK);
			}
			else
				response = new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			response = new ResponseEntity<AuthResponse>(authResponse, HttpStatus.FORBIDDEN);
			log.error("Exception occured while validating JWT : Exception info : {}", e.getMessage());
		}
		
		
		return response;
	}
}

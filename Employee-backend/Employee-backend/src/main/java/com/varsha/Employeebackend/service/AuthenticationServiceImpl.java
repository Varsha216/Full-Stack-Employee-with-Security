package com.varsha.Employeebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varsha.Employeebackend.feign.AuthenticationFeign;


@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	private AuthenticationFeign authClient;
	
	@Override
	public boolean validateJwt(String jwt) {
		// TODO Auto-generated method stub
		return authClient.validateJwt(jwt).getBody().isValid();
	}

}

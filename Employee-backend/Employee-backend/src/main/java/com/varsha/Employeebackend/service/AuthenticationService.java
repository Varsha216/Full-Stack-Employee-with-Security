package com.varsha.Employeebackend.service;

import org.springframework.stereotype.Service;

public interface AuthenticationService {

	public boolean validateJwt(String jwt);
}

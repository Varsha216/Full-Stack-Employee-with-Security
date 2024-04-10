package com.varsha.Employeebackend.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.varsha.Employeebackend.feign.AuthenticationFeign;
import com.varsha.Employeebackend.model.AuthResponse;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceImplTest {

	@InjectMocks
	private AuthenticationServiceImpl authenticationService;
	
	@Mock
	private AuthenticationFeign authClient;
	
	@Test
	public void testValidateJwt() {
		AuthResponse authResponse = new AuthResponse(null, null, true);
		ResponseEntity<AuthResponse> response = new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
		when(authClient.validateJwt("jwt")).thenReturn(response);
		assertTrue(authenticationService.validateJwt("jwt"));
	}
}

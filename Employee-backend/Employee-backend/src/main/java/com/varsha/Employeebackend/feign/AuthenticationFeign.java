package com.varsha.Employeebackend.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.varsha.Employeebackend.model.AuthResponse;


@FeignClient(name="identity-provider", url = "${ms.auth}")
public interface AuthenticationFeign {

	@GetMapping("/validate")
	public ResponseEntity<AuthResponse> validateJwt(@RequestHeader("Authorization") String jwt);
}

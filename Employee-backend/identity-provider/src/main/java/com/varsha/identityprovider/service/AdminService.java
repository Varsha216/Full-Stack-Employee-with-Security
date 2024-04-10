package com.varsha.identityprovider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.varsha.identityprovider.model.AdminProfile;
import com.varsha.identityprovider.repository.AdminRepository;

@Service
public class AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AdminProfile saveAdmin(AdminProfile adminProfile) {
		adminProfile.setPassword(passwordEncoder.encode(adminProfile.getPassword()));
		AdminProfile savedAdmin = adminRepository.save(adminProfile);
		return savedAdmin;
	}

}

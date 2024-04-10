package com.varsha.identityprovider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.varsha.identityprovider.model.AdminProfile;
import com.varsha.identityprovider.repository.AdminRepository;

@Service
public class AdminDetailsService implements UserDetailsService{
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public AdminProfile loadUserByUsername(String username){
		AdminProfile admin = null;
		admin = adminRepository.getAdminProfileByUsername(username);
		return admin;
	}

}

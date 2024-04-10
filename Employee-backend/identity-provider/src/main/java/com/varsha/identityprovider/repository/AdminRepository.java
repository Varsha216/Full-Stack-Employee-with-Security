package com.varsha.identityprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.varsha.identityprovider.model.AdminProfile;

@Repository
public interface AdminRepository extends JpaRepository<AdminProfile, Long>{

	public AdminProfile getAdminProfileByUsername(String username);
}

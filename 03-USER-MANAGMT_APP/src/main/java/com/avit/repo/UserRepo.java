package com.avit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avit.entity.UserMaster;

public interface UserRepo extends JpaRepository<UserMaster, Integer>
{
	
}

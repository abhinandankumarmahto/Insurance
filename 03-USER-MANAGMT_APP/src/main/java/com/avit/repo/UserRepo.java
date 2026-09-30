package com.avit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avit.entity.UserMaster;
import java.util.List;


public interface UserRepo extends JpaRepository<UserMaster, Integer>
{
	public UserMaster findByEmail(String email);
	public UserMaster 	findByEmailAndPassword(String email, String password);
}

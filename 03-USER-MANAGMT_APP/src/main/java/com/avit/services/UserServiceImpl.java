package com.avit.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.avit.bindings.ActivateAccount;
import com.avit.bindings.Login;
import com.avit.bindings.User;
import com.avit.entity.UserMaster;
import com.avit.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserMasterService {
	
	private final UserRepo repo;
	
	private String rndString()
	{
		String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomGen = new StringBuilder();
        Random rnd = new Random();
        int len=6;
        
        	for(int i=0;i<len;i++)
        	{
        		int index = rnd.nextInt(alphaNumeric.length());
        		char rndChar=alphaNumeric.charAt(index);
        		randomGen.append(rndChar);
        				
        	}
        	return randomGen.toString();
        
	}
	@Override
	public boolean saveUser(User user) {
		UserMaster entity =new UserMaster();
		BeanUtils.copyProperties(user, entity);
		
		
        
		entity.setPassword(rndString());
		UserMaster save = repo.save(entity);
		entity.setAacountStatus("Active");
		return save.getUserId()!=null;
	}

	@Override
	public boolean activateUserAcc(ActivateAccount activateAccount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeAccountStatus(Integer id, String accStatus) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String login(Login login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String forgetPassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}

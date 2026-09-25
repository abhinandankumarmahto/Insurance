package com.avit.services;

import java.util.List;

import com.avit.bindings.ActivateAccount;
import com.avit.bindings.Login;
import com.avit.bindings.User;

public interface UserMasterService 
{
	public boolean saveUser(User user);
	public boolean activateUserAcc(ActivateAccount activateAccount);
	public List<User> getAllUser();
	public User getUserById(Integer id);
	public boolean deleteUserById(Integer id);
	public boolean changeAccountStatus(Integer id,String accStatus);
	public String login(Login login);
	public String forgetPassword(String email);
	
}

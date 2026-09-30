package com.avit.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.avit.bindings.ActivateAccount;
import com.avit.bindings.Login;
import com.avit.bindings.User;
import com.avit.services.UserMasterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController 
{
	private final UserMasterService service;
	
	@PostMapping("/user")
	public ResponseEntity<String> userReg(@RequestBody User user)
	{
		boolean saveUser = service.saveUser(user);
		if(saveUser) return new ResponseEntity<String>("Regitration Successfull",HttpStatus.OK);
		else return new ResponseEntity<String>("Regitration failed",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@PostMapping("/activate")
	public ResponseEntity<String> activateUser(@RequestBody ActivateAccount account)
	{
		boolean userAcc = service.activateUserAcc(account);
		if(userAcc) return new ResponseEntity<String>("Account activated",HttpStatus.OK);
		else return new ResponseEntity<String>("Your temp password is incorrect",HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = service.getAllUser();
		return new ResponseEntity<List<User>>(allUser,HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id )
	{
		User userById = service.getUserById(id);
		return new ResponseEntity<User>(userById,HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Integer id )
	{
		boolean userById = service.deleteUserById(id);
		if(userById) return new ResponseEntity<String>("User Deleted",HttpStatus.OK);
		else return new ResponseEntity<String>("User not Deleted",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/status/{userId}/{status}")
	public ResponseEntity<String> statusChange (@PathVariable Integer userId,@PathVariable String status)
	{
		boolean accountStatus = service.changeAccountStatus(userId, status);
		if(accountStatus) return new ResponseEntity<String>("Account staus changed",HttpStatus.OK);
		else return new ResponseEntity<String>("Account not staus changed",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Login login)
	{
		String status = service.login(login);
		return new ResponseEntity<String>(status,HttpStatus.OK);
		
	}
	
	@PostMapping("/forgot/{email}")
	public ResponseEntity<String> login(@PathVariable String email)
	{
		String status = service.forgetPassword(email);
		return new ResponseEntity<String>(status,HttpStatus.OK);
		
	}
}

package com.avit.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.avit.bindings.ActivateAccount;
import com.avit.bindings.Login;
import com.avit.bindings.User;
import com.avit.entity.UserMaster;
import com.avit.repo.UserRepo;
import com.avit.utils.Sendmail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserMasterService {

	private final UserRepo repo;
	private final Sendmail mail;

	private String rndString() {
		String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder randomGen = new StringBuilder();
		Random rnd = new Random();
		int len = 6;

		for (int i = 0; i < len; i++) {
			int index = rnd.nextInt(alphaNumeric.length());
			char rndChar = alphaNumeric.charAt(index);
			randomGen.append(rndChar);

		}
		return randomGen.toString();

	}
	
	private String readForgotPassword(String fullname, String password)
	{
		StringBuilder builder = new StringBuilder();
		String string ="";
		try {
			FileReader file = new FileReader("REG_EMAIL_BODY.txt");
			BufferedReader br = new BufferedReader(file);
			String line = br.readLine();
			while(line==null)
			{
				builder.append(line);
				line=br.readLine();
			}
			string = builder.toString();
			string.replace("{FULLNAME}", fullname);
			string.replace("{PSWD}", password);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
		
	}

	private String readRegMailBody(String fullname, String tempPwd) 
	{
		String url = "";
		StringBuffer stringBuffer = new StringBuffer();
		String mailBody = null;
		try {
			FileReader reader = new FileReader("REG_EMAIL_BODY.txt");
			BufferedReader buffer = new BufferedReader(reader);

			String line = buffer.readLine();
			while (line == null) {
				stringBuffer.append(line);
				line = buffer.readLine();
				mailBody = buffer.toString();
				mailBody.replace("{FULLNAME}", fullname);
				mailBody.replace("{{TEMP_PSWD}", tempPwd);
				mailBody.replace("{URL}", url);
			}
			buffer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mailBody;
	}

	@Override
	public boolean saveUser(User user) {
		UserMaster entity = new UserMaster();
		BeanUtils.copyProperties(user, entity);

		entity.setPassword(rndString());
		UserMaster save = repo.save(entity);
		entity.setAacountStatus("In-Active");

		String subject = "Your Registration successfully";
		String body = readRegMailBody(entity.getFullname(), entity.getPassword());

		mail.sendMail(user.getEmail(), subject, body);
		return save.getUserId() != null;
	}

	@Override
	public boolean activateUserAcc(ActivateAccount activateAccount) {
		UserMaster entity = new UserMaster();
		entity.setEmail(activateAccount.getEmail());
		entity.setPassword(activateAccount.getTempPwd());
		List<UserMaster> findAll = repo.findAll(Example.of(entity));
		if (findAll.isEmpty())
			return false;
		else {
			UserMaster userMaster = findAll.get(0);
			userMaster.setPassword(activateAccount.getNewPwd());
			userMaster.setAacountStatus("Active");
			repo.save(userMaster);
			return true;
		}
	}

	@Override
	public List<User> getAllUser() {

		List<User> userList = new ArrayList<>();
		List<UserMaster> entity = repo.findAll();
		for (UserMaster entities : entity) {
			User user = new User();
			BeanUtils.copyProperties(entities, user);
			userList.add(user);
		}
		return userList;
	}

	@Override
	public User getUserById(Integer id) {
		Optional<UserMaster> byId = repo.findById(id);
		if (byId.isPresent()) {
			UserMaster master = byId.get();
			User user = new User();
			BeanUtils.copyProperties(master, user);
			return user;
		}
		return null;
	}

	@Override
	public boolean deleteUserById(Integer id) {
		try {
			repo.deleteById(id);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean changeAccountStatus(Integer id, String accStatus) {
		Optional<UserMaster> byId = repo.findById(id);
		if (byId.isPresent()) {
			UserMaster userMaster = byId.get();
			userMaster.setAacountStatus(accStatus);
			repo.save(userMaster);
			return true;
		}

		return false;
	}

	@Override
	public String login(Login login) {
//		List<UserMaster> all = repo.findAll();
//		String email = login.getEmail();
//		String pswd = login.getPswd();
//		for(UserMaster entity: all)
//		{
//			if(email==entity.getEmail()&&pswd==entity.getPassword()) {
//				return "Login Successful";
//			}
//			
//		}
//		return "Login failed";

//		UserMaster userMaster = new UserMaster();
//		userMaster.setEmail(login.getEmail());
//		userMaster.setPassword(login.getPswd());
//		List<UserMaster> all = repo.findAll(Example.of(userMaster));
//
//		if (all.isEmpty()) 
//		{
//			return "Login Failed";
//
//		}
//		else 
//		{	UserMaster userMaster2 = all.get(0);
//			if(userMaster2.getAacountStatus().equals("Active"))
//			{
//				return "SuccessFully login";
//			}
//			else return "Activate your Account";
//		}

		UserMaster byEmailAndPassword = repo.findByEmailAndPassword(login.getEmail(), login.getPswd());
		if (byEmailAndPassword == null) {
			return "Login failed";
		} else {
			if (byEmailAndPassword.getAacountStatus().equals("Active")) {
				return "Login successfull";

			} else {
				return "Activate Your Accout";
			}

		}

	}

	@Override
	public String forgetPassword(String email) {
//		UserMaster master = new UserMaster();
//		master.setEmail(email);
//		List<UserMaster> all = repo.findAll(Example.of(master));
//		if(all.isEmpty())
//		{return "Mail not found";}
//		else {
//		UserMaster userMaster = all.get(0);
//		return userMaster.getPassword();

		UserMaster byEmail = repo.findByEmail(email);
		if (byEmail == null) {
			return "Mail not found";
		} else {
		
			String subject = "Change password";
			String body = readForgotPassword(byEmail.getFullname(), byEmail.getPassword());
			boolean sendMail = mail.sendMail(email, subject, body);
			if(sendMail)
			return "mail sent succesfully";
			else return null;
		}

	}

}

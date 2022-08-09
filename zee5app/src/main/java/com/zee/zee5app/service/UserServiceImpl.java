package com.zee.zee5app.service;

import java.util.Optional;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.repo.UserRepo;
import com.zee.zee5app.repo.UserRepositoryImpl;

public class UserServiceImpl implements UserService{
	
	private UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private static UserService userService;
	
	public static UserService getInstance() {
		// userService object
		
		if(userService == null) {
			userService = new UserServiceImpl();
			
		}
		
		return userService;
	}
	
	private UserRepo userRepo = UserRepositoryImpl.getInstance();
	
	public User insertUser(User user) throws UnableToGenerateIdException {
		
		return userRepo.insertUser(user);
	}
	
	public Optional<User> getUserByUserId(String userId) {
		
		return userRepo.getUserByUserId(userId);
		
	}
	
	public Optional<User[]> getAllUsers() {
		return null;
		//return userRepo.getAllUsers();
	}
	
	public String deleteUser(String userId) throws Exception {
		return userRepo.deleteUser(userId);
	}
	
	public User updateUser(String userId, User user) {
		return null;
		//return userRepo.updateUser(userId, user);
	}
	
}

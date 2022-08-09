package com.zee.zee5app.service;

import java.util.Optional;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;

public interface UserService {
	
	public User insertUser(User user) throws UnableToGenerateIdException;
	public User updateUser(String userId, User user);
	public String deleteUser(String userId) throws Exception;
	public Optional<User[]> getAllUsers();
	public Optional<User> getUserByUserId(String userId);
	
}

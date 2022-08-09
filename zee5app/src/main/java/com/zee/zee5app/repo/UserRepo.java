package com.zee.zee5app.repo;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;

public interface UserRepo {
	
	public User insertUser(User user) throws UnableToGenerateIdException;
	public Optional<User> updateUser(String userId, User user) throws InvalidIdException;
	public String deleteUser(String userId) throws Exception;
	public Optional<List<User>> getAllUsers();
	public Optional<User> getUserByUserId(String userId);
	
}

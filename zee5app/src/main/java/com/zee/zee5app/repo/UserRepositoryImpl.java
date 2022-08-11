package com.zee.zee5app.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class UserRepositoryImpl implements UserRepo {

//	private static UserRepo userRepo;
//	
//	private UserRepositoryImpl() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	public static UserRepo getInstance() {
//		if(userRepo == null) {
//			userRepo = new UserRepositoryImpl();
//		}
//		return userRepo;
//	}
	
	@Autowired
	DataSource dataSource;
	@Autowired
	private DBUtils dbUtils;
	
	@Override
	public User insertUser(User user) throws UnableToGenerateIdException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		// In jdbc, qs mark count will start with 1
		String insertStatement = "insert into user_table"
				+ " (userid, firstname, lastname, email, doj, dob, active)"
				+ " values(?,?,?,?,?,?,?)";
		
		//connection object
		//connection = dataSource.getConnection();
		
		//statement object(prepared)
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(insertStatement);
			
			preparedStatement.setString(1, dbUtils.userIdGenerator(user.getFirstName(), user.getLastName()));
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setDate(5, Date.valueOf(user.getDoj()));
			preparedStatement.setDate(6, Date.valueOf(user.getDob()));
			preparedStatement.setBoolean(7, user.isActive());
			
			int result = preparedStatement.executeUpdate();
			if(result > 0) {
				return user;
			}
			else {
				return null;
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//we should get the result, based on that we will return the result
		
		return null;
	}

	@Override
	public Optional<User> updateUser(String userId, User user)throws InvalidIdException {
		// TODO Auto-generated method stub
		
//		String query = "update from user_table where userid=?";
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//
//		connection = dbUtils.getConnection();
//		
//		try {
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, userId);
//			int result = preparedStatement.executeUpdate();
//			if (result > 0) {
//				// update
//
//				return Optional.of(user);
//			} else {
//				throw new InvalidIdException("UserId is not Valid");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			dbUtils.closeConnection(connection);
//		}
//		return Optional.empty();
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateQuery = "upadte user_table "
				+ "set fisrtname=?, lastname=?, email=?, doj=?, dob=?, active=? "
				+ "where userid=?";

		//connection = dataSource.getConnection();
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setDate(5, Date.valueOf(user.getDoj()));
			preparedStatement.setDate(6, Date.valueOf(user.getDob()));
			preparedStatement.setBoolean(7, user.isActive());
			
			int result = preparedStatement.executeUpdate();
			
			if (result > 0) {
				return Optional.of(user);
			} else {
				throw new InvalidIdException("userId not found");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public String deleteUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		
		String deleteStatement = "delete from user_table where userid=?";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		connection = dataSource.getConnection();
		
		
		preparedStatement = connection.prepareStatement(deleteStatement);
			
		preparedStatement.setString(1, userId);
		
		int result = preparedStatement.executeUpdate();
		
		if(result>0) {
			return "success";
		}
		else {
			throw new NoDataFoundException("userid not present");
		}
			
	}

	@Override
	public Optional<List<User>> getAllUsers() {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> users = new ArrayList<User>();
		
		String query = "select * from user_table";
		
		//connection = dataSource.getConnection();
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			
			//preparedStatement.setString(1, userId); use only if accesslevel = none
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				// result exists
				//inside the RESULTSET object
				//User object from resultset data
				
				User user = new User();
				
				user.setUserId(resultSet.getString("userid"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setDoj(resultSet.getDate("doj").toLocalDate());
				user.setDob(resultSet.getDate("dob").toLocalDate());
				user.setActive(resultSet.getBoolean("active"));
				users.add(user);	
				
			}
			return Optional.of(users);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.empty();
		
		
	}

	@Override
	public Optional<User> getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select * from user_table where userid = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			
			//preparedStatement.setString(1, userId); use only if accesslevel = none
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				// result exists
				//inside the RESULTSET object
				//User object from resultset data
				
				User user = new User();
				
				user.setUserId(userId);
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setDoj(resultSet.getDate("doj").toLocalDate());
				user.setDob(resultSet.getDate("dob").toLocalDate());
				user.setActive(resultSet.getBoolean("active"));
				
				return Optional.of(user);
			}
			else {
				return Optional.empty();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

}

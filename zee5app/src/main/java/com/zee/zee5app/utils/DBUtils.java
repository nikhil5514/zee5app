package com.zee.zee5app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.zee.zee5app.exceptions.UnableToGenerateIdException;

public class DBUtils {

	private DBUtils() {
		// TODO Auto-generated constructor stub
	}

	private static DBUtils dbUtils;

	public static DBUtils getInstance() {
		if (dbUtils == null) {
			dbUtils = new DBUtils();
		}
		return dbUtils;
	}
	
	public Connection getConnection() {
		// to provide the connection
		//no hard coded information
		
		Properties properties = loadProperties();
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"), properties.getProperty("db.password"));
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//it is used instead of map cos it has inbuilt load method
	private Properties loadProperties() {
		// to access information from file
		
		Properties properties = new Properties();
		InputStream inputStream = null;
		
		try {
			inputStream = DBUtils.class.getClassLoader().
					getResourceAsStream("application.properties");
			System.out.println(inputStream != null);
			properties.load(inputStream);
			return properties;
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return properties;
		
	}
	
	public String userIdGenerator(String firstName, String lastName) throws UnableToGenerateIdException {
		
		// It is responsible to generate the userid for user entity
		//1st retrieve the value(db stored value from idgen table)
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0;
		int updateResult = 0;
		String query = "select id from useridgenerator";
		String updateIdStatement = "update useridgenerator set id=?";
		String newId = null;
		
		connection = this.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				id = resultSet.getInt(1);
				
				id = ++id;
				
				newId = "" + firstName.charAt(0) + lastName.charAt(0) +  Integer.toString(id);
				System.out.println(newId);
				
				preparedStatement = connection.prepareStatement(updateIdStatement);
				preparedStatement.setInt(1, id);
				
				updateResult = preparedStatement.executeUpdate();
				
				if(updateResult == 0) {
					throw new UnableToGenerateIdException("unable to generate id");
				}
				
				return newId;
			}
			
			return null;
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new UnableToGenerateIdException("unable to generate id "
					+ e.getMessage());
		}
		finally {
			
			this.closeConnection(connection);
		}
		
		
	}
	
	public String movieIdGenerator(String movieName) throws UnableToGenerateIdException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0;
		int updateResult = 0;
		String query = "select id from movieidgenerator";
		String updateIdStatement = "update movieidgenerator set id=?";
		String newId = null;
		
		connection = this.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				id = resultSet.getInt(1);
				
				id = ++id;
				
				newId = "" + movieName.charAt(0) + movieName.charAt(1) +  Integer.toString(id);
				System.out.println(newId);
				
				preparedStatement = connection.prepareStatement(updateIdStatement);
				preparedStatement.setInt(1, id);
				
				updateResult = preparedStatement.executeUpdate();
				
				if(updateResult == 0) {
					throw new UnableToGenerateIdException("unable to generate id");
				}
				
				return newId;
			}
			
			return null;
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new UnableToGenerateIdException("unable to generate id "
					+ e.getMessage());
		}
		finally {
			
			this.closeConnection(connection);
		}
		
	
		
		
		
		
	}
	
	
public String seriesIdGenerator(String seriesName) throws UnableToGenerateIdException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0;
		int updateResult = 0;
		String query = "select id from seriesidgenerator";
		String updateIdStatement = "update seriesidgenerator set id=?";
		String newId = null;
		
		connection = this.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				id = resultSet.getInt(1);
				
				id = ++id;
				
				newId = "" + seriesName.charAt(0) + seriesName.charAt(1) +  Integer.toString(id);
				System.out.println(newId);
				
				preparedStatement = connection.prepareStatement(updateIdStatement);
				preparedStatement.setInt(1, id);
				
				updateResult = preparedStatement.executeUpdate();
				
				if(updateResult == 0) {
					throw new UnableToGenerateIdException("unable to generate id");
				}
				
				return newId;
			}
			
			return null;
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new UnableToGenerateIdException("unable to generate id "
					+ e.getMessage());
		}
		finally {
			
			this.closeConnection(connection);
		}	
	}
	
	public static void main(String[] args) {
		String res = null;
		try {
			res = DBUtils.getInstance().movieIdGenerator("sitaRamam");
		} catch (UnableToGenerateIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(res);
	}

}

//In application.properties file

//it will hold transformations key value pair
//jdbc is referred as protocol, mysql is referred as sub protocol, localhost is server
//3306 is port for communicating with DB


package com.wise.WorkForce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.WorkForce.dto.User;

public class UserDAO {
	public int insert(User user) {
		int status = 0;
		final String QUERY = "insert into user(First_Name,Last_Name,Password,Email,Phone_No) values(?,?,?,?,?)";
		Connection connection = DBUtility.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getPhoneNumber());
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public int getUserId(String email) {
		ResultSet resultSet = null; 
		User user = new User();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select User_Id from user where Email = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setString(1,email);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				user.setUserId(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return user.getUserId();
	}
	
	public String getPassword(String email) {
		ResultSet resultSet = null; 
		User user = new User();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select Password from user where Email = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setString(1,email);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				user.setPassword(resultSet.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return user.getPassword();
	}
	public User get(int userId) {
		ResultSet resultSet = null; 
		User user = new User();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from User where User_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,userId);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				user.setUserId(resultSet.getInt(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setPhoneNumber(resultSet.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return user;
	}
	
	public List<User> getAll() {
		ResultSet resultSet = null; 
		PreparedStatement preparedStatement = null;
		List<User> userList = null;
		final String QUERY = "select * from User";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				userList = new ArrayList<User>();
				do {
					User user = new User();
					user.setUserId(resultSet.getInt(1));
					user.setFirstName(resultSet.getString(2));
					user.setLastName(resultSet.getString(3));
					user.setPassword(resultSet.getString(4));
					user.setEmail(resultSet.getString(5));				
					userList.add(user);
				} while (resultSet.next() );
				return userList;
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return userList;
	}
	public static void main(String[] args) {
		UserDAO T = new UserDAO();
		System.out.println(T.getUserId("efd@gmail.com"));
	}
}


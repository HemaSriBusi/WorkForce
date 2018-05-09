package com.wise.WorkForce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.WorkForce.dto.Address;

public class AddressDAO {
	public int insert(Address address) {
		int status = 0;
		final String QUERY = "insert into Address(User_Id,Door_Number,Street,City,State,Postal_Code,Country) values(?,?,?,?,?,?,?)";
		Connection connection = DBUtility.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, address.getUserId());
			preparedStatement.setString(2, address.getDoorNo());
			preparedStatement.setString(3, address.getStreet());
			preparedStatement.setString(4, address.getCity());
			preparedStatement.setString(5, address.getState());
			preparedStatement.setInt(6, address.getPostalCode());
			preparedStatement.setString(7, address.getCountry());
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public Address getByUserId(int userId) {
		ResultSet resultSet = null; 
		Address address = new Address();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from Address where User_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,userId);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				address.setUserId(resultSet.getInt(1));
				address.setDoorNo(resultSet.getString(2));
				address.setStreet(resultSet.getString(3));
				address.setCity(resultSet.getString(4));
				address.setState(resultSet.getString(5));
				address.setPostalCode(resultSet.getInt(6));
				address.setCountry(resultSet.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return address;
	}
	
	public List<Address> getAll() {
		ResultSet resultSet = null; 
		PreparedStatement preparedStatement = null;
		List<Address> addressList = null;
		final String QUERY = "select * from Address";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				addressList = new ArrayList<Address>();
				do {
					Address address = new Address();
					address.setUserId(resultSet.getInt(1));
					address.setDoorNo(resultSet.getString(2));
					address.setStreet(resultSet.getString(3));
					address.setCity(resultSet.getString(4));
					address.setState(resultSet.getString(5));
					address.setPostalCode(resultSet.getInt(6));
					address.setCountry(resultSet.getString(7));
					addressList.add(address);
				} while ( resultSet.next() );
				return addressList;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return addressList;
	}
	public int updateAddress(int User_Id,String Door_Number, String Street, String city,String state, int postalCode,String country) {
		int status = 0;
		final String QUERY = "update Forces set Door_Number = ?, Street = ?, city = ?,state  = ?, postalCode = ?,Country = ? where Force_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, User_Id);
			preparedStatement.setString(2,Door_Number);
			preparedStatement.setString(3,Street);
			preparedStatement.setString(4,city);
			preparedStatement.setString(5,state);
			preparedStatement.setInt(6,postalCode);
			preparedStatement.setString(7,country);	
			status = preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public static void main(String[] args) {
		AddressDAO addressDAO = new AddressDAO();
		System.out.println(addressDAO.getAll());
	}
}
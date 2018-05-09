package com.wise.WorkForce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.WorkForce.dto.SubServiceType;

public class SubServiceTypeDAO {
	public int insertSubServiceType(SubServiceType subServiceType) {
		int status = 0;
		final String QUERY = "insert into SubServiceType(Sub_Service_Type_Id,Name,Service_Type_Id,Active,Icon) values(?,?,?,?,?)";
		Connection connection = DBUtility.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, subServiceType.getSubServiceTypeId());
			preparedStatement.setString(2, subServiceType.getName());
			preparedStatement.setInt(3, subServiceType.getServiceTypeId());
			preparedStatement.setBoolean(4, subServiceType.isActive());
			preparedStatement.setString(5, subServiceType.getIcon());
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public SubServiceType getBySubServiceTypeId(int subServiceTypeId) {
		ResultSet resultSet = null; 
		SubServiceType subServiceType = new SubServiceType();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from SubServiceType where Sub_Service_Type_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,subServiceTypeId);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				subServiceType.setSubServiceTypeId(resultSet.getInt(1));
				subServiceType.setName(resultSet.getString(2));
				subServiceType.setServiceTypeId(resultSet.getInt(3));
				subServiceType.setActive(resultSet.getBoolean(4));
				subServiceType.setIcon(resultSet.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return subServiceType;
	}
	
	public List<SubServiceType> getSubService(int serviceTypeId) {
		ResultSet resultSet = null; 
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from SubServiceType where Service_Type_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,serviceTypeId);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				List<SubServiceType> subServiceTypeList = new ArrayList<SubServiceType>();
				do {
					SubServiceType subServiceType = new SubServiceType();
					subServiceType.setSubServiceTypeId(resultSet.getInt(1));
					subServiceType.setName(resultSet.getString(2));
					subServiceType.setServiceTypeId(resultSet.getInt(3));
					subServiceType.setActive(resultSet.getBoolean(4));
					subServiceTypeList.add(subServiceType);
				} while(resultSet.next());
				return subServiceTypeList;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return null;
	}
	
	public List<SubServiceType> getAll() {
		ResultSet resultSet = null; 
		PreparedStatement preparedStatement = null;
		List<SubServiceType> subServiceTypeList = null;
		final String QUERY = "select * from SubServiceType";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				subServiceTypeList = new ArrayList<SubServiceType>();
				do {
					SubServiceType subServiceType = new SubServiceType();
					subServiceType.setSubServiceTypeId(resultSet.getInt(1));
					subServiceType.setName(resultSet.getString(2));
					subServiceType.setServiceTypeId(resultSet.getInt(3));
					subServiceType.setActive(resultSet.getBoolean(4));
					subServiceType.setIcon(resultSet.getString(5));
					subServiceTypeList.add(subServiceType);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return subServiceTypeList;
	}
	
	public List<SubServiceType> getAll(int serviceTypeId) {
		ResultSet resultSet = null;
		List<SubServiceType> subServiceTypeList = null;
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from SubServiceType where Service_Type_id= ?";
		
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,serviceTypeId);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				//System.out.println("hell");
				subServiceTypeList  = new ArrayList<SubServiceType>();
				 do {
					 SubServiceType subServiceType = new SubServiceType();
					 subServiceType.setSubServiceTypeId(resultSet.getInt(1));
					 subServiceType.setName(resultSet.getString(2));
					 subServiceType.setServiceTypeId(resultSet.getInt(3));
					 subServiceType.setActive(resultSet.getBoolean(4));
					 subServiceType.setIcon(resultSet.getString(5));
					 subServiceTypeList.add(subServiceType);
				} while(resultSet.next());	
				//return userList;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBUtility.close(resultSet,preparedStatement);
		}
		return subServiceTypeList;
	}
	
	public SubServiceType getSubServiceTypeName(int subServiceTypeId) {
		ResultSet resultSet = null;
		SubServiceType subServiceType = new SubServiceType();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from SubServiceType where Sub_Service_Type_Id= ?";
		
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,subServiceTypeId);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next() ) {
					 subServiceType.setSubServiceTypeId(resultSet.getInt(1));
					 subServiceType.setName(resultSet.getString(2));
					 subServiceType.setServiceTypeId(resultSet.getInt(3));
					 subServiceType.setActive(resultSet.getBoolean(4));
					 subServiceType.setIcon(resultSet.getString(5));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBUtility.close(resultSet,preparedStatement);
		}
		return subServiceType;
	}


	public static void main(String[] args) {
		SubServiceTypeDAO subServiceTypeDAO = new SubServiceTypeDAO();
		System.out.println(subServiceTypeDAO.getSubServiceTypeName(401));
	}
}
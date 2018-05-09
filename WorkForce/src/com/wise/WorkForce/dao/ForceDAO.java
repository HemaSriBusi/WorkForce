package com.wise.WorkForce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.WorkForce.dto.Force;

public class ForceDAO {
	public int insert(Force force) {
		int status = 0;
		final String QUERY = "insert into Forces(Force_Id,Sub_Service_Type_Id,Experience,Cost_Per_Hour) values(?,?,?,?)";
		Connection connection = DBUtility.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, force.getForceId());
			preparedStatement.setInt(2, force.getSubServiceTypeId());
			preparedStatement.setInt(3, force.getExperience());
			preparedStatement.setDouble(4, force.getCostPerHour());
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public List<Force> getByForceId(int forceId) {
		ResultSet resultSet = null; 
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from Forces where Force_Id= ?";
		Connection connection = DBUtility.getConnection(); 
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,forceId);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				List<Force> forceList = new ArrayList<Force>();
				do {
					Force force = new Force();
					force.setForceId(resultSet.getInt(1));
					force.setSubServiceTypeId(resultSet.getInt(2));
					force.setExperience(resultSet.getInt(3));
					force.setCostPerHour(resultSet.getDouble(4));				
					forceList.add(force);
				} while(resultSet.next());
				return forceList;
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
	
	public List<Force> getBySubServiceTypeId(int subServiceTypeId) {
		ResultSet resultSet = null; 
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from Forces where Sub_Service_Type_Id= ?";
		Connection connection = DBUtility.getConnection(); 
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,subServiceTypeId);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				List<Force> forceList = new ArrayList<Force>();
				do {
					Force force = new Force();
					force.setForceId(resultSet.getInt(1));
					force.setSubServiceTypeId(resultSet.getInt(2));
					force.setExperience(resultSet.getInt(3));
					force.setCostPerHour(resultSet.getDouble(4));			
					forceList.add(force);
				} while(resultSet.next());
				return forceList;
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
	
	public List<Force> getAll() {
		ResultSet resultSet = null; 
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from Forces";
		Connection connection = DBUtility.getConnection(); 
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				List<Force> forceList = new ArrayList<Force>();
				do {
					Force force = new Force();
					force.setForceId(resultSet.getInt(1));
					force.setSubServiceTypeId(resultSet.getInt(2));
					force.setExperience(resultSet.getInt(3));
					force.setCostPerHour(resultSet.getDouble(4));			
					forceList.add(force);
				} while(resultSet.next());
				return forceList;
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
	public int updateExperience(int forceId,int experience) {
		int status = 0;
		final String QUERY = "update Forces set Experience = ? where Force_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,experience);
			preparedStatement.setInt(2,forceId);
			status = preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	 
	public int updateCostPerHour(int forceId,int costPerHour) {
		int status = 0;
		final String QUERY = "update Forces set Experience = ? where Force_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,costPerHour);
			preparedStatement.setInt(2,forceId);
			status = preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	public static void main(String[] args) {
		ForceDAO forceDAO = new ForceDAO();
		System.out.println(forceDAO.getAll());
	}
}
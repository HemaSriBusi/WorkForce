package com.wise.WorkForce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.WorkForce.dto.ServiceType;

public class ServiceTypeDAO {
	public int insertServiceType(ServiceType serviceType) {
		int status = 0;
		final String QUERY = "insert into ServiceType(Service_Type_Id,Name,Icon,Active) values(?,?,?,?)";
		Connection connection = DBUtility.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, serviceType.getServiceTypeId());
			preparedStatement.setString(2, serviceType.getName());
			preparedStatement.setString(3, serviceType.getIcon());
			preparedStatement.setBoolean(4, serviceType.isActive());
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public ServiceType getByServiceTypeId(int serviceTypeId) {
		ResultSet resultSet = null; 
		ServiceType serviceType = new ServiceType();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from ServiceType where Service_Type_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,serviceTypeId);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				serviceType.setServiceTypeId(resultSet.getInt(1));
				serviceType.setName(resultSet.getString(2));
				serviceType.setIcon(resultSet.getString(3));
				serviceType.setActive(resultSet.getBoolean(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return serviceType;
	}
	
	public List<ServiceType> getAll() {
		ResultSet resultSet = null; 
		PreparedStatement preparedStatement = null;
		List<ServiceType> serviceTypeList = null;
		final String QUERY = "select * from ServiceType";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				 serviceTypeList = new ArrayList<ServiceType>();
				do {
					ServiceType serviceType = new ServiceType();
					serviceType.setServiceTypeId(resultSet.getInt(1));
					serviceType.setName(resultSet.getString(2));
					serviceType.setIcon(resultSet.getString(3));
					serviceType.setActive(resultSet.getBoolean(4));				
					serviceTypeList.add(serviceType);
				} while(resultSet.next());
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return serviceTypeList;
	}
	public static void main(String[] args) {
		ServiceTypeDAO serviceTypeDAO = new ServiceTypeDAO();
		System.out.println(serviceTypeDAO.getByServiceTypeId(1));
	}
	
}
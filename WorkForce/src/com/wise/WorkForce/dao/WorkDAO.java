package com.wise.WorkForce.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.WorkForce.dto.Work;

public class WorkDAO {

	public int insertWork(Work work) {
		int status = 0;
		final String QUERY = "insert into Work(Work_Id,Description,Sub_Service_Type_Id,User_Id,Accepted,Date) values(?,?,?,?,?,?)";
		Connection connection = DBUtility.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, work.getWorkId());
			preparedStatement.setString(2, work.getDescription());
			preparedStatement.setInt(3, work.getSubServiceTypeId());
			preparedStatement.setInt(4, work.getUserId());
			preparedStatement.setBoolean(5, work.isAccepted());
			preparedStatement.setDate(6, new DBUtility().convertUtilToSql(work.getDate()));
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public int workUpdate(int workId) {
		 
		PreparedStatement preparedStatement = null;
		final String QUERY = "update Work set accepted = 1 where Work_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,workId);
			return preparedStatement.executeUpdate();
			/*if ( resultSet.next() ) {
				work.setWorkId(resultSet.getInt(1));
				work.setDescription(resultSet.getString(2));
				work.setSubServiceTypeId(resultSet.getInt(3));
				work.setUserId(resultSet.getInt(4));
				work.setAccepted(resultSet.getBoolean(5));	
				work.setDate(resultSet.getDate(6));
			}*/
			
			//System.out.println(work);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtility.close(preparedStatement);
		}
		return 0;
	}
	
	
	public List<Work> getByWorkId(int workId) {
		ResultSet resultSet = null; 
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from Work where Work_Id = ?";
		Connection connection = DBUtility.getConnection(); 
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,workId);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				List<Work> workList = new ArrayList<Work>();
				do {
					Work work = new Work();
					work.setWorkId(resultSet. getInt(1));
					work.setDescription(resultSet.getString(2));
					work.setSubServiceTypeId(resultSet.getInt(3));
					work.setUserId(resultSet.getInt(4));
					work.setAccepted(resultSet.getBoolean(5));	
					work.setDate(resultSet.getDate(6));
					workList.add(work);
				} while(resultSet.next());
				return workList;
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
	public List<Work> getForceWorkDetails(int subServiceTypeId) {
		ResultSet resultSet = null; 
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from Work where Sub_Service_Type_Id = ? ";
		Connection connection = DBUtility.getConnection(); 
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,subServiceTypeId);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				List<Work> workList = new ArrayList<Work>();
				do {
					Work work = new Work();
					System.out.println(subServiceTypeId);
					work.setWorkId(resultSet.getInt(1));
					work.setDescription(resultSet.getString(2));
					work.setSubServiceTypeId(resultSet.getInt(3));
					work.setUserId(resultSet.getInt(4));
					work.setUser(new UserDAO().get(resultSet.getInt(4)));
					work.setAddress(new AddressDAO().getByUserId(resultSet.getInt(4)));
					work.setAccepted(resultSet.getBoolean(5));	
					work.setDate(resultSet.getDate(6));
					workList.add(work);
				} while(resultSet.next());
				return workList;
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
	public List<Work> getWorkDetails(int userId) {
		ResultSet resultSet = null; 
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from Work  where User_Id = ? ";
		Connection connection = DBUtility.getConnection(); 
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,userId);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				List<Work> workList = new ArrayList<Work>();
				do {
					Work work = new Work();
					work.setWorkId(resultSet.getInt(1));
					work.setDescription(resultSet.getString(2));
					work.setUserId(resultSet.getInt(4));
					work.setSubServiceType(new SubServiceTypeDAO().getSubServiceTypeName(resultSet.getInt(3)));
					//work.setAccepted(resultSet.getBoolean(5));			
					work.setDate(resultSet.getDate(6));
					//work.setBidList(new BidDAO().getBidDetails(resultSet.getInt(1)));
					workList.add(work);
				} while(resultSet.next());
				return workList;
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
	
	
	public static void main(String[] args) {
		System.out.println(new WorkDAO().workUpdate(1));
	}
}
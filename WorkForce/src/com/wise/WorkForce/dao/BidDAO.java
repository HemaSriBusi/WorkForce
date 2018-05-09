package com.wise.WorkForce.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.WorkForce.dto.Bid;

public class BidDAO {
	public int insertBid(Bid bid) {
        int status = 0;
        final String QUERY = "insert into Bid(Force_Id,Bid_Amount,Work_Id,Accepted) values(?,?,?,?)";
	    Connection connection = DBUtility.getConnection();
	    try {
	    	PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
	        preparedStatement.setInt(1, bid.getForceId());
	        preparedStatement.setInt(2, bid.getBidAmount());
	        preparedStatement.setInt(3, bid.getWorkId());
	        preparedStatement.setBoolean(4, false);
	        status = preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
	    return status;
    }
	    
	public List<Bid> getBidsByWorkId(int workId) {
        ResultSet resultSet = null; 
        List<Bid> bidList = null;
	    PreparedStatement preparedStatement = null;
	    final String QUERY = "select * from Bid where Work_Id = ?";
	    Connection connection = DBUtility.getConnection();
	    try {
	    	preparedStatement = connection.prepareStatement(QUERY);
	        preparedStatement.setInt(1,workId);
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	        	bidList = new ArrayList<Bid>();
	        	do {
	        		Bid bid = new Bid();
	            	bid.setForceId(resultSet.getInt(1));
	            	bid.setBidAmount(resultSet.getInt(2));
	            	bid.setWorkId(resultSet.getInt(3));
	            	bid.setAccepted(resultSet.getBoolean(4));
	            	bidList.add(bid);
	            } while ( resultSet.next() );
	         }
	    } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	    }
	    finally {
	        DBUtility.close(resultSet,preparedStatement);
	    }
	    return bidList;
	}
	
	public List<Bid> getBidsByUserId(int forceId) {
        ResultSet resultSet = null; 
        List<Bid> bidList = null;
	    PreparedStatement preparedStatement = null;
	    final String QUERY = "select * from Bid where Work_Id = ?";
	    Connection connection = DBUtility.getConnection();
	    try {
	    	preparedStatement = connection.prepareStatement(QUERY);
	        preparedStatement.setInt(1,forceId);
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	        	bidList = new ArrayList<Bid>();
	        	do {
	        		Bid bid = new Bid();
	            	bid.setForceId(resultSet.getInt(1));
	            	bid.setBidAmount(resultSet.getInt(2));
	            	bid.setWorkId(resultSet.getInt(3));
	            	bid.setAccepted(resultSet.getBoolean(4));
	            	bidList.add(bid);
	            } while ( resultSet.next() );
	         }
	    } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	    }
	    finally {
	        DBUtility.close(resultSet,preparedStatement);
	    }
	    return bidList;
	}
	
	public List<Bid> getBids(int forceId) {
        ResultSet resultSet = null; 
        List<Bid> bidList = null;
	    PreparedStatement preparedStatement = null;
	    final String QUERY = "select * from Bid where Force_Id = ?";
	    Connection connection = DBUtility.getConnection();
	    try {
	    	preparedStatement = connection.prepareStatement(QUERY);
	        preparedStatement.setInt(1,forceId);
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	        	bidList = new ArrayList<Bid>();
	        	do {
	        		Bid bid = new Bid();
	            	bid.setForceId(resultSet.getInt(1));
	            	bid.setBidAmount(resultSet.getInt(2));
	            	bid.setWorkId(resultSet.getInt(3));
					bid.setUser(new UserDAO().get(resultSet.getInt(1)));

	            	bidList.add(bid);
	            } while ( resultSet.next() );
	         }
	    } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	    }
	    finally {
	        DBUtility.close(resultSet,preparedStatement);
	    }
	    return bidList;
	}
	
	public List<Bid> getBidDetails(int workId) {
        ResultSet resultSet = null; 
        List<Bid> bidList = null;
	    PreparedStatement preparedStatement = null;
	    final String QUERY = "select * from Bid where Work_Id = ?";
	    Connection connection = DBUtility.getConnection();
	    try {
	    	preparedStatement = connection.prepareStatement(QUERY);
	        preparedStatement.setInt(1,workId);
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	        	bidList = new ArrayList<Bid>();
	        	do {
	        		Bid bid = new Bid();
	            	bid.setForceId(resultSet.getInt(1));
	            	bid.setBidAmount(resultSet.getInt(2));
	            	bid.setWorkId(resultSet.getInt(3));
	            	bid.setAccepted(resultSet.getBoolean(4));
					bid.setUser(new UserDAO().get(resultSet.getInt(1)));
	            	bidList.add(bid);
	            } while ( resultSet.next() );
	         }
	    } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	    }
	    finally {
	        DBUtility.close(resultSet,preparedStatement);
	    }
	    return bidList;
	}
	
	public List<Bid> getBidDetails(int userId,int workId) {
        ResultSet resultSet = null; 
        List<Bid> bidList = null;
	    PreparedStatement preparedStatement = null;
	    final String QUERY = "select * from Bid where User_Id = ? and Work_Id = ?";
	    Connection connection = DBUtility.getConnection();
	    try {
	    	preparedStatement = connection.prepareStatement(QUERY);
	        preparedStatement.setInt(1,userId);
	        preparedStatement.setInt(2,workId);
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	        	bidList = new ArrayList<Bid>();
	        	do {
	        		Bid bid = new Bid();
	            	bid.setForceId(resultSet.getInt(1));
	            	bid.setBidAmount(resultSet.getInt(2));
	            	bid.setWorkId(resultSet.getInt(3));
	            	bidList.add(bid);
	            } while ( resultSet.next() );
	         }
	    } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	    }
	    finally {
	        DBUtility.close(resultSet,preparedStatement);
	    }
	    return bidList;
	}
	
	
	
	public static void main(String[] args) {
		BidDAO bidDAO = new BidDAO();
		System.out.println(bidDAO.getBidDetails(1));
	}
}
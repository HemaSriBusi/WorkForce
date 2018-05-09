package com.wise.WorkForce.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.WorkForce.dto.Feedback;

public class FeedbackDAO {
	public int insert(Feedback feedback) {
		int status = 0;
		final String QUERY = "insert into Feedback(Given_By_Id,Given_To_Id,Feedback_Type,Comments,Rating) values(?,?,?,?,?)";
		Connection connection = DBUtility.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, feedback.getGivenById());
			preparedStatement.setInt(2, feedback.getGivenToId());
			preparedStatement.setString(3, feedback.getFeedbackType());
			preparedStatement.setString(4, feedback.getComments());
			preparedStatement.setInt(5, feedback.getRating());
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public List<Feedback> getById(int givenToId) {
		ResultSet resultSet = null; 
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from Feedback where Given_To_Id = ?";
		Connection connection = DBUtility.getConnection(); 
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,givenToId);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				List<Feedback> feedbackList = new ArrayList<Feedback>();
				do {
					Feedback feedback = new Feedback();
					feedback.setGivenById(resultSet.getInt(1));
					feedback.setGivenToId(resultSet.getInt(2));
					feedback.setFeedbackType(resultSet.getString(3));
					feedback.setComments(resultSet.getString(4));
					feedback.setRating(resultSet.getInt(5));				
					feedbackList.add(feedback);
				} while(resultSet.next());
				return feedbackList;
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
	
	public List<Feedback> getAll() {
		ResultSet resultSet = null; 
		PreparedStatement preparedStatement = null;
		List<Feedback> feedbackList = null;
		final String QUERY = "select * from Feedback";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				feedbackList = new ArrayList<Feedback>();
				do {
					Feedback feedback = new Feedback();
					feedback.setGivenToId(resultSet.getInt(1));
					feedback.setGivenById(resultSet.getInt(2));
					feedback.setFeedbackType(resultSet.getString(3));
					feedback.setComments(resultSet.getString(4));
					feedback.setRating(resultSet.getInt(5));
					feedbackList.add(feedback);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return feedbackList;
	}
	public static void main(String[] args) {
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		System.out.println(feedbackDAO.getAll());
	}
}
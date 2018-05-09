package com.wise.WorkForce.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DBUtility {
	static Connection connection = null;
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Manforce?useSSL = true","root","root");
		}
		catch(SQLException |ClassNotFoundException exception) {
			exception.printStackTrace();
		}
		return connection;
	}
	//...args - variable length arguments
	public static void close(Object ...args) {
		try {
			for ( Object arg : args ) {
				if ( arg instanceof ResultSet ) {
					((ResultSet) arg).close();
				}
				if ( arg instanceof Connection ) {
					((Connection) arg).close();
				}
				if ( arg instanceof Statement ) {
					((Statement) arg).close();
				}
				if ( arg instanceof PreparedStatement ) {
					((PreparedStatement) arg).close();
				}
			}
		}
		catch ( SQLException exception) {
			exception.printStackTrace();
		}
	}
	public static java.sql.Date convertUtilToSql(Date date){
		return new java.sql.Date(date.getTime());
	}
}

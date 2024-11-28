package com.yumstop.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumstop.dao.UserDAO;
import com.yumstop.dbutils.DBUtils;
import com.yumstop.model.User;

public class UserDAOImpl implements UserDAO{
ArrayList<User> userList = new ArrayList<User>();
	
	private static final String ADD_USER="INSERT INTO USER (userName,email,phoneNumber,password,address) values (?,?,?,?,?)";
	private static final String SELECT_ALL="SELECT * FROM USER";
	private static final String SELECT_ON_EMAIL="SELECT * FROM USER WHERE email=?";
	private static final String UPDATE_ON_EMAIL="UPDATE USER SET userName=?,email=? ,phoneNumber=?,address=? WHERE userId=? ";
	private static final String DELETE_ON_EMAIL="DELETE FROM USER WHERE email=?";
	
	private Connection con;
	private PreparedStatement pstmt;
	private int status;
	private Statement stmt;
	private ResultSet resultSet;
	private User user;

	public UserDAOImpl() {
		
		try {
			con=DBUtils.getConnect();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addUser(User u) {
		try {
			pstmt=con.prepareStatement(ADD_USER);
			pstmt.setString(1, u.getUserName());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getPhoneNumber());
			pstmt.setString(4, u.getPassword());
			pstmt.setString(5, u.getAddress());
			status=pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public ArrayList<User> getAllUsers() {
		
		try {
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(SELECT_ALL);
			userList = getUserFromResultSet(resultSet);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return userList;
	}
	
	ArrayList<User> getUserFromResultSet(ResultSet resultSet){
		try {
			while(resultSet.next()) {
				userList.add(new User(resultSet.getInt("userId"),
				resultSet.getString("userName"),
				resultSet.getString("email"),
				resultSet.getString("phoneNumber"),
				resultSet.getString("password"),
				resultSet.getString("address")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	@Override
	public User getUser(String email) {
		try{
			pstmt=con.prepareStatement(SELECT_ON_EMAIL);
			pstmt.setString(1, email);
			resultSet=pstmt.executeQuery();
			userList=getUserFromResultSet(resultSet);
			user=userList.get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int updateUser(User u) {
		try {
			pstmt=con.prepareStatement(UPDATE_ON_EMAIL);
			
			pstmt.setString(1, u.getUserName());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getPhoneNumber());
			pstmt.setString(4, u.getAddress());
			pstmt.setInt(5, u.getUserId());
			status=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deleteUser(String email) {
		
		try {
			pstmt=con.prepareStatement(DELETE_ON_EMAIL);
			pstmt.setString(1, email);
			status=pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}

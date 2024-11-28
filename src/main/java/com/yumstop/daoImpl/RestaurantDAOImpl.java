package com.yumstop.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumstop.dao.RestaurantDAO;
import com.yumstop.dbutils.DBUtils;
import com.yumstop.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO{
	private static final String ADD_RESTAURANT = "INSERT INTO RESTAURANT (restaurantName, deliveryTime, address, adminUserId, rating, isActive, imagePath) VALUES (?,?,?,?,?,?,?)";
	private static final String SELECT_ALL = "SELECT * FROM RESTAURANT";
	private static final String SELECT_ON_ID = "SELECT * FROM RESTAURANT WHERE restaurantId=?";
	private static final String UPDATE_ON_ID = "UPDATE RESTAURANT SET restaurantName=?, deliveryTime=?, address=?, adminUserId=?, rating=?, isActive=?, imagePath=? WHERE restaurantId=?";
	private static final String DELETE_ON_ID = "DELETE FROM RESTAURANT WHERE restaurantId=?";

	private Connection con;
	private PreparedStatement pstmt;
	private int status;
	private Statement stmt;
	private ResultSet resultSet;
	private ArrayList<Restaurant> restaurantList = new ArrayList<>();

	public RestaurantDAOImpl() {
		try {
			con = DBUtils.getConnect();
			if(con==null) {
				System.out.println("Connection Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addRestaurant(Restaurant restaurant) {
		try {
			pstmt = con.prepareStatement(ADD_RESTAURANT);
			pstmt.setString(1, restaurant.getRestaurantName());
			pstmt.setString(2, restaurant.getDeliveryTime());
			pstmt.setString(3, restaurant.getAddress());
			pstmt.setInt(4, restaurant.getAdminUserId());
			pstmt.setDouble(5, restaurant.getRating());
			pstmt.setBoolean(6, restaurant.isActive());
			pstmt.setString(7, restaurant.getImagePath());
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<Restaurant> getAllRestaurants() {
		try {
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(SELECT_ALL);
			restaurantList = getRestaurantsFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurantList;
	}

	private ArrayList<Restaurant> getRestaurantsFromResultSet(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
				restaurantList.add(new Restaurant(
						resultSet.getInt("restaurantId"),
						resultSet.getString("restaurantName"),
						resultSet.getString("deliveryTime"),
						resultSet.getString("address"),
						resultSet.getInt("adminUserId"),
						resultSet.getDouble("rating"),
						resultSet.getBoolean("isActive"),
						resultSet.getString("imagePath")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurantList;
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		Restaurant restaurant = null;
		try {
			pstmt = con.prepareStatement(SELECT_ON_ID);
			pstmt.setInt(1, restaurantId);
			resultSet = pstmt.executeQuery();
			ArrayList<Restaurant> restaurantList = getRestaurantsFromResultSet(resultSet);
			if (!restaurantList.isEmpty()) {
				restaurant = restaurantList.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurant;
	}

	@Override
	public int updateRestaurant(Restaurant restaurant) {
		try {
			pstmt = con.prepareStatement(UPDATE_ON_ID);
			pstmt.setString(1, restaurant.getRestaurantName());
			pstmt.setString(2, restaurant.getDeliveryTime());
			pstmt.setString(3, restaurant.getAddress());
			pstmt.setInt(4, restaurant.getAdminUserId());
			pstmt.setDouble(5, restaurant.getRating());
			pstmt.setBoolean(6, restaurant.isActive());
			pstmt.setString(7, restaurant.getImagePath());
			pstmt.setInt(8, restaurant.getRestaurantId());
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deleteRestaurant(int restaurantId) {
		try {
			pstmt = con.prepareStatement(DELETE_ON_ID);
			pstmt.setInt(1, restaurantId);
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}

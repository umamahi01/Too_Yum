package com.yumstop.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumstop.dao.OrderTableDAO;
import com.yumstop.dbutils.DBUtils;
import com.yumstop.model.OrderTable;

public class OrderTableDAOImpl implements OrderTableDAO{
	private static final String ADD_ORDER = "INSERT INTO ORDERSTABLE (userId, restuarantId, totalAmount, paymentMethod) VALUES (?,?,?,?)";
	private static final String SELECT_ALL = "SELECT * FROM ORDERSTABLE";
	private static final String SELECT_ON_ID = "SELECT * FROM ORDERSTABLE WHERE orderId=?";
	private static final String UPDATE_ON_ID = "UPDATE ORDERSTABLE SET userId=?, restaurantId=?, totalAmount=?, paymentMethod=? WHERE orderId=?";
	private static final String DELETE_ON_ID = "DELETE FROM ORDERSTABLE WHERE orderId=?";

	private Connection con = null;
	private PreparedStatement pstmt;
	private int status;
	private Statement stmt;
	private ResultSet resultSet;
	private ArrayList<OrderTable> orderList = new ArrayList<>();

	public OrderTableDAOImpl() {
		try {
			con = DBUtils.getConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public int addOrder(OrderTable order) {
		try {
			pstmt = con.prepareStatement(ADD_ORDER);
			pstmt.setInt(1, order.getUserId());
			pstmt.setInt(2, order.getRestaurantId());
			pstmt.setDouble(3, order.getTotalAmount());
		
			pstmt.setString(4, order.getPaymentMethod());
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	

	    @Override
	    public int maxOrderId() {
	        int maxId = 0;
	        String query = "SELECT MAX(orderId) FROM ORDERSTABLE"; // SQL query to get the max order_id

	        try {
	            stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            if (rs.next()) {
	                maxId = rs.getInt(1); // Retrieve the max order ID from the result set
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return maxId;
	    }
	

	@Override
	public ArrayList<OrderTable> getAllOrders() {
		try {
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(SELECT_ALL);
			orderList = getOrdersFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	private ArrayList<OrderTable> getOrdersFromResultSet(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
				orderList.add(new OrderTable(
						resultSet.getInt("orderId"),
						resultSet.getInt("userId"),
						resultSet.getInt("restaurantId"),
						resultSet.getDouble("totalAmount"),
						resultSet.getString("paymentMethod")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public OrderTable getOrder(int orderId) {
		OrderTable order = null;
		try {
			pstmt = con.prepareStatement(SELECT_ON_ID);
			pstmt.setInt(1, orderId);
			resultSet = pstmt.executeQuery();
			ArrayList<OrderTable> orderList = getOrdersFromResultSet(resultSet);
			if (!orderList.isEmpty()) {
				order = orderList.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public int updateOrder(OrderTable order) {
		try {
			pstmt = con.prepareStatement(UPDATE_ON_ID);
			pstmt.setInt(1, order.getUserId());
			pstmt.setInt(2, order.getRestaurantId());
			
			pstmt.setDouble(3, order.getTotalAmount());
			
			pstmt.setString(4, order.getPaymentMethod());
			pstmt.setInt(5, order.getOrderId());
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deleteOrder(int orderId) {
		try {
			pstmt = con.prepareStatement(DELETE_ON_ID);
			pstmt.setInt(1, orderId);
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}

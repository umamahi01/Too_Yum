package com.yumstop.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumstop.dao.OrderItemDAO;
import com.yumstop.dbutils.DBUtils;
import com.yumstop.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO{
	private static final String ADD_ORDER_ITEM = "INSERT INTO ORDERITEM (orderId,menuId, quantity, totalAmount) VALUES (?,?,?,?)";
	private static final String SELECT_ALL = "SELECT * FROM ORDERITEM";
	private static final String SELECT_ON_ID = "SELECT * FROM ORDERITEM WHERE orderItemId=?";
	private static final String SELECT_BY_ORDER_ID = "SELECT * FROM ORDERITEM WHERE orderId=?";
	private static final String UPDATE_ON_ID = "UPDATE ORDERITEM SET orderId=?,menuId=?, quantity=?, itemTotal=? WHERE orderItemId=?";
	private static final String DELETE_ON_ID = "DELETE FROM ORDERITEM WHERE orderItemId=?";

	private Connection con = null;
	private PreparedStatement pstmt;
	private int status;
	private Statement stmt;
	private ResultSet resultSet;
	private ArrayList<OrderItem> orderItemList = new ArrayList<>();

	public OrderItemDAOImpl() {
		try {
			con = DBUtils.getConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addOrderItem(OrderItem orderItem) {
		try {
			pstmt = con.prepareStatement(ADD_ORDER_ITEM);
			pstmt.setInt(1, orderItem.getOrderId());
			pstmt.setInt(2, orderItem.getMenuId());
			pstmt.setInt(3, orderItem.getQuantity());
			pstmt.setDouble(4, orderItem.getTotalAmount());
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<OrderItem> getAllOrderItems() {
		try {
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(SELECT_ALL);
			orderItemList = getOrderItemsFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItemList;
	}

	private ArrayList<OrderItem> getOrderItemsFromResultSet(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
				orderItemList.add(new OrderItem(
						resultSet.getInt("orderItemId"),
						resultSet.getInt("orderId"),
						resultSet.getInt("menuId"),
						resultSet.getInt("quantity"),
						resultSet.getDouble("totalAmount")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItemList;
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		OrderItem orderItem = null;
		try {
			pstmt = con.prepareStatement(SELECT_ON_ID);
			pstmt.setInt(1, orderItemId);
			resultSet = pstmt.executeQuery();
			ArrayList<OrderItem> orderItemList = getOrderItemsFromResultSet(resultSet);
			if (!orderItemList.isEmpty()) {
				orderItem = orderItemList.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItem;
	}

	@Override
	public ArrayList<OrderItem> getOrderItemsByOrderId(int orderId) {
		ArrayList<OrderItem> orderItemsByOrderId = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(SELECT_BY_ORDER_ID);
			pstmt.setInt(1, orderId);
			resultSet = pstmt.executeQuery();
			orderItemsByOrderId = getOrderItemsFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItemsByOrderId;
	}

	@Override
	public int updateOrderItem(OrderItem orderItem) {
		try {
			pstmt = con.prepareStatement(UPDATE_ON_ID);
			pstmt.setInt(1, orderItem.getOrderId());
			pstmt.setInt(2, orderItem.getMenuId());
			pstmt.setInt(3, orderItem.getQuantity());
			pstmt.setDouble(4, orderItem.getTotalAmount());
			pstmt.setInt(5, orderItem.getOrderItemId());
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deleteOrderItem(int orderItemId) {
		try {
			pstmt = con.prepareStatement(DELETE_ON_ID);
			pstmt.setInt(1, orderItemId);
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}

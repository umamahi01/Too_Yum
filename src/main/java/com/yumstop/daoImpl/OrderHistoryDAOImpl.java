package com.yumstop.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumstop.dao.OrderHistoryDAO;
import com.yumstop.dbutils.DBUtils;
import com.yumstop.model.OrderHistory;

public class OrderHistoryDAOImpl implements OrderHistoryDAO{
    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;
    private int status = 0;
    private static String ADD_ALL = "INSERT INTO ORDERHISTORY(orderId, userId, totalAmount) values (?, ?, ?)";
    private static String GET_ALL = "SELECT * FROM ORDERHISTORY";
    private static String GET_ONE = "SELECT * FROM ORDERHISTORY WHERE orderHistoryId=?";
    
    public OrderHistoryDAOImpl() {
        con = DBUtils.getConnect();
    }
    
    @Override
    public int addOrderHistory(OrderHistory orderHistory) {
        try {
            pstmt = con.prepareStatement(ADD_ALL);
            pstmt.setInt(1, orderHistory.getOrderId());
            System.out.println("servlet"+orderHistory.getUserId());
            pstmt.setInt(2, orderHistory.getUserId());
            pstmt.setDouble(3, orderHistory.getTotalAmount());
            
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    @Override
    public ArrayList<OrderHistory> getAllOrderHistory() {
        ArrayList<OrderHistory> orderHistoryList = new ArrayList<>();
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(GET_ALL);
            orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
    
    @Override
    public OrderHistory getOrderHistory(int orderHistoryId) {
        OrderHistory orderHistory = null;
        try {
            pstmt = con.prepareStatement(GET_ONE);
            pstmt.setInt(1, orderHistoryId);
            resultSet = pstmt.executeQuery();
            ArrayList<OrderHistory> orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
            if (!orderHistoryList.isEmpty()) {
                orderHistory = orderHistoryList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistory;
    }
    
    public ArrayList<OrderHistory> extractOrderHistoryFromResultSet(ResultSet resultSet) {
        ArrayList<OrderHistory> orderHistoryList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                orderHistoryList.add(new OrderHistory(
                        resultSet.getInt("orderHistoryId"),
                        resultSet.getInt("orderId"),
                        resultSet.getInt("userId"),
                        resultSet.getDate("orderDate"),
                        resultSet.getDouble("totalAmount")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
    
    // Fetches orders specific to a user by userId
    public ArrayList<OrderHistory> getOrderHistoryByUserId(int userId) {
        ArrayList<OrderHistory> orderHistoryList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement("SELECT * FROM ORDERHISTORY WHERE userId = ?");
            pstmt.setInt(1, userId);
            resultSet = pstmt.executeQuery();
            orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
}

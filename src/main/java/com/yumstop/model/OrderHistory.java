package com.yumstop.model;

import java.sql.Date;

public class OrderHistory {
	private int orderHistoryId;
	private int orderId;
	private int userId;
	private Date orderDate;
	private Double totalAmount;
	private String status;
	
	
	public OrderHistory() {
		super();
	}
	public OrderHistory(int orderHistoryId, int orderId, int userId,Date orderDate, Double totalAmount) {
		super();
		this.orderHistoryId = orderHistoryId;
		this.orderId = orderId;
		this.userId = userId;
		this.orderDate=orderDate;
		this.totalAmount = totalAmount;

	}
	public OrderHistory( int orderId, int userId,Date orderDate, Double totalAmount) {
		super();
	
		this.orderId = orderId;
		this.userId = userId;
		this.orderDate=orderDate;
		this.totalAmount = totalAmount;

	}
	
	public int getOrderHistoryId() {
		return orderHistoryId;
	}
	public void setOrderHistoryId(int orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

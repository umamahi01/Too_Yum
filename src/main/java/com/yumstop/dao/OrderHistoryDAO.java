package com.yumstop.dao;

import java.util.ArrayList;

import com.yumstop.model.OrderHistory;

public interface OrderHistoryDAO {
	int addOrderHistory(OrderHistory orderHistory);
	ArrayList<OrderHistory> getAllOrderHistory();
	OrderHistory getOrderHistory(int orderHistoryId);
}

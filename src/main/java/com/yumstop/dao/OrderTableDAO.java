package com.yumstop.dao;

import java.util.ArrayList;

import com.yumstop.model.OrderTable;

public interface OrderTableDAO {
	 int addOrder(OrderTable order);
	    int maxOrderId();
	    ArrayList<OrderTable> getAllOrders();
	    OrderTable getOrder(int orderId);
	    int updateOrder(OrderTable order);
	    int deleteOrder(int orderId);
}

package com.yumstop.dao;

import java.util.ArrayList;

import com.yumstop.model.OrderItem;

public interface OrderItemDAO {
	int addOrderItem(OrderItem orderItem);
    ArrayList<OrderItem> getAllOrderItems();
    OrderItem getOrderItem(int orderItemId);
    ArrayList<OrderItem> getOrderItemsByOrderId(int orderId);
    int updateOrderItem(OrderItem orderItem);
    int deleteOrderItem(int orderItemId);
}

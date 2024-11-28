package com.yumstop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yumstop.dao.OrderHistoryDAO;
import com.yumstop.dao.OrderItemDAO;
import com.yumstop.dao.OrderTableDAO;
import com.yumstop.daoImpl.OrderHistoryDAOImpl;
import com.yumstop.daoImpl.OrderItemDAOImpl;
import com.yumstop.daoImpl.OrderTableDAOImpl;
import com.yumstop.model.Cart;
import com.yumstop.model.CartItem;
import com.yumstop.model.Menu;
import com.yumstop.model.OrderHistory;
import com.yumstop.model.OrderItem;
import com.yumstop.model.OrderTable;
import com.yumstop.model.User;

@WebServlet("/orderItemServlet")
public class OrderItemServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        // Retrieve user and restaurant details from session
        User userList = (User) session.getAttribute("user");
        int restaurantId = (int) session.getAttribute("restaurantId");
        int userId = userList.getUserId();
        String userName = userList.getUserName();
        ArrayList<Menu> menuList = (ArrayList<Menu>) session.getAttribute("menuList");
        int menuId=0;
        if (menuList != null) {
            for (Menu menuItem : menuList) {
                 menuId = menuItem.getMenuId();
                System.out.println("Menu ID: " + menuId);
            }
        } else {
            System.out.println("No menu items found in session.");
        }
        
        // Retrieve total amount and payment method from the request
        double totalAmount = Double.parseDouble(req.getParameter("itemsTotal"));

        // Retrieve payment method
        String paymentMethod = req.getParameter("paymentMethod");

        // Create OrderTable object and set its fields
        OrderTable orderTable = new OrderTable();
        orderTable.setUserId(userId);;
        orderTable.setRestaurantId(restaurantId);
        orderTable.setTotalAmount(totalAmount);
        orderTable.setPaymentMethod(paymentMethod);
        
        //inserting to OrderHistory
        Cart cart=(Cart)session.getAttribute("cart");
        int orderId=0;
        OrderItem orderItem=new OrderItem();
        Map<Integer, CartItem> cartItems= cart.getItems();
        OrderTableDAO orderTableDAO=new OrderTableDAOImpl();
        for(int key:cartItems.keySet()) {
        	orderId=orderTableDAO.maxOrderId();
        	System.out.println(orderId);
        	int quantity=cartItems.get(key).getQuantity();
        	orderItem.setOrderId(orderId);
        	orderItem.setMenuId(menuId);
        	orderItem.setQuantity(quantity);
        	orderItem.setTotalAmount(totalAmount);
        	
        }
        
        
        
        OrderItemDAO orderItemDAO=new OrderItemDAOImpl();
        int result2=orderItemDAO.addOrderItem(orderItem);
        System.out.println(result2);
        // Use DAO to add the order to the database

        int result1 = orderTableDAO.addOrder(orderTable);
        
        OrderHistory orderHistory=new OrderHistory();
        orderHistory.setOrderId(orderId);
        System.out.println("OrderId: "+orderId);
        orderHistory.setUserId(userId);
        System.out.println(userId);
        orderHistory.setTotalAmount(totalAmount);
        
        OrderHistoryDAO orderHistoryDAO=new OrderHistoryDAOImpl();
        int result3=orderHistoryDAO.addOrderHistory(orderHistory);
        // Redirect based on the result
        if (result1 == 1 && result2==1 && result3==1) {
            resp.sendRedirect("orderConfirm.jsp?userName=" + userName);
        } else {
            resp.sendRedirect("failure.jsp");
        }
        
        // Clear the cart after order is placed
        session.setAttribute("cart", null);
    }

}

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yumstop.model.OrderHistory" %>
<%@ page import="com.yumstop.daoImpl.OrderHistoryDAOImpl" %>
<%@ page import="com.yumstop.model.User" %> <!-- Assuming a User model exists -->
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    // Get the user from session
    User userList = (User) session.getAttribute("user");
    if (userList == null) {
        response.sendRedirect("home.jsp"); // Redirect to home page if no user is logged in
        return;
    }
    
    int userId = userList.getUserId();
    OrderHistoryDAOImpl orderHistoryDAO = new OrderHistoryDAOImpl();
    ArrayList<OrderHistory> orderHistoryList = orderHistoryDAO.getOrderHistoryByUserId(userId);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Your Order History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            background-image: url('https://img.freepik.com/free-photo/grapefruit-background-copyspace-right_23-2147822416.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
        }

        .container {
            width: 60%;
            padding: 20px;
            background-color: #ffffff;
            color: #333;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 4cm;
            position: absolute;
            right: calc(0px + 4cm);
            opacity: 0;
            animation: fadeIn 1s forwards;
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        h2 {
            color: #ff7f00;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #ff7f00;
            color: white;
        }

        .empty-message {
            text-align: center;
            font-size: 18px;
            color: #555;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Your Order History</h2>

        <% if (orderHistoryList.isEmpty()) { %>
            <p class="empty-message">You have no order history.</p>
        <% } else { %>
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Total Amount</th>
                        <th>Order Date</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (OrderHistory orderHistory : orderHistoryList) { %>
                        <tr>
                            <td><%= orderHistory.getOrderId() %></td>
                            <td>$<%= orderHistory.getTotalAmount() %></td>
                            <td><%= orderHistory.getOrderDate() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>
    </div>

</body>
</html>
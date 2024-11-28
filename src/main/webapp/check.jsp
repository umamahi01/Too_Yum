<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.yumstop.model.CartItem" %>
<%@ page import="com.yumstop.model.Cart" %>
<%@ page import="java.util.ArrayList" %>

<%
    // Retrieve the cart object from the session
    Cart cart = (Cart) session.getAttribute("cart");

    // Initialize the itemsTotal to 0.0 if not passed
    double itemsTotal = 0.0;
    int quantity = 0;
    if (cart != null && !cart.getItems().isEmpty()) {
        // Calculate total price of items in the cart
        for (CartItem item : cart.getItems().values()) {
            itemsTotal += item.getPrice() * item.getQuantity();
            quantity = item.getQuantity();
        }
    } else {
        out.println("<h2>No items in the cart!</h2>");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Billing Process</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('https://t3.ftcdn.net/jpg/06/75/72/98/360_F_675729886_wpafoYC7P7QT6KDNNeHvNULUCJXgkVK2.jpg');
            background-size: cover;
            background-position: center;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #fff;
        }
        .container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 600px; /* Increased width of the form */
            text-align: center;
            margin-left: -4cm; /* Move the form 4cm to the left */
            box-sizing: border-box; /* Ensures padding and border are included in width */
            opacity: 0;
            animation: fadeIn 1s forwards; /* Apply the fade-in effect */
        }
        
        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }

        h2 {
            color: #d9534f; /* Red color */
            margin-bottom: 20px;
            font-size: 24px;
        }
        table {
            width: 100%;
            margin-bottom: 20px;
            background-color: rgba(255, 255, 255, 0.3);
        }
        th, td {
            padding: 10px;
            text-align: center;
            color: #333;
        }
        th {
            background-color: #d9534f; /* Red color */
            color: white;
            font-weight: bold;
        }
        td {
            font-weight: bold;
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #333;
            font-weight: bold;
        }
        textarea {
            width: 100%;
            height: 80px;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-bottom: 15px;
            resize: none;
            box-sizing: border-box; /* Ensures padding is included in the width */
        }
        .payment-options {
            margin-bottom: 15px;
            text-align: left;
        }
        .payment-options label {
            display: flex;
            align-items: center;
            margin-bottom: 5px;
            color: #333;
            font-weight: bold;
        }
        .payment-options input[type="radio"] {
            margin-right: 10px;
            accent-color: #d9534f; /* Red accent color */
        }
        input[type="submit"] {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #d9534f; /* Red color */
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
            font-weight: bold;
        }
        input[type="submit"]:hover {
            background-color: #c9302c; /* Darker red */
        }
    </style>
    <script>
        function validateForm() {
            var address = document.getElementById('address').value;
            if (address.trim() === '') {
                alert('Please enter your delivery address.');
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Billing Process</h2>

        <form action="orderItemServlet?quantity" method="post" onsubmit="return validateForm()">
            <table>
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (CartItem item : cart.getItems().values()) { %>
                        <tr>
                            <td><%= item.getItemName() %></td>
                            <td><%= item.getQuantity() %></td>
                            <td>$<%= item.getPrice() * item.getQuantity() %></td>
                        </tr>
                    <% } %>
                    <tr>
                        <td colspan="2"><strong>Total Amount:</strong></td>
                        <td><strong>$<%= itemsTotal %></strong></td>
                    </tr>
                </tbody>
            </table>

            <input type="hidden" name="itemsTotal" value="<%= itemsTotal %>">

            <label for="address">Delivery Address:</label>
            <textarea id="address" name="address" required></textarea>

            <label>Payment Method:</label>
            <div class="payment-options">
                <label><input type="radio" name="paymentMethod" value="Credit Card" required> Credit Card</label>
                <label><input type="radio" name="paymentMethod" value="Debit Card"> Debit Card</label>
                <label><input type="radio" name="paymentMethod" value="UPI"> UPI</label>
                <label><input type="radio" name="paymentMethod" value="Cash"> Cash On Delivery</label>
            </div>

            <input type="submit" value="Place Order">
        </form>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.yumstop.model.Cart" %> 
<%@ page import="com.yumstop.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <script>
        function decreaseQuantity(itemId) {
            let quantityInput = document.getElementById('quantity-' + itemId);
            let currentQuantity = parseInt(quantityInput.value);
            if (currentQuantity > 1) {
                quantityInput.value = currentQuantity - 1;
            }
        }

        function increaseQuantity(itemId) {
            let quantityInput = document.getElementById('quantity-' + itemId);
            let currentQuantity = parseInt(quantityInput.value);
            quantityInput.value = currentQuantity + 1;
        }

        function validateQuantity(itemId) {
            let quantityInput = document.getElementById('quantity-' + itemId);
            let currentQuantity = parseInt(quantityInput.value);
            if (isNaN(currentQuantity) || currentQuantity < 1) {
                quantityInput.value = 1;  // Reset to minimum if the input is invalid
            }
        }

        // Smooth scroll effect when the page is loaded or when an action is taken
        window.onload = function() {
            document.body.style.opacity = '1'; // Make sure the page is visible once everything is loaded
            document.body.style.transition = 'opacity 1s ease-in-out';
        };

        // Smooth Scroll on Button Click
        document.querySelector('.add-more-items button').addEventListener('click', function(event) {
            event.preventDefault(); // Prevent the default link behavior
            window.scrollTo({
                top: document.querySelector('.cart-container').offsetTop,
                behavior: 'smooth'
            });
        });
    </script>
    
    <style>
        /* Smooth fade-in effect */
        body {
            font-family: Arial, sans-serif;
            background-image: url('https://images.unsplash.com/photo-1490818387583-1baba5e638af?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTd8fHxlbnwwfHx8fHw%3D');
            background-size: cover;
            background-position: center;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            color: #fff;
            opacity: 0;
            transition: opacity 1s ease-in-out;
        }
        h1 {
            color: red;
            text-align: center;
            margin-left: 4cm;
        }
        .cart-container {
            background-color: rgba(255, 255, 255, 0.9);
            border: 1px solid #ddd;
            padding: 15px;
            width: 400px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
            margin-bottom: 20px;
            margin-left: 4cm;
            opacity: 0;
            animation: fadeIn 1s forwards;
        }
        .cart-item {
            border-bottom: 1px solid #eee;
            padding: 10px 0;
            text-align: center;
            opacity: 0;
            animation: fadeIn 1s forwards;
            animation-delay: 0.5s;
        }
        .cart-item h3 {
            margin: 0 0 10px 0;
            color: black;
        }
        .cart-item p {
            margin: 5px 0;
            color: #555;
        }
        .cart-item form {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-top: 10px;
        }
        label {
            margin-right: 10px;
            color: #ffffff;
        }
        /* Styling for buttons */
        .update-button, .remove-button, .add-more-items button, .checkout input[type="submit"] {
            padding: 6px 12px;
            font-size: 0.9em;
            border-radius: 5px;
            border: none;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .update-button {
            background-color: green;
        }
        .update-button:hover {
            background-color: #218838;
        }
        .remove-button {
            background-color: red;
        }
        .remove-button:hover {
            background-color: #c82333;
        }
        .add-more-items button {
            background-color: red;
            padding: 12px 30px;
            font-size: 1.1em;
            width: 100%;
            margin-bottom: 10px;
        }
        .add-more-items button:hover {
            background-color: darkred;
        }
        .checkout input[type="submit"] {
            background-color: green;
            padding: 12px 40px;
            font-size: 1.2em;
            width: 100%;
        }
        .checkout input[type="submit"]:hover {
            background-color: #45a049;
        }
        .quantity-controls button {
            background-color: #FF69B4;
            color: white;
            border: none;
            padding: 6px 10px;
            font-size: 0.8em;
            cursor: pointer;
        }
        .quantity-controls .decrease-btn {
            background-color: red;
        }
        .quantity-controls .increase-btn {
            background-color: green;
        }
        .quantity-input {
            width: 50px;
            text-align: center;
            background-color: #f0f0f0;
            color: #333;
            border: 1px solid #ccc;
            font-size: 0.9em;
            padding: 4px;
            margin: 0 5px;
        }
        .button-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 20px;
            margin-left: 4cm;
            gap: 10px;
            opacity: 0;
            animation: fadeIn 1s forwards;
            animation-delay: 1s;
        }
        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }
    </style>
</head>
<body>

    <h1>Your Cart</h1>
    
    <div class="cart-container">
        <% Cart cart = (Cart) session.getAttribute("cart"); 
        if (cart != null && !cart.getItems().isEmpty()) {
            for (CartItem item : cart.getItems().values()) { %>
                <div class="cart-item">
                    <h3><%= item.getItemName() %></h3>  
                    <p>Price: $<%= item.getPrice() %></p>
                    <form action="addToCartServlet">
                        <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                        <label>Quantity: 
                            <div class="quantity-controls" style="display: flex; align-items: center;">
                                <button type="button" onclick="decreaseQuantity('<%= item.getItemId() %>')" class="decrease-btn">-</button>
                                 
                                <input type="text" name="quantity" id="quantity-<%= item.getItemId() %>" 
                                       value="<%= item.getQuantity() %>" 
                                       class="quantity-input" 
                                       oninput="validateQuantity('<%= item.getItemId() %>')">
                                 
                                <button type="button" onclick="increaseQuantity('<%= item.getItemId() %>')" class="increase-btn">+</button>
                            </div>
                        </label>
                        <input type="submit" name="action" value="Update" class="update-button">
                        <input type="submit" name="action" value="Remove" class="remove-button">
                    </form>
                </div>
        <% }
        } else { %>
            <p>Your Cart is Empty</p>
        <% } %>
    </div>

    <div class="button-container">
        <div class="add-more-items">
            <a href="menuServlet?restaurantId=<%= session.getAttribute("restaurantId") %>">
                <button>Add More Items</button>
            </a>
        </div>

        <% if (cart != null && !cart.getItems().isEmpty()) { %>
        <div class="checkout">
            <form action="check.jsp">
                <input type="submit" value="Proceed to Payment">
            </form>
        </div>
        <% } %>
    </div>

</body>
</html>
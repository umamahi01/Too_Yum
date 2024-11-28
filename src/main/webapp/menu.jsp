<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.yumstop.model.Menu" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu for <%= request.getParameter("restaurantName") %></title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            color: #343a40;
            margin: 0;
            padding: 20px;
            background-image: url('https://wallpapers.com/images/featured/delicious-food-pictures-i5wjpvjqrk3qroy0.jpg');
            background-size: cover; /* Ensures the image covers the entire page */
            background-position: center; /* Centers the background image */
            background-attachment: fixed; /* Keeps the background fixed when scrolling */
        }

        header {
            text-align: center;
            margin-bottom: 40px;
        }

        header h1 {
            color: white;
            font-size: 2.5em;
            font-weight: bold;
            margin: 0;
        }

        .menu-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            max-width: 1200px;
            margin: auto;
        }

        .menu-item {
            background-color: #ffffff;
            border: 1px solid #dee2e6;
            border-radius: 12px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 280px;
            transition: transform 0.3s, box-shadow 0.3s, opacity 0.5s ease-in-out;
            text-align: center;
            opacity: 0;
            transform: translateY(30px); /* Items start from below */
        }

        .menu-item.visible {
            opacity: 1;
            transform: translateY(0); /* Items move to their original position */
        }

        .menu-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
        }

        .menu-item img {
            width: 100%;
            height: auto;
            border-radius: 10px;
            margin-bottom: 15px;
            max-height: 160px;
            object-fit: cover;
        }

        .menu-item h3 {
            font-size: 1.4em;
            color: #FF5733;
            margin-bottom: 10px;
            font-weight: bold;
        }

        .menu-item p {
            font-size: 1em;
            color: #495057;
            margin-bottom: 10px;
        }

        .menu-item .price {
            font-weight: bold;
            color: #28a745;
            font-size: 1.2em;
        }

        .add-to-cart {
            background-color: #ff5722;
            color: white;
            padding: 8px 12px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-size: 0.95em;
            margin-top: 10px;
        }

        .add-to-cart:hover {
            background-color: #e64a19;
        }

        .quantity-label {
            margin-top: 10px;
            font-weight: bold;
            color: #343a40;
        }

        @media (max-width: 600px) {
            .menu-item {
                width: 100%;
                max-width: 100%;
            }
        }
    </style>
</head>
<body>

<header>
    <h1>Menu for <%= request.getParameter("restaurantName") %></h1>
</header>

<div class="menu-container">
<%
    ArrayList<Menu> menuList = (ArrayList<Menu>) session.getAttribute("menuList");

    if (menuList != null && !menuList.isEmpty()) {
        for (Menu menuItem : menuList) {
            String itemImagePath = menuItem.getImagePath(); // Ensure getImagePath() is defined in Menu model
%>
        <div class="menu-item">
            <img src="<%= itemImagePath %>" alt="Image of <%= menuItem.getItemName() %>">
            <h3><%= menuItem.getItemName() %></h3>
            <p class="description"><%= menuItem.getDescription() %></p>
            <p class="price">$<%= menuItem.getPrice() %></p>
            <form action="addToCartServlet?itemId=<%= menuItem.getMenuId() %>&restaurantId=<%= session.getAttribute("restaurantId") %>&restaurantName=<%= session.getAttribute("restaurantName") %>" method="post">
                <input type="hidden" name="itemId" value="<%= menuItem.getMenuId() %>">
                <input type="submit" class="add-to-cart" value="Add to Cart">
                <div class="quantity-label">Quantity:</div>
                <input type="number" name="quantity" value="1" min="1" style="width: 60px; margin-top: 5px; padding: 5px; border-radius: 5px; border: 1px solid #ccc;">
                <input type="hidden" name="action" value="add">
            </form>
        </div>
<%
        }
    } else {
%>
        <p>No menu items available.</p>
<%
    }
%>
</div>

<script>
    // Add 'visible' class to each menu item with a delay to create a smooth transition
    document.addEventListener("DOMContentLoaded", function() {
        const menuItems = document.querySelectorAll('.menu-item');
        menuItems.forEach((item, index) => {
            setTimeout(() => {
                item.classList.add('visible');
            }, index * 200); // Delay each item by 200ms
        });
    });
</script>

</body>
</html>
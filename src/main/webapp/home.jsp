<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.yumstop.model.User"%>
<%@ page import="com.yumstop.model.Restaurant"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>YumStop - Home</title>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
<style>
body {
    font-family: 'Poppins', sans-serif;
    background: url('https://img.freepik.com/premium-photo/sandwich-with-sun-dried-tomato-tasty-snack-concept_185193-132520.jpg') no-repeat center center fixed;
    background-size: cover;
    margin: 0;
    padding: 0;
    color: #fff;
    overflow-x: hidden;
}

/* Navbar Styling */
.navbar {
    padding: 15px 30px;
    display: flex;
    align-items: center;
    position: fixed;
    width: 100%;
    top: 0;
    z-index: 1000;
    background-color: rgba(255, 255, 255, 0);
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.navbar .logo {
    color: #000;
    font-size: 26px;
    font-weight: 600;
    text-decoration: none;
    flex: 1;
}

.navbar ul {
    list-style: none;
    display: flex;
    justify-content: flex-end;
    margin-left: auto;
    margin-right: 30px;
}

.navbar ul li {
    margin: 0 15px;
}

.navbar ul li a {
    text-decoration: none;
    background-color: #007bff;
    color: white;
    padding: 12px 20px;
    font-size: 16px;
    font-weight: 600;
    border-radius: 8px;
    transition: background-color 0.3s ease, transform 0.2s;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

.navbar ul li a:hover {
    background-color: #0056b3;
    transform: translateY(-2px);
}

/* Welcome Section Styling */
.welcome-section {
    text-align: center;
    margin-top: 100px;
    color: #000;
    padding: 0 20px;
}

.welcome-section h2 {
    font-size: 32px;
    margin: 10px 0;
    font-weight: 700;
    color: #000;
    font-weight: bold;
}

.welcome-section h3 {
    font-size: 20px;
    color: #000;
    font-weight: bold;
}

/* Restaurant Section Styling */
.restaurant-title {
    text-align: center;
    margin-top: 40px;
    font-size: 28px;
    font-weight: 600;
    color: #000;
    font-weight: bold;
}

/* Restaurant Card Styling */
.restaurant-container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center;
    margin-top: 20px;
    padding: 0 20px;
}

.restaurant-card {
    background-color: white;
    border: 1px solid #ddd;
    border-radius: 10px;
    padding: 20px;
    width: 300px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s, box-shadow 0.3s, opacity 0.6s ease-in-out;
    opacity: 0;
    visibility: hidden; /* Initially hidden */
}

.restaurant-card.visible {
    opacity: 1;
    visibility: visible; /* Make visible once loaded */
}

.restaurant-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 30px rgba(0, 0, 0, 0.2);
}

.restaurant-card img {
    width: 100%;
    height: auto;
    border-radius: 10px;
    opacity: 0;
    transition: opacity 0.5s ease-in-out; /* Smooth transition for image fade-in */
}

.restaurant-card img.loaded {
    opacity: 1; /* Visible after loading */
}

.restaurant-card h4 {
    margin: 15px 0;
    font-size: 1.4em;
    color: #333;
    font-weight: bold;
}

.restaurant-card p {
    margin: 5px 0;
    font-size: 0.95em;
    color: #666;
    font-weight: bold;
}

.rating {
    color: #ffa500;
    font-weight: bold;
    display: inline-block;
    margin-top: 10px;
}

/* View Menu Button */
.view-menu-button {
    display: inline-block;
    margin-top: 10px;
    background-color: #28a745;
    color: white;
    padding: 10px 20px;
    text-decoration: none;
    border-radius: 5px;
    transition: background-color 0.3s ease, transform 0.2s;
    font-weight: bold;
}

.view-menu-button:hover {
    background-color: #218838;
    transform: translateY(-2px);
}

/* Invalid User Message */
.invalid-user {
    background-color: #ffdddd;
    border: 1px solid #ff4d4d;
    padding: 20px;
    margin: 20px 0;
    color: #ff4d4d;
    text-align: center;
    font-weight: bold;
}

/* Footer Styling */
footer {
    position: relative;
    width: 100%;
    text-align: center;
    color: white;
    padding: 15px 0;
    background-color: rgba(0, 0, 0, 0.5);
    font-size: 0.9em;
    font-weight: 500;
    margin-top: 40px;
}

footer a {
    color: #FFD700;
    text-decoration: none;
    margin: 0 10px;
    transition: color 0.3s ease;
    font-weight: bold;
}

footer a:hover {
    color: #FF4500;
}
</style>
</head>
<body>
    <div class="navbar">
        <a href="home.jsp" class="logo">YumStop</a>
        <ul>
            <li><a href="cart.jsp">View Cart</a></li>
            <li><a href="orderHistory.jsp">View Order History</a></li>
            <li><a href="logoutServlet">Logout</a></li>
        </ul>
    </div>

    <div class="welcome-section">
        <%
            User user = (User) session.getAttribute("user");
            if (user != null) {
        %>
        <h2>
            Welcome,
            <%= user.getUserName() %>!
        </h2>
        <h3>Explore all the amazing restaurants available to you!</h3>
        <%
            } else {
        %>
        <div class="invalid-user">
            <h2>INVALID USER</h2>
        </div>
        <%
            }
        %>
    </div>

    <h3 class="restaurant-title">Available Restaurants:</h3>

    <%
        ArrayList<Restaurant> restaurantList = (ArrayList<Restaurant>) session.getAttribute("restaurantList");
        if (restaurantList != null && !restaurantList.isEmpty()) {
    %>
    <div class="restaurant-container">
        <%
            for (Restaurant restaurant : restaurantList) {
                int restaurantId = restaurant.getRestaurantId();
                String restaurantName = restaurant.getRestaurantName();
                String deliveryTime = restaurant.getDeliveryTime();
                String address = restaurant.getAddress();
                double rating = restaurant.getRating();
                String imagePath = restaurant.getImagePath(); // Assuming this method exists
        %>
        <div class="restaurant-card">
            <img src="placeholder.jpg" data-src="<%= imagePath %>" alt="<%= restaurantName %> Image" class="lazyload">
            <h4><%= restaurantName %></h4>
            <p><strong>Delivery Time:</strong> <%= deliveryTime %></p>
            <p><strong>Address:</strong> <%= address %></p>
            <span class="rating">Rating: <%= rating %></span><br> 
            <a href="menuServlet?restaurantId=<%= restaurantId %>&restaurantName=<%= restaurantName %>" class="view-menu-button">View Menu</a>
        </div>
        <%
            }
        %>
    </div>
    <%
        } else {
    %>
    <div class="invalid-user">
        <h2>No Restaurants Available</h2>
    </div>
    <%
        }
    %>

    <footer>
        <p>&copy; 2024 YumStop. All rights reserved.</p>
    </footer>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const lazyImages = document.querySelectorAll('img.lazyload');
            
            const imageObserver = new IntersectionObserver((entries, observer) => {
                entries.forEach(entry => {
                    if (entry.isIntersecting) {
                        const lazyImage = entry.target;
                        lazyImage.src = lazyImage.getAttribute('data-src');
                        lazyImage.classList.remove('lazyload');
                        
                        // Add the loaded class to trigger fade-in
                        lazyImage.classList.add('loaded');
                        
                        observer.unobserve(lazyImage);
                    }
                });
            });
            
            lazyImages.forEach(image => imageObserver.observe(image));

            // Make restaurant cards visible with smooth transition
            const cards = document.querySelectorAll('.restaurant-card');
            cards.forEach(card => {
                setTimeout(() => {
                    card.classList.add('visible');
                }, 200); // Adding a slight delay for smoother transition
            });
        });
    </script>
</body>
</html>
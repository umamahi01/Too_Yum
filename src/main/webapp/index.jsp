<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.yumstop.model.Restaurant"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>YumStop - Home</title>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap"
	rel="stylesheet">
<style>              
body {
	font-family: 'Poppins', sans-serif;
	background:
		url('https://wallpapercave.com/wp/wp3094970.jpg')
		no-repeat center center fixed;
	background-size: cover;
	margin: 0;
	padding: 0;
	color: #333;
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
    background-color: rgba(255, 255, 255, 0.2); /* Slightly more opaque white background */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Light shadow for added depth */
}

.navbar .logo {
    color: white; /* Sets text color to white */
    font-size: 24px;
    font-weight: bold; /* Makes the text bold */
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
	margin: 0 10px;
}

.navbar ul li a {
	text-decoration: none;
	background-color: #007bff;
	color: white;
	padding: 12px 20px;
	font-size: 16px;
	font-weight: 600;
	border-radius: 8px;
	transition: background-color 0.3s ease;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

.navbar ul li a:hover {
	background-color: #0056b3;
}

.welcome-section {
    position: relative;
    top: 0;
    left: 0;
    background-color: rgba(0, 0, 0, 0.1); /* Almost fully transparent */
    color: white;
    width: 90%;
    max-width: 600px;
    animation: fadeInUp 1.5s ease;
    text-align: center;
    padding: 30px;
    margin: 100px auto 20px;
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3); /* Reduced shadow for a softer look */
}

.welcome-section h1 {
	font-size: 30px; /* Reduced font size */
	margin: 0;
	font-weight: 600;
	text-shadow: 2px 2px 6px rgba(0, 0, 0, 0.5);
}

.welcome-section p {
	font-size: 20px;
	margin-top: 10px;
	/* Reduced margin for better fit */
	line-height: 1.4; /* Adjusted line height */
	color: white;
	font-weight: bold;
}

/* Restaurant Card Styling */
.restaurant-container {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
	justify-content: center;
	margin-top: 20px; /* Space for titles */
	padding: 0 20px; /* Padding for better alignment */
}

.restaurant-card {
    background-color: rgba(255, 255, 255, 0.9); /* Light background color for visibility */
    border: 1px solid #ddd;
    border-radius: 10px;
    padding: 20px;
    width: 300px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s, box-shadow 0.3s;
    text-align: center;
    opacity: 0; /* Start as invisible */
    animation: fadeIn 0.5s forwards;
}

.restaurant-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 8px 30px rgba(0, 0, 0, 0.2);
}

.restaurant-card img {
	width: 100%;
	height: auto;
	border-radius: 10px;
}

.restaurant-card h4 {
	margin: 15px 0;
	font-size: 1.4em;
	color: #333;
}

.restaurant-card p {
	margin: 5px 0;
	font-size: 0.95em;
	color: #666;
}

.rating {
	color: #ffa500;
	font-weight: bold;
	display: inline-block;
	margin-top: 10px;
}

/* Footer Styling */
footer {
	text-align: center;
	padding: 20px;
	background-color: rgba(255, 255, 255, 0.9);
	position: relative;
	bottom: 0;
	width: 100%;
	box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
}

footer p {
	margin: 5px 0;
}

footer a {
	color: #007bff;
	text-decoration: none;
}

@keyframes fadeInUp {
    0% {
        opacity: 0;
        transform: translateY(20px);
    }
    100% {
        opacity: 1;
        transform: translateY(0);
    }
}

@keyframes fadeIn {
    0% {
        opacity: 0;
    }
    100% {
        opacity: 1;
    }
}

</style>
</head>
<body>
	<div class="navbar">
		<a href="home.jsp" class="logo">YumStop</a>
		<ul>
			<li><a href="signUp.jsp">Sign Up</a></li>
			<li><a href="signIn.jsp">Sign In</a></li>
		</ul>
	</div>

	<div class="welcome-section">
		<h1>Welcome to YumStop: Fresh Flavours Delivered!</h1>
		<p>Explore the best restaurants near you.</p>
	</div>

	<div class="restaurant-container" id="restaurantContainer">
		<% 
            // Retrieve restaurant list from session
            ArrayList<Restaurant> restaurantList = (ArrayList<Restaurant>) session.getAttribute("restaurantList");

            // Check if the restaurant list is not null and contains at least one restaurant
            if (restaurantList != null && !restaurantList.isEmpty()) {
                // Loop through the restaurant list to display each restaurant's details
                for (Restaurant restaurant : restaurantList) {
                    int restaurantId = restaurant.getRestaurantId();
                    String restaurantName = restaurant.getRestaurantName();
                    String deliveryTime = restaurant.getDeliveryTime();
                    String address = restaurant.getAddress();
                    double rating = restaurant.getRating();
                    String imagePath = restaurant.getImagePath(); // Assuming this method exists
        %>
		<div class="restaurant-card" id="restaurant<%= restaurantId %>">
			<img src="<%= imagePath %>" alt="<%= restaurantName %> Image">
			<h4><%= restaurantName %></h4>
			<p>
				<strong>Delivery Time:</strong>
				<%= deliveryTime %></p>
			<p>
				<strong>Address:</strong>
				<%= address %></p>
			<span class="rating">Rating: <%= rating %></span>
		</div>
		<%
                }
            } else {
        %>
		<h3>No restaurants available at the moment. Please check back later.</h3>
		<%
            }
        %>
	</div>

	<footer>
		<p>&copy; 2024 YumStop. All rights reserved.</p>
		<a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a>
	</footer>

	<script>
        // JavaScript to animate restaurant cards one by one with a delay
        window.onload = function() {
            const restaurants = document.querySelectorAll('.restaurant-card');
            let delay = 0;

            restaurants.forEach((restaurant, index) => {
                setTimeout(() => {
                    restaurant.style.opacity = 1; // Reveal restaurant card
                    restaurant.style.animation = 'fadeIn 1s forwards';
                }, delay);
                delay += 500; // Increase delay for each subsequent restaurant
            });
        };
    </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Order Confirmation</title>
    <style>
        /* Smooth fade-in animation */
        @keyframes fadeIn {
            0% {
                opacity: 0;
                transform: translateY(20px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }

        body {
            font-family: Arial, sans-serif;
            background-image: url('https://t4.ftcdn.net/jpg/06/94/03/95/360_F_694039559_Da4HGqJ5LfpjIcvWVyyQftkBo80dqcXs.jpg');
            background-size: cover;
            margin: 0;
            padding: 0;
            color: #fff;
            overflow-x: hidden;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.85); /* Slight transparency for the white background */
            border-radius: 10px;
            padding: 40px;
            text-align: center;
            max-width: 600px;
            margin: 100px auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Slight shadow for visibility */
            color: #333; /* Darker text for readability */
            opacity: 0; /* Initial state for fade-in */
            animation: fadeIn 1s ease-out forwards; /* Apply the fade-in animation */
        }

        h1 {
            color: #FFD700; /* Gold color for header */
            margin-bottom: 20px;
            font-size: 2.5em;
        }

        p {
            font-size: 1.2em;
            color: #555; /* Softer color for text */
        }

        .button {
            background-color: #28a745;
            color: white;
            padding: 15px 25px;
            border: none;
            border-radius: 5px;
            font-size: 1.2em;
            cursor: pointer;
            text-decoration: none;
            margin-top: 20px;
            display: inline-block;
            transition: background-color 0.3s;
        }

        .button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <% String userName = request.getParameter("userName"); %>
        <h1>Hey <%= userName != null ? userName : "Guest" %>! Order Confirmed!</h1>
        <p>Your order will reach you very soon.....</p>
        <a href="home.jsp" class="button">Go to Home</a>
    </div>
</body>
</html>
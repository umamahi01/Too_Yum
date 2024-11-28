<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Sign In - YumStop</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" rel="stylesheet">
<style>
    body {
        font-family: 'Poppins', sans-serif;
        background: url('https://images.pexels.com/photos/1640774/pexels-photo-1640774.jpeg') no-repeat center center fixed;
        background-size: cover;
        margin: 0;
        display: flex;
        justify-content: center; /* Center form horizontally */
        align-items: center; /* Center form vertically */
        height: 100vh;
        color: black;
    }

    .form-container {
        background: rgba(240, 248, 255, 0.85); /* Light white with lightish blue tint */
        padding: 40px;
        border-radius: 12px;
        box-shadow: 0 4px 20px rgba(0, 0, 0, 0.5);
        width: 400px;
        animation: fadeIn 1.2s ease;
    }

    h2 {
        text-align: center;
        margin-bottom: 20px;
        font-weight: 600;
        color: black;
    }

    form {
        display: flex;
        flex-direction: column;
    }

    label {
        margin-bottom: 5px;
        font-weight: bold;
        color: black;
    }

    .input-container {
        position: relative;
        margin-bottom: 20px;
    }

    input[type="email"], input[type="password"] {
        width: 100%;
        padding: 14px;
        border: 1px solid #007bff;
        border-radius: 5px;
        font-size: 16px;
        background-color: rgba(255, 255, 255, 0.8);
        transition: border-color 0.3s ease;
    }

    input[type="submit"] {
        background-color: skyblue; /* Sky blue background */
        color: black;
        padding: 12px;
        border: none;
        border-radius: 5px;
        font-size: 18px;
        cursor: pointer;
        transition: background-color 0.3s ease, transform 0.2s ease;
        font-weight: bold;
    }

    input[type="submit"]:hover {
        background-color: deepskyblue; /* Lighter blue on hover */
    }

    .forgot-password a {
        color: black;
        text-decoration: none;
    }

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(20px); }
        to { opacity: 1; transform: translateY(0); }
    }
</style>

</head>
<body>
    <div class="form-container">
        <h2>Welcome Back to HungryHub!</h2>
        <form action="signInUser" method="POST">
            <label for="email">Email:</label>
            <div class="input-container">
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>

            <label for="password">Password:</label>
            <div class="input-container">
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
                <button type="button" class="show-password" onclick="togglePasswordVisibility()">Show</button>
            </div>

            <div class="remember-me">
                <input type="checkbox" id="remember-me" name="remember-me">
                <label for="remember-me" style="margin-left: 8px;">Remember Me</label>
            </div>

            <input type="submit" value="LOGIN">
        </form>
        <div class="forgot-password">
            <p><a href="#">Forgot Password?</a></p>
        </div>
    </div>

    <script>
        function togglePasswordVisibility() {
            const passwordInput = document.getElementById("password");
            const showButton = document.querySelector(".show-password");
            if (passwordInput.type === "password") {
                passwordInput.type = "text";
                showButton.textContent = "Hide";
            } else {
                passwordInput.type = "password";
                showButton.textContent = "Show";
            }
        }
    </script>
</body>
</html>
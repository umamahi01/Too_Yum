<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
<style>
body, html {
    height: 100%;
    margin: 0;
    font-family: 'Poppins', sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    background: url('https://i.pinimg.com/originals/b4/bb/f6/b4bbf6e9f97b9e2a67ace13c75dfdb06.jpg') no-repeat center center fixed;
    background-size: cover;
}

.form-container {
    position: fixed;
    top: 50%;
    right: calc(5% + 2cm); /* Shift form 2cm left */
    transform: translateY(-50%);
    background: rgba(255, 255, 255, 0.2); /* Highly transparent white */
    padding: 20px;
    border-radius: 12px;
    box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.2);
    width: 550px; /* Increased width */
    max-width: 90%;
    border: 1px solid #C62828;
    backdrop-filter: blur(10px);
    opacity: 0; /* Initially hidden */
    transition: opacity 1s ease-in-out; /* Smooth transition effect */
}

/* Gloss effect */
.form-container::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(to top left, rgba(255, 255, 255, 0.4), rgba(255, 255, 255, 0.2));
    border-radius: 12px;
    pointer-events: none;
    z-index: 1;
    opacity: 0.5;
}

h2 {
    text-align: center;
    color: #D50000;
    margin-bottom: 15px;
    font-weight: bold;
    font-size: 2em;
}

table {
    width: 100%;
}

td {
    padding: 8px 0;
    color: black;
    font-weight: bold;
    font-size: 1em;
}

input[type="text"], input[type="email"], input[type="password"], input[type="tel"] {
    width: 100%;
    padding: 10px;
    border: 1px solid rgba(0, 0, 0, 0.3);
    border-radius: 8px;
    font-size: 14px;
    margin-top: 5px;
    background-color: #FFFFFF;
    color: black;
    transition: border-color 0.3s ease;
    font-weight: bold;
}

input:focus {
    border-color: #D50000;
    outline: none;
}

input::placeholder {
    color: rgba(0, 0, 0, 0.5);
    font-weight: 400;
}

input[type="submit"] {
    width: 100%;
    background-color: #D50000;
    color: white;
    padding: 12px;
    border: none;
    border-radius: 8px;
    font-size: 16px;
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.2s ease;
    margin-top: 15px;
}

input[type="submit"]:hover {
    background-color: #B71C1C;
    transform: scale(1.05);
}

.error-message {
    color: #D50000;
    font-weight: bold;
    margin-bottom: 10px;
    display: none;
    text-align: center;
}
</style>
</head>
<body onload="fadeInForm()">
    <div class="form-container" id="formContainer">
        <h2>Create Your Account</h2>
        <div class="error-message" id="error-message"></div>
        <form action="signUpUser" id="addUser" onsubmit="return validateForm()">
            <table>
                <tr>
                    <td>User Name:</td>
                    <td><input type="text" name="userName" id="userName" required placeholder="Enter your username"></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="email" id="email" required placeholder="Enter your email"></td>
                </tr>
                <tr>
                    <td>Phone Number:</td>
                    <td><input type="tel" name="phoneNumber" id="phoneNumber" required placeholder="Enter your phone number"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" id="password" required placeholder="Create a password"></td>
                </tr>
                <tr>
                    <td>Confirm Password:</td>
                    <td><input type="password" name="cpassword" id="confirmPassword" required placeholder="Confirm your password"></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="address" id="address" required placeholder="Enter your address"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Register Me"></td>
                </tr>
            </table>
        </form>
    </div>

    <script>
        // Function to gradually fade in the form
        function fadeInForm() {
            document.getElementById("formContainer").style.opacity = "1";
        }

        function validateForm() {
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("confirmPassword").value;
            const errorMessage = document.getElementById("error-message");

            if (password !== confirmPassword) {
                errorMessage.textContent = "Passwords do not match!";
                errorMessage.style.display = "block";
                return false;
            } else {
                errorMessage.style.display = "none";
            }
            return true;
        }
    </script>
</body>
</html>
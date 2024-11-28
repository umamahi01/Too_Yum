package com.yumstop.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yumstop.dao.UserDAO;
import com.yumstop.daoImpl.UserDAOImpl;
import com.yumstop.model.User;
@WebServlet("/signInUser")
public class SignInUser extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUser(email);
        
        if (user != null && password.equals(user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("restaurantServlet");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("errorMessage", "Incorrect details. Try again or <a href='signUp.jsp'>Sign Up</a>.");
            resp.sendRedirect("index.jsp");
        }
    }
}
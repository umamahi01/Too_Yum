package com.yumstop.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yumstop.dao.UserDAO;
import com.yumstop.daoImpl.UserDAOImpl;
import com.yumstop.model.User;

@WebServlet("/signUpUser")
public class SignUpUser extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName=req.getParameter("userName");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String cpassword=req.getParameter("cpassword");
		String phoneNumber=req.getParameter("phoneNumber");
		String address=req.getParameter("address");
		
		User user=new User(userName, email, phoneNumber, password, address);
		UserDAO userDAO=new UserDAOImpl();
		int status = userDAO.addUser(user);	
		if(status == 1) {
			resp.sendRedirect("signIn.jsp");
		}
		else {
			resp.sendRedirect("failure.jsp");
		}
	}
}
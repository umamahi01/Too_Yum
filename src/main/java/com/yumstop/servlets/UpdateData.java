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

@WebServlet("/updateData")
public class UpdateData extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO userDAO = new UserDAOImpl();
		User user = new User(Integer.parseInt(req.getParameter("userId")),req.getParameter("userName"),req.getParameter("email"),req.getParameter("phoneNumber"),null,req.getParameter("address"));
		int status=userDAO.updateUser(user);
		if(status==0) {
			resp.sendRedirect("failure.jsp");
		}
		else {
			resp.sendRedirect("success.jsp");
		}
	}

}
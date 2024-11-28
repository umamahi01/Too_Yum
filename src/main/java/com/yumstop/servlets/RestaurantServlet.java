package com.yumstop.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yumstop.dao.RestaurantDAO;
import com.yumstop.daoImpl.RestaurantDAOImpl;
import com.yumstop.model.Restaurant;
@WebServlet("/restaurantServlet")
public class RestaurantServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDAO restaurantDAO=new RestaurantDAOImpl();
		ArrayList<Restaurant> restaurantList=restaurantDAO.getAllRestaurants();
		HttpSession session=req.getSession();
		session.setAttribute("restaurantList", restaurantList);
		resp.sendRedirect("home.jsp");
	}
}
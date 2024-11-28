package com.yumstop.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yumstop.dao.MenuDAO;
import com.yumstop.daoImpl.MenuDAOImpl;
import com.yumstop.model.Menu;

@WebServlet("/menuServlet")
public class MenuServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            // Parse restaurantId and restaurantName from request
            int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
            String restaurantName = req.getParameter("restaurantName");
            System.out.println(restaurantId);

            // Fetch the menu list for the restaurant
            MenuDAO menuDAO = new MenuDAOImpl();
            ArrayList<Menu> menuList = menuDAO.getMenuOnRestaurantId(restaurantId);

            // Store menu, restaurantId, and restaurantName in session attributes
            session.setAttribute("menuList", menuList);
            session.setAttribute("restaurantId", restaurantId);
            session.setAttribute("restaurantName", restaurantName);

            // Redirect to the menu.jsp page with parameters
            resp.sendRedirect("menu.jsp?restaurantId=" + restaurantId + "&restaurantName=" + restaurantName);

        } catch (NumberFormatException e) {
            // Handle error for invalid restaurantId format
            resp.getWriter().write("Invalid restaurantId format.");
            e.printStackTrace();
        }
    }
}
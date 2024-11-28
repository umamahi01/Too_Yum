package com.yumstop.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yumstop.dao.MenuDAO;
import com.yumstop.daoImpl.MenuDAOImpl;
import com.yumstop.model.Cart;
import com.yumstop.model.CartItem;
import com.yumstop.model.Menu;

@WebServlet("/addToCartServlet")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemId = request.getParameter("itemId");
        String restaurantId = request.getParameter("restaurantId");
        String restaurantName = request.getParameter("restaurantName");
        String quantity = request.getParameter("quantity");

        // Log received data for debugging
        System.out.println("Received itemId: " + itemId);
        System.out.println("Received restaurantId: " + restaurantId);
        System.out.println("Received restaurantName: " + restaurantName);
        System.out.println("Received quantity: " + quantity);

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart(); 
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addItemToCart(request, cart);
        } else if ("Update".equals(action)) {
            updateCartItem(request, cart);
        } else if ("Remove".equals(action)) {
            removeCartItem(request, cart);
        }

        System.out.println("Cart Contents: " + cart.getItems().values()); // Debugging the cart contents

        session.setAttribute("cart", cart);

        // Redirect to cart.jsp after action
        response.sendRedirect("cart.jsp");
        System.out.println("Control reached addToServlet");
    }

    private void addItemToCart(HttpServletRequest request, Cart cart) {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            MenuDAO menuDAO = new MenuDAOImpl();
            Menu menuItem = menuDAO.getMenu(itemId);

            if (menuItem != null) {
                CartItem cartItem = new CartItem();
                cartItem.setItemId(menuItem.getMenuId());
                cartItem.setRestaurantId(menuItem.getRestaurantId());
                cartItem.setItemName(menuItem.getItemName());
                cartItem.setDescription(menuItem.getDescription());
                cartItem.setPrice(menuItem.getPrice());
                cartItem.setQuantity(quantity);
                cartItem.setTotalPrice(quantity * menuItem.getPrice());

                // Log cartItem data to ensure everything is correct
                System.out.println("Adding item to cart: " + cartItem);

                cart.addItem(cartItem); // Add the item to the cart
            } else {
                System.out.println("Menu item not found for itemId: " + itemId);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Log the error
        }
    }

    private void updateCartItem(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        cart.updateItem(itemId, quantity);
    }

    private void removeCartItem(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        cart.removeItem(itemId);
    }
}
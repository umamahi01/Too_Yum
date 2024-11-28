package com.yumstop.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumstop.dao.MenuDAO;
import com.yumstop.dbutils.DBUtils;
import com.yumstop.model.Menu;

public class MenuDAOImpl implements MenuDAO{
	ArrayList<Menu> menuList = new ArrayList<>();

	private static final String ADD_MENU = "INSERT INTO MENU (restaurantId, itemName, description, quantity, price, isAvailable) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ALL = "SELECT * FROM MENU";
	private static final String SELECT_ON_ID = "SELECT * FROM MENU WHERE menuId=?";
	private static final String UPDATE_ON_ID = "UPDATE MENU SET restaurantId=?, itemName=?, description=?, quantity=?, price=?, isAvailable=? WHERE menuId=?";
	private static final String DELETE_ON_ID = "DELETE FROM MENU WHERE menuId=?";
	private static final String SELECT_ON_RESTAURANT_ID = "SELECT * FROM MENU WHERE restaurantId=?";

	private Connection con;
	private PreparedStatement pstmt;
	private int status;
	private Statement stmt;
	private ResultSet resultSet;
	private Menu menu;

	public MenuDAOImpl() {
		try {
			con = DBUtils.getConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addMenu(Menu menu) {
		try {
			pstmt = con.prepareStatement(ADD_MENU);
			pstmt.setInt(1, menu.getRestaurantId());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setInt(4, menu.getQuantity());
			pstmt.setDouble(5, menu.getPrice());
			pstmt.setBoolean(6, menu.getIsAvailable());
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<Menu> getAllMenus() {
		try {
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(SELECT_ALL);
			menuList = getMenuFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}

	@Override
	public Menu getMenu(int menuId) {
		try {
			pstmt = con.prepareStatement(SELECT_ON_ID);
			pstmt.setInt(1, menuId);
			resultSet = pstmt.executeQuery();
			menuList = getMenuFromResultSet(resultSet);
			if (!menuList.isEmpty()) {
				menu = menuList.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public int updateMenu(Menu menu) {
		try {
			pstmt = con.prepareStatement(UPDATE_ON_ID);
			pstmt.setInt(1, menu.getRestaurantId());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setInt(4, menu.getQuantity());
			pstmt.setDouble(5, menu.getPrice());
			pstmt.setBoolean(6, menu.getIsAvailable());
			pstmt.setInt(7, menu.getMenuId());
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deleteMenu(int menuId) {
		try {
			pstmt = con.prepareStatement(DELETE_ON_ID);
			pstmt.setInt(1, menuId);
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<Menu> getMenuOnRestaurantId(int restaurantId) {
		try {
			pstmt = con.prepareStatement(SELECT_ON_RESTAURANT_ID);
			pstmt.setInt(1, restaurantId);
			resultSet = pstmt.executeQuery();
			menuList = getMenuFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}

	private ArrayList<Menu> getMenuFromResultSet(ResultSet resultSet) {
		ArrayList<Menu> menus = new ArrayList<>();
		try {
			while (resultSet.next()) {
				double price = resultSet.getDouble("price");
				int quantity = resultSet.getInt("quantity");
				double totalPrice = price * quantity;  // Calculate totalPrice based on quantity and price

				menus.add(new Menu(
						resultSet.getInt("menuId"),
						resultSet.getInt("restaurantId"),
						resultSet.getString("itemName"),
						resultSet.getString("description"),
						quantity,
						price,
						totalPrice,  // Set calculated totalPrice
						resultSet.getBoolean("isAvailable"),
						resultSet.getString("imagePath")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menus;
	}
}

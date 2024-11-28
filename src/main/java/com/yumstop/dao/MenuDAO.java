package com.yumstop.dao;
import java.util.ArrayList;
import com.yumstop.model.Menu;

public interface MenuDAO {
	int addMenu(Menu menu);
    ArrayList<Menu> getAllMenus();
    Menu getMenu(int menuId);
    int updateMenu(Menu menu);
    int deleteMenu(int menuId);
    ArrayList<Menu> getMenuOnRestaurantId(int restaurantId);
}

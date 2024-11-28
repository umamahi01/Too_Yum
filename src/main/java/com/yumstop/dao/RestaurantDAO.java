package com.yumstop.dao;

import java.util.ArrayList;

import com.yumstop.model.Restaurant;

public interface RestaurantDAO {
	int addRestaurant(Restaurant restaurant);
    ArrayList<Restaurant> getAllRestaurants();
    Restaurant getRestaurant(int restaurantId);
    int updateRestaurant(Restaurant restaurant);
    int deleteRestaurant(int restaurantId);
}

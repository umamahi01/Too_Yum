package com.yumstop.model;

public class CartItem {
	private int itemId;
	private int menuId;
	private int restaurantId;
	private String itemName;
	private String description;
	private int quantity;
	private double price;
	private double totalPrice;

	public CartItem() {

	}

	public CartItem(int itemId,int menuId, int restaurantId, String itemName, String description, int quantity, double price,double totalPrice) {
		this.itemId=itemId;
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice=price*quantity;
	}
	public CartItem(int menuId, int restaurantId, String itemName, String description, int quantity, double price) {
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice=price*quantity;
	}


	public double getTotalPrice() {
		return price*quantity;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = price*quantity;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}


	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

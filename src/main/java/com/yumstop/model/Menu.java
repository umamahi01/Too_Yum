package com.yumstop.model;

public class Menu {
	private int menuId;
	private int restaurantId;
	private String itemName;
	private String description;
	private int quantity;
	private double price;
	private double totalPrice;
	private boolean isAvailable;
	private String imagePath;
	
	public Menu() {
		super();
	}

	
	
	public Menu(int menuId, int restaurantId, String itemName, String description,int quantity, double price,double totalPrice, boolean isAvailable,String imagePath) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.description = description;
		this.quantity=quantity;
		this.price = price;
		this.totalPrice=totalPrice;
		this.isAvailable = isAvailable;
		this.imagePath=imagePath;
	}
	public Menu(int menuId,String itemName, String description,int quantity, double price,double totalPrice, boolean isAvailable,String imagePath) {
		super();
		this.menuId = menuId;
		this.itemName = itemName;
		this.description = description;
		this.quantity=quantity;
		this.price = price;
		this.totalPrice=totalPrice;
		this.isAvailable = isAvailable;
		this.imagePath=imagePath;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	
	public double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
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

	public void setDescription(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", restaurantId=" + restaurantId + ", itemName=" + itemName + ", description="
				+ description + ", price=" + price + ", isAvailable=" + isAvailable + "]";
	}

	
}

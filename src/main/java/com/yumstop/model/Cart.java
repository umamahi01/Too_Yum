package com.yumstop.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Integer, CartItem> items;

	public Cart() {
		items = new HashMap<>();
	}

	
	public Map<Integer, CartItem> getItems() {
		return items;
	}

	
	public void addItem(CartItem item) {
	    if (items.containsKey(item.getItemId())) {
	        CartItem existingItem = items.get(item.getItemId());
	        existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
	        existingItem.setTotalPrice(existingItem.getQuantity() * existingItem.getPrice());
	    } else {
	        items.put(item.getItemId(), item);
	    }
	    // Debugging log
	    System.out.println("Current items in the cart: " + items.values());
	}


	
	public void updateItem(int itemId, int quantity) {
	    CartItem item = items.get(itemId); // Fetch the item from the map
	    if (item != null) {
	        item.setQuantity(quantity); // Update the quantity
	        System.out.println("Item updated: " + item.getItemName() + " | Quantity: " + item.getQuantity());
	    } else {
	        System.out.println("Item not found in cart.");
	    }
	}

	
	public void removeItem(int itemId) {
		items.remove(itemId);
	}

	// Clear the cart
	public void clear() {
		items.clear();
	}

	// Get an item by its ID
	public CartItem getItemById(int itemId) {
		// Directly fetch the item from the map using the itemId as the key
		return items.get(itemId);
	}
}

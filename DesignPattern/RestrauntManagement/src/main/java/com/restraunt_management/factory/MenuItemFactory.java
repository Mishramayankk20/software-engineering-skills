package com.restraunt_management.factory;

import org.springframework.stereotype.Component;

import com.restraunt_management.model.MenuItem;

@Component
public class MenuItemFactory {

	public MenuItem create(Long id, String name, String category, double price) {

		if (category == null || category.isBlank()) {
			throw new IllegalArgumentException("Category is required");
		}

		return switch (category.toUpperCase()) {

		case "PIZZA" -> new MenuItem(id, name, "PIZZA", price);

		case "BURGER" -> new MenuItem(id, name, "BURGER", price);

		case "BEVERAGE" -> new MenuItem(id, name, "BEVERAGE", price);

		case "DESSERT" -> new MenuItem(id, name, "DESSERT", price);

		default -> throw new IllegalArgumentException("Unsupported menu category: " + category);
		};
	}
}
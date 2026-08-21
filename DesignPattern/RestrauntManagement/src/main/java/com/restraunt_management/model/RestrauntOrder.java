package com.restraunt_management.model;

import java.util.List;
import java.util.ArrayList;

public class RestrauntOrder {

	private Long id;
	private int tableNumber;
	private String customerName;
	private String specialInstructions;
	private List<OrderItem> items;
	private OrderStatus status;

	private RestrauntOrder(Builder builder) {
		this.id = builder.id;
		this.tableNumber = builder.tableNumber;
		this.customerName = builder.customerName;
		this.specialInstructions = builder.specialInstructions;
		this.items = builder.items;
		this.status = OrderStatus.PLACED;
	}

	public static class Builder {

		private Long id;
		private int tableNumber;
		private String customerName;
		private String specialInstructions;
		private List<OrderItem> items = new ArrayList<>();

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder tableNumber(int tableNumber) {
			this.tableNumber = tableNumber;
			return this;
		}

		public Builder customerName(String customerName) {
			this.customerName = customerName;
			return this;
		}

		public Builder specialInstructions(String instructions) {
			this.specialInstructions = instructions;
			return this;
		}

		public Builder items(List<OrderItem> items) {
			this.items = items;
			return this;
		}

		public RestrauntOrder build() {
			if (tableNumber <= 0) {
				throw new IllegalArgumentException("Table number must be greater than 0");
			}

			if (items == null || items.isEmpty()) {
				throw new IllegalArgumentException("Order must contain at least one item");
			}

			return new RestrauntOrder(this);
		}
	}

	public Long getId() {
		return id;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public double getSubtotal() {
		return items.stream().mapToDouble(OrderItem::getTotal).sum();
	}

}

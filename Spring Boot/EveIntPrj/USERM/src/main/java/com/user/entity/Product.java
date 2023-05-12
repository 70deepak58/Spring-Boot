package com.user.entity;



public class Product {

	
	private String name;
	private int  qty;
	private int  price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(String name, int qty, int price) {
		super();
		this.name = name;
		this.qty = qty;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", qty=" + qty + ", price=" + price + "]";
	}
	
	
}

package com.cart.dto;

import java.sql.Timestamp;

public class CartVO {
	private int cartNum;
	private String userId;
	private int num;
	private String pSize;
	private int quantity;
	private int balance;
	private Timestamp orderdate;

	public int getCartNum() {
		return cartNum;
	}

	@Override
	public String toString() {
		return "CartVO [cartNum=" + cartNum + ", userId=" + userId + ", num=" + num + ", pSize=" + pSize + ", quantity="
				+ quantity + ", balance=" + balance + ", orderdate=" + orderdate + "]";
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quentity) {
		this.quantity = quentity;
	}

	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getpSize() {
		return pSize;
	}

	public void setpSize(String pSize) {
		this.pSize = pSize;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

}

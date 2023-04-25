package com.order.dto;

import java.sql.Timestamp;

public class OrderVO { // orders table + order_detail table

	private int orderNumber; // seq
	private int orderDetailNumber; // seq
	private int num; // product number
	private int quantity;
	private String userid;
	private String result;
	private Timestamp indate;
	private int totalprice;

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getOrderDetailNumber() {
		return orderDetailNumber;
	}

	public void setOrderDetailNumber(int orderDetailNumber) {
		this.orderDetailNumber = orderDetailNumber;
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

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Timestamp getIndate() {
		return indate;
	}

	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}

	@Override
	public String toString() {
		return "OrderVO [orderNumber=" + orderNumber + ", orderDetailNumber=" + orderDetailNumber + ", num=" + num
				+ ", quantity=" + quantity + ", userid=" + userid + ", result=" + result + ", indate=" + indate
				+ ", totalprice=" + totalprice + "]";
	}

}

package com.purchased.dto;

import java.sql.Timestamp;

public class PurchasedVO {
	private String userid, bName, pName, imgUrl, pSize, orderNum, recipName, recipPhone, recipZipcode, recipAddr1, recipAddr2, memo, payMethod, status;
	private int price, purchasedPrice, quantity, pNum, discountRate, deliveryFee, pointUsed;
	private Timestamp orderDate;
	public String getUserid() {return userid;}
	public void setUserid(String userid) {this.userid = userid;}
	public String getbName() {return bName;}
	public void setbName(String bName) {this.bName = bName;}
	public String getpName() {return pName;}
	public void setpName(String pName) {this.pName = pName;}
	public String getImgUrl() {return imgUrl;}
	public void setImgUrl(String imgUrl) {this.imgUrl = imgUrl;}
	public String getpSize() {return pSize;}
	public void setpSize(String pSize) {this.pSize = pSize;}
	public String getOrderNum() {return orderNum;}
	public void setOrderNum(String orderNum) {this.orderNum = orderNum;}
	public String getRecipName() {return recipName;}
	public void setRecipName(String recipName) {this.recipName = recipName;}
	public String getRecipPhone() {return recipPhone;}
	public void setRecipPhone(String recipPhone) {this.recipPhone = recipPhone;}
	public String getRecipZipcode() {return recipZipcode;}
	public void setRecipZipcode(String recipZipcode) {this.recipZipcode = recipZipcode;}
	public String getRecipAddr1() {return recipAddr1;}
	public void setRecipAddr1(String recipAddr1) {this.recipAddr1 = recipAddr1;}
	public String getRecipAddr2() {return recipAddr2;}
	public void setRecipAddr2(String recipAddr2) {this.recipAddr2 = recipAddr2;}
	public String getMemo() {return memo;}
	public void setMemo(String memo) {this.memo = memo;}
	public String getPayMethod() {return payMethod;}
	public void setPayMethod(String payMethod) {this.payMethod = payMethod;}
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
	public int getPurchasedPrice() {return purchasedPrice;}
	public void setPurchasedPrice(int purchasedPrice) {this.purchasedPrice = purchasedPrice;}
	public int getQuantity() {return quantity;}
	public void setQuantity(int quantity) {this.quantity = quantity;}
	public int getpNum() {return pNum;}
	public void setpNum(int pNum) {this.pNum = pNum;}
	public int getDiscountRate() {return discountRate;}
	public void setDiscountRate(int discountRate) {this.discountRate = discountRate;}
	public int getDeliveryFee() {return deliveryFee;}
	public void setDeliveryFee(int deliveryFee) {this.deliveryFee = deliveryFee;}
	public Timestamp getOrderDate() {return orderDate;}
	public void setOrderDate(Timestamp orderDate) {this.orderDate = orderDate;}
	public int getPointUsed() {return pointUsed;}
	public void setPointUsed(int pointUsed) {this.pointUsed = pointUsed;}
	
	@Override
	public String toString() {
		return "PurchasedVO [userid=" + userid + ", bName=" + bName + ", pName=" + pName + ", imgUrl=" + imgUrl
				+ ", pSize=" + pSize + ", orderNum=" + orderNum + ", recipName=" + recipName + ", recipPhone="
				+ recipPhone + ", recipZipcode=" + recipZipcode + ", recipAddr1=" + recipAddr1 + ", recipAddr2="
				+ recipAddr2 + ", memo=" + memo + ", payMethod=" + payMethod + ", status=" + status + ", price=" + price
				+ ", purchasedPrice=" + purchasedPrice + ", quantity=" + quantity + ", pNum=" + pNum + ", discountRate="
				+ discountRate + ", deliveryFee=" + deliveryFee + ", orderDate=" + orderDate + "]";
	}
}
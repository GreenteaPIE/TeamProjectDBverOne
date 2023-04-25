package com.auction.dto;

import java.sql.Timestamp;

public class AuctionVO {
	private String userId, bName, pName, pSize, imgUrl;
	private int price, endPrice, startPrice, onOff, num;
	private Timestamp endTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpSize() {
		return pSize;
	}
	public void setpSize(String pSize) {
		this.pSize = pSize;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(int endPrice) {
		this.endPrice = endPrice;
	}
	public int getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}
	public int getOnOff() {
		return onOff;
	}
	public void setOnOff(int onOff) {
		this.onOff = onOff;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "AuctionVO [userId=" + userId + ", bName=" + bName + ", pName=" + pName + ", pSize=" + pSize
				+ ", imgUrl=" + imgUrl + ", price=" + price + ", endPrice=" + endPrice + ", startPrice=" + startPrice
				+ ", onOff=" + onOff + ", num=" + num + ", endTime=" + endTime + "]";
	}
	
}

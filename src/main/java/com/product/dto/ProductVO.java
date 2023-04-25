package com.product.dto;

import java.sql.Timestamp;

public class ProductVO {
	private int num;
	private int pGender;
	private String bName;
	private int kind;
	private String pName;
	private String imgUrl;
	private String pSize;
	private int balance;
	private int price;
	private int purchasedNum;
	private String explain;
	private Timestamp writedate;
	private int readcount;
	

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getpGender() {
		return pGender;
	}

	public void setpGender(int pGender) {
		this.pGender = pGender;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPurchasedNum() {
		return purchasedNum;
	}

	public void setPurchasedNum(int purchasedNum) {
		this.purchasedNum = purchasedNum;
	}

	public Timestamp getWritedate() {
		return writedate;
	}

	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "ProductVO [num=" + num + ", pGender=" + pGender + ", bName=" + bName + ", kind=" + kind + ", pName="
				+ pName + ", imgUrl=" + imgUrl + ", pSize=" + pSize + ", balance=" + balance + ", price=" + price
				+ ", purchasedNum=" + purchasedNum + ", explain=" + explain + ", writedate=" + writedate
				+ ", readcount=" + readcount + "]";
	}

}

package com.brand.dto;

public class BrandVO {
	private String bName, imgUrl;

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "BrandVO [bName=" + bName + ", imgUrl=" + imgUrl + "]";
	}
	
}

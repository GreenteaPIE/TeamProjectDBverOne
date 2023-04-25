package com.freeboard.dto;

import java.sql.Timestamp;

public class FreeBoardVO {
	private String userid,title, content, imgurl;
	private int readcount, num;
	private Timestamp writedate;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Timestamp getWritedate() {
		return writedate;
	}
	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}
	@Override
	public String toString() {
		return "FreeBoardVO [userid=" + userid + ", title=" + title + ", content=" + content + ", imgurl=" + imgurl
				+ ", readcount=" + readcount + ", num=" + num + ", writedate=" + writedate + "]";
	}

	}



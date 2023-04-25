package com.shopuser.dto;

import java.sql.Timestamp;

public class ShopUserVO {
   
   private String userid, pass, name, email, address1, address2, phone, zipcode;
   private int gender, point, grade;
   private Timestamp enter;
   public String getUserid() {
      return userid;
   }
   public void setUserid(String userid) {
      this.userid = userid;
   }
   public String getPass() {
      return pass;
   }
   public void setPass(String pass) {
      this.pass = pass;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getAddress1() {
      return address1;
   }
   public void setAddress1(String address1) {
      this.address1 = address1;
   }
   public String getAddress2() {
      return address2;
   }
   public void setAddress2(String address2) {
      this.address2 = address2;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   public int getGrade() {
      return grade;
   }
   public void setGrade(int grade) {
      this.grade = grade;
   }
   public String getZipcode() {
      return zipcode;
   }
   public void setZipcode(String zipcode) {
      this.zipcode = zipcode;
   }
   public int getGender() {
      return gender;
   }
   public void setGender(int gender) {
      this.gender = gender;
   }
   public int getPoint() {
      return point;
   }
   public void setPoint(int point) {
      this.point = point;
   }
   public Timestamp getEnter() {
      return enter;
   }
   public void setEnter(Timestamp enter) {
      this.enter = enter;
   }
   @Override
public String toString() {
	return "ShopUserVO [userid=" + userid + ", pass=" + pass + ", name=" + name + ", email=" + email + ", address1="
			+ address1 + ", address2=" + address2 + ", phone=" + phone + ", zipcode=" + zipcode + ", gender=" + gender
			+ ", point=" + point + ", grade=" + grade + ", enter=" + enter + "]";
}
   

}
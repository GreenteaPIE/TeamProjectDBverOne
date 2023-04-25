package com.db.controller.action.admin;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.controller.action.Action;
import com.db.controller.action.shopuser.ShopUserLoginAction;
import com.shopuser.dao.ShopUserDAO;
import com.shopuser.dto.ShopUserVO;

import util.PasswordEncryption;

public class UpdateUserAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String userid = request.getParameter("userid");
		String name = request.getParameter("name");
		String pass = PasswordEncryption.sha256(request.getParameter("pass"));
		String email = request.getParameter("email");
		String zipcode = request.getParameter("zipcode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");

		ShopUserVO suVo = new ShopUserVO();
		suVo.setUserid(userid);
		suVo.setName(name);
		suVo.setPass(pass);
		suVo.setEmail(email);
		suVo.setZipcode(zipcode);
		suVo.setAddress1(address1);
		suVo.setAddress2(address2);
		suVo.setPhone(phone);
		suVo.setGender(Integer.parseInt(gender));

		ShopUserDAO sDao = ShopUserDAO.getInstance();
		sDao.myPageUpdateUser(suVo);

		new ShopUserLoginAction().execute(request, response);
	}
}

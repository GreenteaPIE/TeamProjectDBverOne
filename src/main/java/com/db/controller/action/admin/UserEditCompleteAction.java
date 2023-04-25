package com.db.controller.action.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.shopuser.dao.ShopUserDAO;
import com.shopuser.dto.ShopUserVO;

import util.PasswordEncryption;

public class UserEditCompleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String pass = PasswordEncryption.sha256(request.getParameter("pass"));
		String email = request.getParameter("email");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String zipcode = request.getParameter("zipcode");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String grade = request.getParameter("grade");
		String point = request.getParameter("point");
		
		ShopUserVO sVo = new ShopUserVO();
		
		sVo.setUserid(userid);
		sVo.setAddress1(address1);
		sVo.setAddress2(address2);
		sVo.setEmail(email);
		sVo.setGender(Integer.parseInt(gender));
		sVo.setGrade(Integer.parseInt(grade));
		sVo.setName(name);
		sVo.setPass(pass);
		sVo.setPhone(phone);
		sVo.setPoint(Integer.parseInt(point));
		sVo.setZipcode(zipcode);
		
		ShopUserDAO sDao = ShopUserDAO.getInstance();
		sDao.updateUser(sVo);
		RequestDispatcher rd = request.getRequestDispatcher("admin/userEditComplete.jsp");
		rd.forward(request, response);
	}
	
}

package com.db.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.shopuser.dao.ShopUserDAO;

public class UserManagementDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		ShopUserDAO sDao = ShopUserDAO.getInstance();
		sDao.deleteUser(userid);
		
		new UserManagementPageAction().execute(request, response);
	}
	
}

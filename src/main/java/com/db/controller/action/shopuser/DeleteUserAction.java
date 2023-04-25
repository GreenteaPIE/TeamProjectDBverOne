package com.db.controller.action.shopuser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.shopuser.dao.ShopUserDAO;

public class DeleteUserAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShopUserDAO suDao = ShopUserDAO.getInstance();
		suDao.ShopUserexit(request.getParameter("userid"));
		new ShopUserLogout().execute(request, response);
	}
}

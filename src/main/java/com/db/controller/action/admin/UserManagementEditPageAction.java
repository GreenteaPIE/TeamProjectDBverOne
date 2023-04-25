package com.db.controller.action.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.shopuser.dao.ShopUserDAO;
import com.shopuser.dto.ShopUserVO;

public class UserManagementEditPageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/userManagementEdit.jsp";
		String userid = request.getParameter("userid");
		ShopUserDAO sDao = ShopUserDAO.getInstance();
		ShopUserVO sVo = sDao.getShopUser(userid);
		request.setAttribute("user", sVo);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}

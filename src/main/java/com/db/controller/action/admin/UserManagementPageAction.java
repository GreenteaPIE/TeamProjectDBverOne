package com.db.controller.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.shopuser.dao.ShopUserDAO;
import com.shopuser.dto.ShopUserVO;

public class UserManagementPageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/userManagementPage.jsp";
		ShopUserDAO sDao = ShopUserDAO.getInstance();
		List<ShopUserVO> list = sDao.getAllMember();
		request.setAttribute("user", list);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}

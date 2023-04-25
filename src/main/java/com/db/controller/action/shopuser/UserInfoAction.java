package com.db.controller.action.shopuser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.controller.action.Action;
import com.shopuser.dao.ShopUserDAO;
import com.shopuser.dto.ShopUserVO;

public class UserInfoAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/shopuser/userinfo.jsp";
		String userid = request.getParameter("userid");
		ShopUserDAO suDao = ShopUserDAO.getInstance();
		ShopUserVO suVo = suDao.getShopUser(userid);
		request.setAttribute("loginUser", suVo);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}
}

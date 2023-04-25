package com.db.controller.action.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.categorieslist.dao.CateGoriesListDAO;
import com.categorieslist.dto.CateGoriesListVO;
import com.db.controller.action.Action;
import com.mysql.cj.Session;
import com.shopuser.dto.ShopUserVO;

public class HotDeal implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url = "/categories/hotDealList.jsp";
		CateGoriesListDAO cgDAO  = CateGoriesListDAO.getInstance();
		ArrayList<CateGoriesListVO> CateGoriesList = cgDAO.hotDealList();
		request.setAttribute("CateGoriesList", CateGoriesList);
		
		HttpSession session = request.getSession();
		ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");
		request.setAttribute("loginUser", loginUser);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
	}

}

package com.db.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.categorieslist.dao.CateGoriesListDAO;
import com.categorieslist.dto.CateGoriesListVO;
import com.db.controller.action.Action;

public class AdminAuctionFormAction1 implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String bname = request.getParameter("bname");
		String url = "/admin/adminAuctionForm1.jsp";
        request.setAttribute("bname", bname);
		CateGoriesListDAO cgDAO  = CateGoriesListDAO.getInstance();
		ArrayList<CateGoriesListVO> CateGoriesList = cgDAO.BcollecionProducts(bname);
		request.setAttribute("CateGoriesList", CateGoriesList);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}

package com.db.controller.action.search;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.categorieslist.dao.CateGoriesListDAO;
import com.categorieslist.dto.CateGoriesListVO;
import com.db.controller.action.Action;

public class searchProduct implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String pname = request.getParameter("pname");
		String url = null;
		request.setAttribute("pname", pname);
		CateGoriesListDAO cgDAO = CateGoriesListDAO.getInstance();

		ArrayList<CateGoriesListVO> CateGoriesList = cgDAO.searchProducts(pname);

		if (CateGoriesList.isEmpty()) {
			url = "/categories/searchNotFound.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} else {
			url = "/categories/searchProductList.jsp";
			request.setAttribute("CateGoriesList", CateGoriesList);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}

}

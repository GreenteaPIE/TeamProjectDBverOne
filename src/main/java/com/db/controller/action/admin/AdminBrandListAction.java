package com.db.controller.action.admin;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brand.dao.BrandDAO;
import com.brand.dto.BrandVO;
import com.db.controller.action.Action;

public class AdminBrandListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = "admin/adminBrandList.jsp";
		BrandDAO bDao = BrandDAO.getInstance();
		List<BrandVO> bList = bDao.getAllBrandList();
		request.setAttribute("brand", bList);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}

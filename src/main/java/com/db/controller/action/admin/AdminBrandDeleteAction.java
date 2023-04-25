package com.db.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brand.dao.BrandDAO;
import com.db.controller.action.Action;

public class AdminBrandDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String bname = request.getParameter("brandname");
		BrandDAO bDao = BrandDAO.getInstance();
		bDao.deleteBrand(bname);
		
		new AdminBrandListAction().execute(request, response);
	}

}

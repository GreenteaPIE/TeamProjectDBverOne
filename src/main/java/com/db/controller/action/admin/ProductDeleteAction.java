package com.db.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.product.dao.ProductDAO;

public class ProductDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int num =Integer.parseInt(request.getParameter("num"));
		ProductDAO pDao = ProductDAO.getInstance();
		pDao.deleteProduct(num);
		
		new ProductListAction().execute(request, response);
		
	}

}

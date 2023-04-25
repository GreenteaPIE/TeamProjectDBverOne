package com.db.controller.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.product.dao.ProductDAO;
import com.product.dto.ProductVO;

public class ProductListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = "/product/productList1.jsp";
		ProductDAO bDao = ProductDAO.getInstance();
		//List<ProductVO> pList = bDao.selectAllProducts();
		List<ProductVO> pList = bDao.selectAllProductsNotDuplicated();
		request.setAttribute("ProductList", pList);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}

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

public class ProductViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		String url = "/product/productView.jsp";
		//int num = Integer.parseInt(request.getParameter("num"));
		ProductDAO pDao = ProductDAO.getInstance();
		String pName = request.getParameter("pName");
		ProductVO pVo = pDao.getProductByName(pName);
		List<ProductVO> sList = pDao.getProductSizeList(pName);
		
		//ProductVO pVo = pDao.getProduct(num);
		request.setAttribute("Product", pVo);
		request.setAttribute("pSize", sList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}

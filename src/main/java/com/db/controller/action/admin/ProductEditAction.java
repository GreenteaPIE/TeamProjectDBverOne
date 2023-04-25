package com.db.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brand.dao.BrandDAO;
import com.brand.dto.BrandVO;
import com.db.controller.action.Action;
import com.product.dao.ProductDAO;
import com.product.dto.ProductVO;

public class ProductEditAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String url = "product/productEdit.jsp";
		BrandDAO bDao = BrandDAO.getInstance();
		List<BrandVO> bList = bDao.getAllBrandList();
		//int num = Integer.parseInt(request.getParameter("num"));
		String pName = request.getParameter("pName");
		String pSize = request.getParameter("pSize");
		ProductDAO pDao = ProductDAO.getInstance();
		ProductVO pVo = pDao.getProductByNameSize(pName,pSize);
		List<ProductVO> sList = new ArrayList<>();
		//ProductVO pVo = pDao.getProduct(num);
		request.setAttribute("pSize", pSize);
		request.setAttribute("Product", pVo);
		request.setAttribute("brand", bList);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}

package com.db.controller.action.categorieslist;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.categorieslist.dao.CateGoriesListDAO;
import com.categorieslist.dto.CateGoriesListVO;
import com.db.controller.action.Action;
import com.product.dao.ProductDAO;
import com.product.dto.ProductVO;

public class brandProductDetail implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/categories/brandProductDetail.jsp";
		String pName = request.getParameter("pName");
		CateGoriesListDAO cgDao = CateGoriesListDAO.getInstance();
		HttpSession session = request.getSession();
		ProductDAO pDao = ProductDAO.getInstance();
		List<ProductVO> sList = pDao.getProductSizeList(pName);
		ProductVO pVo = pDao.getProductByName(pName);
		request.setAttribute("pSize", sList);
		request.setAttribute("bllist", pVo);
		String bname = request.getParameter("bname");
		request.setAttribute("bname", bname);
		
	
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}

package com.db.controller.action.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.dao.CartDAO;
import com.cart.dto.CartVO;
import com.categorieslist.dao.CateGoriesListDAO;
import com.categorieslist.dto.CateGoriesListVO;
import com.db.controller.action.Action;
import com.product.dao.ProductDAO;
import com.product.dto.ProductVO;
import com.shopuser.dto.ShopUserVO;

public class HotDealBuyPage implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");
		request.setAttribute("loginUser", loginUser);
		String url = "/product/hotdealcheckout.jsp";
		
		String hotdealprice = request.getParameter("dp");
		request.setAttribute("hotdealprice", hotdealprice);
		
		String num = request.getParameter("num");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		ProductDAO pDao = ProductDAO.getInstance();
		
		CateGoriesListDAO cgDao = CateGoriesListDAO.getInstance();
		CateGoriesListVO cgVo = cgDao.selectOneCGByNum(num);

		request.setAttribute("ProductList", cgVo);
	    dispatcher.forward(request, response);
		
	}

}

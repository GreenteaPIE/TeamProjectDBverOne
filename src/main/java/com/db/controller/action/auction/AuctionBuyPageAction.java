package com.db.controller.action.auction;

import java.io.IOException;

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
import com.shopuser.dto.ShopUserVO;

public class AuctionBuyPageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");
		request.setAttribute("loginUser", loginUser);
		String url = "/product/hotdealcheckout.jsp";
		String pName = request.getParameter("pName");
		String pSize = request.getParameter("pSize");

		String hotdealprice = request.getParameter("dp");

		request.setAttribute("hotdealprice", hotdealprice);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		ProductDAO pDao = ProductDAO.getInstance();
		ProductVO pVo = null;
		if (pSize.length() == 0) {
			pVo = pDao.getProductByName(pName);
		} else {
			pVo = pDao.getProductByNameSize(pName, pSize);
		}

		System.out.println("pVo:" + pVo);
		request.setAttribute("ProductList", pVo);
		dispatcher.forward(request, response);

	}

}

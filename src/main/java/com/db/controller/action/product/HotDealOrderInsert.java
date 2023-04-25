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
import com.order.dao.OrderDAO;
import com.product.dao.ProductDAO;
import com.shopuser.dto.ShopUserVO;

public class HotDealOrderInsert implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = null;
		int total = Integer.parseInt(((request.getParameter("total"))));
		String num = request.getParameter("hnum");
		HttpSession session = request.getSession();
		ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			url = "DBServlet?command=shopuser_login_form";
		} else {
		
			OrderDAO orderDAO = OrderDAO.getInstance();
			request.setAttribute("total", total);
			request.setAttribute("hnum", num);
			
			CateGoriesListDAO cgDAO = CateGoriesListDAO.getInstance();
			CateGoriesListVO dealproduct = cgDAO.selectOneCGByNum(num);
					
			int maxOrderNumber = orderDAO.hotDealInsertOrder(dealproduct, loginUser.getUserid(), total);
			url = "DBServlet?command=hotdeal_order_list&orderNumber=" + maxOrderNumber;

		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}

package com.db.controller.action.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.dao.CartDAO;
import com.cart.dto.CartVO;
import com.db.controller.action.Action;
import com.order.dao.OrderDAO;
import com.shopuser.dto.ShopUserVO;

public class OrderInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String url = "DBServlet?command=order_list";
		int total = Integer.parseInt(((request.getParameter("total"))));
		HttpSession session = request.getSession();
		ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			url = "DBServlet?command=shopuser_login_form";
		} else {
			CartDAO cartDAO = CartDAO.getinstance();
			ArrayList<CartVO> listCart = cartDAO.listCart(loginUser.getUserid());
			request.setAttribute("total", total);
			OrderDAO orderDAO = OrderDAO.getInstance();
			
			System.out.println(total);

			int maxOrderNumber = orderDAO.insertOrder(listCart, loginUser.getUserid(), total);
			url = "DBServlet?command=order_list&orderNumber=" + maxOrderNumber;

		}
		response.sendRedirect(url);

	}

}

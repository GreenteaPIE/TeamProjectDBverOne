package com.db.controller.action.purchased;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.dao.CartDAO;
import com.cart.dto.CartVO;
import com.db.controller.action.Action;
import com.order.dao.OrderDAO;
import com.order.dto.OrderVO;
import com.product.dao.ProductDAO;
import com.product.dto.ProductVO;
import com.shopuser.dto.ShopUserVO;

public class PurchasedDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "purchased/purchasedDetail.jsp";

		HttpSession session = request.getSession();
		ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");
		

		if (loginUser == null) {
			url = "DBServlet?command=shopuser_login_form";
		} else {

			CartDAO cDao = CartDAO.getinstance();
			ArrayList<CartVO> cartList = cDao.listCart2(loginUser.getUserid());
			request.setAttribute("cartList", cartList);

			ProductDAO pDao = ProductDAO.getInstance();
			ArrayList<ProductVO> ProductList = pDao.selectAllProducts();
			request.setAttribute("ProductList", ProductList);

			String orderNumber = request.getParameter("orderNumber").trim();

			OrderDAO orderDAO = OrderDAO.getInstance();
			List<OrderVO> orderView = orderDAO.selectOrderView(orderNumber);
			request.setAttribute("orderView", orderView);

		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}

package com.db.controller.action.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.dao.CartDAO;
import com.db.controller.action.Action;
import com.order.dao.OrderDAO;
import com.order.dto.OrderVO;
import com.product.dao.ProductDAO;
import com.product.dto.ProductVO;
import com.shopuser.dto.ShopUserVO;

public class OrderListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String url = "product/orderList.jsp";
		OrderVO orderVO = new OrderVO(); // OrderVO 객체 생성
		HttpSession session = request.getSession();
		session.setAttribute("orderVO", orderVO); // orderVO 객체를 세션에 저장
		ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");
		session.getAttribute("orderVO");
		// orderVO 객체에 필요한 값들을 설정

		if (loginUser == null) {
			url = "DBServlet?command=shopuser_login_form";
		} else {

			ProductDAO pDao = ProductDAO.getInstance();
			ArrayList<ProductVO> ProductList = pDao.selectAllProducts();
			request.setAttribute("ProductList", ProductList);

			int orderNumber = Integer.parseInt(request.getParameter("orderNumber"));
			OrderDAO orderDAO = OrderDAO.getInstance();
			ArrayList<OrderVO> orderList = orderDAO.listOrderById(loginUser.getUserid(), "1", orderNumber);
			request.setAttribute("orderList", orderList);
			

		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}

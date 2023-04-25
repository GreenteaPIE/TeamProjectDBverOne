package com.db.controller.action.purchased;

import java.io.IOException;
import java.util.ArrayList;

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

public class PurchasedListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "purchased/purchasedList.jsp";

		HttpSession session = request.getSession();
		ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			url = "DBServlet?command=shopuser_login_form";
		} else {

			OrderDAO orderDAO = OrderDAO.getInstance();
			ArrayList<Integer> orderNumberList = orderDAO.selectSeqOrdering(loginUser.getUserid());
			ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();

			for (int orderNumber : orderNumberList) {
				ArrayList<OrderVO> orderListing = orderDAO.listOrderById(loginUser.getUserid(), "1", orderNumber);

				OrderVO orderVO = orderListing.get(0);
//				orderVO.setPname(orderVO.getPname() + "외" + orderListing.size() + "건");
				orderList.add(orderVO);
			}

			request.setAttribute("orderList", orderList);

			ProductDAO pDao = ProductDAO.getInstance();
			ArrayList<ProductVO> ProductList = pDao.selectAllProducts();
			request.setAttribute("ProductList", ProductList);

			CartDAO cDao = CartDAO.getinstance();
			ArrayList<CartVO> cartList = cDao.listCart(loginUser.getUserid());
			request.setAttribute("cartList", cartList);

		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}

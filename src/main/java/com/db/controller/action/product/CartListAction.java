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
import com.db.controller.action.Action;
import com.product.dao.ProductDAO;
import com.product.dto.ProductVO;
import com.shopuser.dto.ShopUserVO;

public class CartListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");

		String url = "cart/cart.jsp";

		HttpSession session = request.getSession();
		ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");

		if (loginUser == null) { // 로그인 유저가 없을 때 로그인 페이지로
			url = "DBServlet?command=shopuser_login_form";

		} else {
			CartDAO cDao = CartDAO.getinstance();
			ArrayList<CartVO> cartList = cDao.listCart(loginUser.getUserid());
			ProductDAO pDao = ProductDAO.getInstance();
			ArrayList<ProductVO> ProductList = pDao.selectAllProducts();
			request.setAttribute("ProductList", ProductList);
			request.setAttribute("cartList", cartList);
			System.out.println("loginuser아이디 : " + loginUser.getUserid());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}

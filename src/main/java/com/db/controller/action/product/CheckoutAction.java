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

public class CheckoutAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");
		request.setAttribute("loginUser", loginUser);
		String url = "/product/checkout.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		CartDAO cDao = CartDAO.getinstance();
		ArrayList<CartVO> cartList = cDao.listCart(loginUser.getUserid());
		ProductDAO pDao = ProductDAO.getInstance();
		ArrayList<ProductVO> ProductList = pDao.selectAllProducts();
		request.setAttribute("ProductList", ProductList);
		request.setAttribute("cartList", cartList);
	    dispatcher.forward(request, response);
	}

}

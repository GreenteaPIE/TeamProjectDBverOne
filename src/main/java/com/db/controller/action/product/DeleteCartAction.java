package com.db.controller.action.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.dao.CartDAO;
import com.db.controller.action.Action;

public class DeleteCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//System.out.println("cartNum");
		
		request.setCharacterEncoding("utf-8");

		String url = "DBServlet?command=cart";

		String[] cartNumArr = request.getParameterValues("cartNum");
		for (String cartNum : cartNumArr) {
			System.out.println(cartNum);
			CartDAO cartDAO = CartDAO.getinstance();
			cartDAO.deleteCart(Integer.parseInt(cartNum));
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}

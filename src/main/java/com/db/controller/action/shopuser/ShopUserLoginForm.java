package com.db.controller.action.shopuser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.controller.action.Action;

public class ShopUserLoginForm implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "shopuser/shopuserlogin.jsp";
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") != null) {
			url = "main.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);  //로그인정보가 있다면 main으로 가고 아니면 login으로 보냄.
		rd.forward(request, response);
		
	}

}

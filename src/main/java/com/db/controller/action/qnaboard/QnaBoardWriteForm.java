package com.db.controller.action.qnaboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.controller.action.Action;

public class QnaBoardWriteForm implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/qnaboard/qnaBoardWrite.jsp";
		String userid = request.getParameter("userid");
		request.setAttribute("userid", userid);
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}

package com.db.controller.action.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.freeboard.dao.FreeBoardDAO;
import com.freeboard.dto.FreeBoardVO;

public class FreeBoardDetailPageAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "freeBoardUpdateForm.jsp";
		String num = request.getParameter("num");
		FreeBoardDAO fbDao = FreeBoardDAO.getInstance();
		FreeBoardVO fbVo = fbDao.getBoardInfo(num);
		request.setAttribute("board", fbVo);
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}

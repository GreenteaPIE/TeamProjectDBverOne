package com.db.controller.action.freeboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.controller.action.Action;
import com.freeboard.dao.FreeBoardDAO;
import com.freeboard.dto.FreeBoardVO;



public class FreeBoardUpdateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/freeboard/freeBoardUpdate.jsp";
		String num = request.getParameter("num");
		FreeBoardDAO fbDao = FreeBoardDAO.getInstance();
		fbDao.updateReadCount(num);
		FreeBoardVO fbVo = fbDao.selectOneFreeBoardByNum(num);
		request.setAttribute("board", fbVo);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}

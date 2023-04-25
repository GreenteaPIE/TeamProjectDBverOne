package com.db.controller.action.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.freeboard.dao.FreeBoardDAO;



public class FreeBoardDelete implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FreeBoardDAO fbDao = FreeBoardDAO.getInstance();
		fbDao.deleteFreeBoard(Integer.parseInt(request.getParameter("num")));
		new FreeBoardListAction().execute(request, response);
		
	}

}

package com.db.controller.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.freeboard.dao.FreeBoardDAO;
import com.freeboard.dto.FreeBoardVO;

public class BoardManagement implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/boardManageMentPage.jsp";
		FreeBoardDAO fbDao = FreeBoardDAO.getInstance();
		List<FreeBoardVO> list = fbDao.getAllBoardList();
		request.setAttribute("freeboard", list);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}

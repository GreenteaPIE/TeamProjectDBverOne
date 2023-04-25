package com.db.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.freeboard.dao.FreeBoardDAO;
import com.qnaboard.dao.QnaBoardDAO;

public class qnaBoardManagementDelete implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		QnaBoardDAO qbDao = QnaBoardDAO.getInstance();
		qbDao.deleteBoardByNum(num);
		
		new qnaBoardManagementPageAction().execute(request, response);
		
	}

}

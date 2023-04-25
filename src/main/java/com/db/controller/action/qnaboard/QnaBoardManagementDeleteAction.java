package com.db.controller.action.qnaboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.qnaboard.dao.QnaBoardDAO;

public class QnaBoardManagementDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		QnaBoardDAO qbDao = QnaBoardDAO.getInstance();
		qbDao.deleteBoardByNum(num);
		
		new QnaBoardManagementPageAction().execute(request, response);
	}

}

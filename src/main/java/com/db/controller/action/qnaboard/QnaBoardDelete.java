package com.db.controller.action.qnaboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.qnaboard.dao.QnaBoardDAO;




public class QnaBoardDelete implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnaBoardDAO qbDao = QnaBoardDAO.getInstance();
		qbDao.deleteQnaBoard(Integer.parseInt(request.getParameter("num")));
		new QnaBoardListAction().execute(request, response);
		
	}

}

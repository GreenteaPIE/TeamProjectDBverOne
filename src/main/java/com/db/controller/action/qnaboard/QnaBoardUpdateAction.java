package com.db.controller.action.qnaboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.controller.action.Action;
import com.qnaboard.dao.QnaBoardDAO;
import com.qnaboard.dto.QnaBoardVO;



public class QnaBoardUpdateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/qnaboard/qnaBoardUpdate.jsp";
		String num = request.getParameter("num");
		QnaBoardDAO qbDao = QnaBoardDAO.getInstance();
		qbDao.updateReadCount(num);
		QnaBoardVO qbVo = qbDao.selectOneQnaBoardByNum(num);
		request.setAttribute("board", qbVo);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}

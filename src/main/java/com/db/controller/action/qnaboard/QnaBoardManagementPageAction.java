package com.db.controller.action.qnaboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.qnaboard.dao.QnaBoardDAO;
import com.qnaboard.dto.QnaBoardVO;

public class QnaBoardManagementPageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/qnaBoardManagementPage.jsp";
		QnaBoardDAO qbDao = QnaBoardDAO.getInstance();
		List<QnaBoardVO> list = qbDao.getAllBoardList();
		request.setAttribute("qnaboard", list);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}

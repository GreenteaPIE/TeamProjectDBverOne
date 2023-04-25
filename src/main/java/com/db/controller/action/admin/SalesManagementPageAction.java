package com.db.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.order.dao.OrderDAO;
import com.order.dto.OrderVO;

public class SalesManagementPageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/salesManagementPage.jsp";
        OrderDAO oDao = OrderDAO.getInstance();
        
		ArrayList<OrderVO> selectAllOders = oDao.selectAllOders();
		request.setAttribute("orders", selectAllOders);
		
		ArrayList<OrderVO> selectAllOderDetail = oDao.selectAllOderDetail();
		request.setAttribute("orderdetail", selectAllOderDetail);
		

	
		
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}

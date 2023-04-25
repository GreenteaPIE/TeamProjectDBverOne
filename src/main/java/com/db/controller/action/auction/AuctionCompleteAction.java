package com.db.controller.action.auction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auction.dao.AuctionDAO;
import com.auction.dto.AuctionVO;
import com.db.controller.action.Action;
import com.product.dao.ProductDAO;
import com.product.dto.ProductVO;

public class AuctionCompleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//String url = "auction/auctionDetail.jsp";
		String url = "DBServlet?command=auction_detail";
		int num = Integer.parseInt(request.getParameter("num"));

		String originPname = request.getParameter("originProduct");
		ProductDAO pDao = ProductDAO.getInstance();
		ProductVO pVo = pDao.getProductByName(originPname);
		AuctionDAO auDao = AuctionDAO.getInstance();
		AuctionVO auVo = auDao.getAuctionDetail(num);
		url="DBServlet?command=auction_detail&pName="+originPname+"&num="+num;
		request.setAttribute("auction", auVo);
		request.setAttribute("originProduct", pVo);
		
		auDao.auctionComplete(num);
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}

package com.db.controller.action.auction;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auction.dao.AuctionDAO;
import com.auction.dto.AuctionVO;
import com.db.controller.action.Action;
import com.product.dao.ProductDAO;
import com.product.dto.ProductVO;

public class AuctionDetailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String url = "auction/auctionDetail.jsp";
		String pName = request.getParameter("pName");
		int num = Integer.parseInt(request.getParameter("num"));
		ProductDAO pDao = ProductDAO.getInstance();
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		AuctionDAO auDao = AuctionDAO.getInstance();
		AuctionVO auVo = auDao.getAuctionDetail(num);
		if(auVo.getEndTime().before(new Date())) {
			auDao.auctionComplete(num);
		}
		auVo = auDao.getAuctionDetail(num);
		ProductVO pVo = pDao.getProductByName(pName);
		request.setAttribute("originProduct", pVo);
		request.setAttribute("auction", auVo);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}

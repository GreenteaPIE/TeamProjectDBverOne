package com.db.controller.action.auction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auction.dao.AuctionDAO;
import com.auction.dto.AuctionVO;
import com.db.controller.action.Action;
import com.shopuser.dto.ShopUserVO;

public class AuctionViewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String url = "auction/auctionView.jsp";
		HttpSession session = request.getSession();
		ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");
		session.setAttribute("loginUser", loginUser);
		AuctionDAO auDao = AuctionDAO.getInstance();
		List<AuctionVO> auList = auDao.getAuctionList();
		request.setAttribute("AuctionList", auList);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}

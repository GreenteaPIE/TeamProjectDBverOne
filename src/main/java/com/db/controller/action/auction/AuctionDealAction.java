package com.db.controller.action.auction;

import java.io.IOException;

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
import com.shopuser.dto.ShopUserVO;

public class AuctionDealAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		// String url = "auction/auctionDetail.jsp";
		String url = "DBServlet?command=auction_detail";
		ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");
		session.setAttribute("loginUser", loginUser);
		if (loginUser == null) {
			url = "DBServlet?command=shopuser_login_form";
			request.getRequestDispatcher(url).forward(request, response);
			return; // 추가된 부분
		} else {
			int num = Integer.parseInt(request.getParameter("num"));
			int price = Integer.parseInt(request.getParameter("price"));
			String originPname = request.getParameter("originProduct");
			ProductDAO pDao = ProductDAO.getInstance();
			ProductVO pVo = pDao.getProductByName(originPname);
			AuctionDAO auDao = AuctionDAO.getInstance();
			AuctionVO auVo = auDao.getAuctionDetail(num);
			url="DBServlet?command=auction_detail&pName="+originPname+"&num="+num;
			auDao.dealAuction(num, price, loginUser.getUserid());
			request.setAttribute("auction", auVo);
			request.setAttribute("originProduct", pVo);
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

}

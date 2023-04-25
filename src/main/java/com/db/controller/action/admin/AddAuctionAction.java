package com.db.controller.action.admin;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auction.dao.AuctionDAO;
import com.auction.dto.AuctionVO;
import com.db.controller.action.Action;
import com.shopuser.dto.ShopUserVO;

public class AddAuctionAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "index.jsp";
		HttpSession session = request.getSession();
		ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");
		session.setAttribute("loginUser", loginUser);
		String pName = request.getParameter("pName");
		String bName = request.getParameter("bName");
		//String datetimeString = request.getParameter("endTime"); // "2022-04-19 10:30:00"
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		//LocalDateTime datetime = LocalDateTime.parse(datetimeString, formatter);
		String dateString = request.getParameter("endTime");;
		String imgUrl = request.getParameter("imgUrl");
		LocalDateTime localDateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		Timestamp endTime = Timestamp.valueOf(localDateTime);
		int onOff = Integer.parseInt(request.getParameter("onOff"));
		int startPrice = Integer.parseInt(request.getParameter("startPrice"));
		int price = startPrice;
		
		System.out.println("AddAuctionAction에서 loginUser 확인 : " + loginUser);

		if (loginUser == null) {
			url = "DBServlet?command=shopuser_login_form";
			request.getRequestDispatcher(url).forward(request, response);
			return; // 추가된 부분
		} else {
			AuctionVO aVo = new AuctionVO();
			aVo.setUserId(loginUser.getUserid());
			System.out.println("AddCartAction---------------");
			System.out.println("loginUser : " + loginUser.getUserid());
			aVo.setbName(bName);
			System.out.println("bname : " + bName);
			aVo.setpSize(request.getParameter("pSize"));
			System.out.println("pSize : " + request.getParameter("pSize"));
			aVo.setpName(pName);
			System.out.println("pName : " + pName);
			aVo.setStartPrice(startPrice);
			System.out.println("startPrice : " + startPrice);
			aVo.setOnOff(onOff);
			System.out.println("onOff : " + onOff);
			aVo.setEndTime(endTime);
			System.out.println("endTime : " + endTime);
			aVo.setPrice(price);
			System.out.println("price : " + price);
			aVo.setImgUrl(imgUrl);
			AuctionDAO aDao = AuctionDAO.getInstance();

			System.out.println("AddCartAction에서 찍어본 aVo : " + aVo);
			aDao.insertAuction(aVo);

			response.sendRedirect(url);
		}
	}

}

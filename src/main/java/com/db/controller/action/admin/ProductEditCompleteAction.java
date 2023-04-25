package com.db.controller.action.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.product.dao.ProductDAO;
import com.product.dto.ProductVO;

public class ProductEditCompleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String url = "DBServlet?command=product_list";
		String pName = request.getParameter("pName");
		int kind = Integer.parseInt(request.getParameter("kind"));
		String bName = request.getParameter("bName");
		int pGender = Integer.parseInt(request.getParameter("pGender"));
		int price = Integer.parseInt(request.getParameter("price"));
		String pSize = request.getParameter("pSize");
		int balance = Integer.parseInt(request.getParameter("balance"));
		String explain = request.getParameter("explain");
		int num =Integer.parseInt( request.getParameter("num"));
		String imgUrl;
		if(request.getParameter("imgUrl") == null) {
			imgUrl = request.getParameter("imgUrlOrigin");
		} else {
		 imgUrl= request.getParameter("imgUrl");
		}
		ProductVO pVo = new ProductVO();
		
		ProductDAO pDao = ProductDAO.getInstance();
		
		pVo.setpName(pName);
		pVo.setKind(kind);
		pVo.setbName(bName);
		pVo.setpGender(pGender);
		pVo.setPrice(price);
		pVo.setpSize(pSize);
		pVo.setBalance(balance);
		pVo.setExplain(explain);
		pVo.setImgUrl(imgUrl);
		pVo.setNum(num);
		
		pDao.productEdit(pVo);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
}

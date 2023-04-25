package com.db.controller.action.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.dao.CartDAO;
import com.cart.dto.CartVO;
import com.db.controller.action.Action;
import com.product.dao.ProductDAO;
import com.product.dto.ProductVO;
import com.shopuser.dto.ShopUserVO;

public class AddCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "DBServlet?command=cart";
	      HttpSession session = request.getSession();
	      ShopUserVO loginUser = (ShopUserVO) session.getAttribute("loginUser");
	      session.setAttribute("loginUser", loginUser);
	      ProductDAO pDao = ProductDAO.getInstance();
	      ProductVO pVo = new ProductVO();
	      String pSize = request.getParameter("pSize");
	      System.out.println("pSize:"+pSize);
	      String pName = request.getParameter("pName");
	      System.out.println("pName: "+pName);
	      if(pSize.equals("null")) {
	         pVo = pDao.getProductByName(pName);
	      } else {
	         pVo = pDao.getProductByNameSize(pName, pSize);
	      }
	      
	      System.out.println("pVo:" + pVo);
	      System.out.println("AddCartAction에서 loginUser 확인 : " + loginUser);

	      if (loginUser == null) {
	         url = "DBServlet?command=shopuser_login_form";
	         request.getRequestDispatcher(url).forward(request, response);
	         return; // 추가된 부분
	      } else {
	         CartVO cartVO = new CartVO();
	         cartVO.setUserId(loginUser.getUserid());
	         System.out.println("AddCartAction---------------");
	         System.out.println("loginUser : " + loginUser.getUserid());
	         cartVO.setNum(pVo.getNum());
	         System.out.println("num : " + pVo.getNum());
	         cartVO.setpSize(request.getParameter("pSize"));
	         System.out.println("pSize : " + request.getParameter("pSize"));
	         cartVO.setQuantity(Integer.parseInt(request.getParameter("purchasedNum")));
	         System.out.println("Quantity : " + request.getParameter("purchasedNum"));

	         CartDAO cartDAO = CartDAO.getinstance();

	         System.out.println("AddCartAction에서 찍어본 cartVO : " + cartVO);
	         cartDAO.insertCart(cartVO);

	         response.sendRedirect(url);
	      }
	}

}

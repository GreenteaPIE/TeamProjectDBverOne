package com.db.controller.action.shopuser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.controller.action.Action;
import com.shopuser.dao.ShopUserDAO;
import com.shopuser.dto.ShopUserVO;
import util.PasswordEncryption;


public class JoinAction implements Action {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
    String url = "main.jsp"; 
    
    HttpSession session = request.getSession();
    
    ShopUserVO userVO = new ShopUserVO();
    
    userVO.setUserid(request.getParameter("userid"));
    userVO.setPass(PasswordEncryption.sha256(request.getParameter("pass")));
    userVO.setName(request.getParameter("name"));
    userVO.setEmail(request.getParameter("email"));
    userVO.setZipcode(request.getParameter("zipcode"));
    userVO.setAddress1(request.getParameter("address1"));
    userVO.setAddress2(request.getParameter("address2")); 
    userVO.setGender(Integer.parseInt(request.getParameter("gender")));
    userVO.setPhone(request.getParameter("phone"));    
      
    session.setAttribute("id", request.getParameter("id"));    
    
    ShopUserDAO sud = ShopUserDAO.getInstance();
    
    sud.insertUser(userVO);

    
    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
    dispatcher.forward(request, response);
  }
}

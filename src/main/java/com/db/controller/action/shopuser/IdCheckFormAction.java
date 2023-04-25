package com.db.controller.action.shopuser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;
import com.shopuser.dao.ShopUserDAO;
import com.shopuser.dto.ShopUserVO;

public class IdCheckFormAction implements Action {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
    String url = "/shopuser/idcheck.jsp";  
   
  
    
    
    String id = request.getParameter("id").trim();
   
    
    ShopUserDAO uDo=ShopUserDAO.getInstance();
    int message = uDo.confirmID(id);
        
    request.setAttribute("message", message);
    request.setAttribute("id", id);
    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
    dispatcher.forward(request, response);
  }
}

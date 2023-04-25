package com.db.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.shopuser.dao.ShopUserDAO;
import com.shopuser.dto.ShopUserVO;

public class IndexAction implements Action {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {    
    String url = "/index.jsp";
    
    ShopUserDAO suDao=ShopUserDAO.getInstance();
   
    
    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
    dispatcher.forward(request, response);
  }
}

package com.db.controller.action.shopuser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.controller.action.Action;


public class JoinFormAction implements Action {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {  
	  request.setCharacterEncoding("UTF-8");
    String url = "/shopuser/userJoinForm.jsp";  
    
    RequestDispatcher dispatcher=request.getRequestDispatcher(url);
    dispatcher.forward(request, response);
  }
}

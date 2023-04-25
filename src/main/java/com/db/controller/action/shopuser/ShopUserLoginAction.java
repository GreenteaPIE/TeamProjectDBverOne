package com.db.controller.action.shopuser;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.db.controller.action.Action;
import com.shopuser.dao.ShopUserDAO;
import com.shopuser.dto.ShopUserVO;

public class ShopUserLoginAction implements Action {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String url = "/shopuser/shopuserlogin.jsp";
    String userid = request.getParameter("userid");
    String plainPass = request.getParameter("pass");
    
    // 입력받은 비밀번호를 sha256으로 암호화
    String encryptedPass = "";
    try {
      encryptedPass = PasswordEncryption.sha256(plainPass);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    
    ShopUserDAO suDao = ShopUserDAO.getInstance();
    int result = suDao.shopUserCheck(userid, encryptedPass);
    if (result == 1) {
      ShopUserVO suVo = suDao.getShopUser(userid);
      HttpSession session = request.getSession();
      session.setAttribute("userid", userid);
      session.setAttribute("loginUser", suVo);
      request.setAttribute("message", "회원 인증에 성공 하셨습니다");
      url = "main.jsp";
    } else if (result == 0) {
      request.setAttribute("message", "비밀 번호가 맞지 않습니다");
    } else if (result == -1) {
      request.setAttribute("message", "존재하지 않는 아이디 입니다");
    }
    RequestDispatcher rd = request.getRequestDispatcher(url);
    rd.forward(request, response);
  }

}

// PasswordEncryption 클래스 추가
class PasswordEncryption {

  public static String sha256(String str) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(str.getBytes());
    byte[] byteData = md.digest();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < byteData.length; i++) {
      sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();
  }

}
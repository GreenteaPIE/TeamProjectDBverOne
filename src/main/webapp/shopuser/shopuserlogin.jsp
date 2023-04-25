+<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>shop user login</title>
<script type="text/javascript" src="script/shopuser.js"></script>
</head>
<body>
<%@ include file="../header.jsp"%>
<hr>

   <form name="frm" method="post" action="DBServlet" >
      <input type="hidden" name="command" value="shopuser_login_form">
      <div class="form-group input-group fg-x700">
         <div class="col-lg-4"></div>
         <div class="col-lg-4">
            <div class="jumbotron" style="padding-top: 20px;">
            
            <h3 style= "text-align: center;">로그인</h3>
            <div class="form-group">
               <input type="text" class= "form-control" placeholder="아이디" name="userid" value="${userid}" maxlength="20">
            </div>
            <div class="form-group">
               <input type="password" class= "form-control" placeholder="비밀번호" name="pass" maxlength="20">
            </div>
            <input type="submit" class="btn btn-primary form-control" value="로그인"  onclick="return loginCheck()">
            <input type="reset" class="btn btn-primary form-control" value="취소" >
            <input type="button" class="btn btn-primary form-control" value="회원가입" onclick="location.href='DBServlet?command=user_join_form'"> 
            <hr>${message}
         </div>   
         
      </div>
      <div class="col-lg-4"></div>
      </div>
   </form>
   <hr>
   <%@ include file="../footer.jsp"%>
</body>
</html>
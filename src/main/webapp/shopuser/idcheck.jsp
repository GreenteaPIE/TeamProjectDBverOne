<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 체크</title>
<link href="css/style.css" rel="stylesheet">

<script type="text/javascript">
function idok(){
     opener.formm.userid.value= "${id}"; 
     opener.formm.reid.value= "${id}";
     self.close();
   }
</script>
</head>
<body>
<div class="container">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <form method="post" name="formm" action="DBServlet?command=id_check_form">
        <div class="form-group">
          <label for="id">User ID</label>
          <input type="text" class="form-control" name="id" id="id" value="">
        </div>
        <button type="submit" class="btn btn-primary">검색</button>
        <div style="margin-top: 10px">
          <c:if test="${message == 1}">
            <script type="text/javascript">
              opener.document.formm.userid.value="";
            </script>
            ${id}는 이미 사용중인 아이디입니다.
          </c:if>
          <c:if test="${message==-1}">
            ${id}는 사용 가능한 ID입니다.
            <button type="button" class="btn btn-primary" onclick="idok()">사용</button>
          </c:if>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
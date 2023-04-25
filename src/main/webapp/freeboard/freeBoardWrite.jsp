<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>자유게시판 게시물 작성</title>

</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript" src="script/freeboard.js"></script>
	 <div class="container">
      
     <form action="DBServlet" name="frm" method="post" enctype="multipart/form-data">
      		<div class="row">
      			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
      			<thead>
      				<tr>
      				 <th colspan="2" style= "background-color: #eeeeee; text-align: center;">자유 게시글 등록</th>
      				
      			
      				</tr>
      			</thead>
      			<tbody>
      			<tr>
      					<td>작성자 : <input type="hidden" class="form-control"  name="userid" value="${userid }">${userid }</td>
      				</tr>
      				<tr>
      					<td><input type="text" class="form-control" placeholder="글 제목" name="title" maxlength="50"></td>
      				</tr>	
      				<tr>
      					<td><textarea class="form-control" placeholder="글 내용" name="content" maxlength="2048" style="height: 350px;"></textarea></td>
      					
      				</tr>
      				<tr>
      					<td>첨부사진<input type="file" class="form-control" placeholder="사 진" name="imgurl"></td>
      				</tr>	
      			</tbody>
      			
      		</table>
      		</div>
      		<input type="button" class="btn btn-primary pull-right" value="등록" onclick="if(boardCheck()) go_fbw();">&nbsp;&nbsp;
			<input type="reset" class="btn btn-primary pull-right" value="다시 작성">&nbsp;&nbsp;
			<input type="button" class="btn btn-primary pull-right" value="목록" onclick="location.href='DBServlet?command=free_board_list'">&nbsp;&nbsp;
      	</form>
      		</div>
    <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>

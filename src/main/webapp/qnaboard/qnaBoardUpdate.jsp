<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qna 게시판 게시물 수정</title>

<script type="text/javascript" src="script/qnaboard.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
 <div class="container">
      
     <form action="DBServlet" name="frm" method="post" enctype="multipart/form-data">
     <input type="hidden" name="num" value="${board.num}">
      		<div class="row">
      			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
      			<thead>
      				<tr>
      				 <th colspan="2" style= "background-color: #eeeeee; text-align: center;">Q&A 게시글 수정</th>
      				
      			
      				</tr>
      			</thead>
      			<tbody>
      			<tr>
      					<td>작성자 : <input type="hidden" class="form-control"  name="userid" value="${board.userid }">${board.userid }</td>
      				</tr>
      				<tr>
      					<td><input type="text" class="form-control" name="title" value="${board.title }"></td>
      				</tr>	
      				<tr>
      					<td><textarea class="form-control" name="content"  maxlength="2048" style="height: 350px;">${board.content }</textarea></td>
      					
      				</tr>
      				<tr>
      					<td>첨부사진<c:choose>
							<c:when test="${empty board.imgurl}">

							</c:when>
							<c:otherwise>
									<img src="images/${board.imgurl}" style="max-width: 80%; height: auto;">
							</c:otherwise>
						</c:choose></td>
      				</tr>
      				<tr>
      					<td><input type="file" class="form-control" name="imgurl"><br> (주의사항 : 이미지를 변경하고자 할때만 선택하시오)</td>
      				</tr>	
      			</tbody>
      			
      		</table>
      		</div>
      		<input type="button" class="btn btn-primary pull-right" value="등록" onclick="if(boardCheck()) go_fbu();">&nbsp;&nbsp;
			<input type="reset" class="btn btn-primary pull-right" value="다시 작성">&nbsp;&nbsp;
			<input type="button" class="btn btn-primary pull-right" value="목록" onclick="location.href='DBServlet?command=qna_board_list'">&nbsp;&nbsp;
      	</form>
      		</div>
      	 
	
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>

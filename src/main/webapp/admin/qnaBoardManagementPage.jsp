<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/shopuser.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<hr>

	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
						<th style="background-color: #eeeeee; text-align: center;">조회</th>
						<td style="width: 70px; border: none;"></td>
					</tr>
				</thead>
				<tbody>

					<tr>
						<c:forEach var="qb" items="${qnaboard }">
							<tr class="record"></tr>
							<td>${qb.num }</td>
							<td><a href="DBServlet?command=qna_board_view&num=${qb.num }">${qb.title }</a></td>
							<td>${qb.userid }</td>
							<td><fmt:formatDate value="${qb.writedate }" /></td>
							<td>${qb.readcount }</td>
							<td class="hiddenbutton"><input type="button" value="삭제" onclick="location.href='DBServlet?command=qna_board_management_delete&num=${qb.num }'"></td>
						</c:forEach>

					</tr>


				</tbody>

			</table>
		</div>
	</div>
<jsp:include page="../footer.jsp"></jsp:include>


</body>
</html>
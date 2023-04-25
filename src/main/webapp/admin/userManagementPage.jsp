<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 관리 페이지</title>
<style>
  td {
    width: 100px;
    white-space: nowrap; /* 줄바꿈 방지 */
    overflow: hidden; /* 넘치는 부분 감추기 */
    text-overflow: ellipsis; /* 생략 부호(...)로 대체 */
  }
</style>
<script type="text/javascript" src="script/shopuser.js"></script>
</head>

<body>
<jsp:include page="../header.jsp"></jsp:include>
<hr>
	<div id="wrap" align="center">
		<h1>회원 목록</h1>
		<table class="list">
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>성별</th>
				<th>이메일</th>
				<th>주소</th>
				<th>상세 주소</th>
				<th>우편번호</th>
				<th>전화번호</th>
				<th>등급</th>
				<th>포인트</th>
				<th>가입일자</th>
				<td style="width: 70px; border: none;"></td>
			</tr>
			<c:forEach var="user" items="${user }">
				<tr class="button">
					<td>${user.userid }</td>
					<td>${user.pass }</td>
					<td>${user.name }</td>
					<c:choose>
						<c:when test="${user.gender==1}">
							<td>남자</td>
						</c:when>
						<c:when test="${user.gender==2}">
							<td>여자</td>
						</c:when>
					</c:choose>
					<td>${user.email }</td>
					<td>${user.address1 }</td>
					<td>${user.address2 }</td>
					<td>${user.zipcode }</td>
					<td>${user.phone }</td>
					<c:choose>
						<c:when test="${user.grade == 0 }">
							<td>브론즈</td>
						</c:when>
						<c:when test="${user.grade == 1 }">
							<td>관리자</td>
						</c:when>
						<c:when test="${user.grade == 2 }">
							<td>골드</td>
						</c:when>
						<c:when test="${user.grade == 3 }">
							<td>플래티넘</td>
						</c:when>
						<c:when test="${user.grade == 4 }">
							<td>VIP</td>
						</c:when>
						<c:when test="${user.grade == 5 }">
							<td>VVIP</td>
						</c:when>
						<c:otherwise>
							<td>관리자</td>
						</c:otherwise>
					</c:choose>
					<td>${user.point }</td>
					<td><fmt:formatDate value="${user.enter }"/></td>
					<td class="hiddenbutton">
						<input type="button" class="btn-primary px-3" value="수정"
							onclick="open_win('DBServlet?command=user_management_edit&userid=${user.userid }')"
						>
						<input type="button" class="btn-primary px-3" value="삭제"
							onclick="location.href='DBServlet?command=user_management_delete&userid=${user.userid }'">
					</td>
					
				</tr>
			</c:forEach>
		</table>
		<br>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
						
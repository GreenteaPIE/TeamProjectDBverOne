<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/userManagement.css">
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
	<div id="wrap" align="center">
		<h1>상품 리스트</h1>

		<table>

			<c:if test="${loginUser.grade == 1}">
				<tr>
					<td colspan="5" style="border: white; text-align: right"><input type="button" class="btn-primary px-3" value="상품글쓰기" onclick="location.href='DBServlet?command=product_write_form'"></td>
				</tr>
			</c:if>

			<tr>
				<th>브랜드</th>
				<th>카테고리</th>
				<th>이름</th>
				<th>가격</th>
			</tr>

			<c:forEach var="ProductList" items="${ProductList }">
				<tr>
					<td>${ProductList.bName }</td>
					<td><c:choose>
							<c:when test='${ProductList.kind =="1" }'>상의</c:when>
							<c:when test='${ProductList.kind =="2" }'>하의</c:when>
							<c:when test='${ProductList.kind =="3" }'>잡화</c:when>
						</c:choose></td>
					<td><a href="DBServlet?command=product_View&pName=${ProductList.pName }">${ProductList.pName }</a></td>
					<td>${ProductList.price }원</td>
				</tr>
			</c:forEach>

		</table>
	</div>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
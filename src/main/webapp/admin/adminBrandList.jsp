<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>브랜드 리스트 관리</title>
<script type="text/javascript" src="script/admin.js"></script>
<style type="text/css">
img{
	width: 400px;
}
</style>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
	<div id="wrap" align="center">
	<h1>브랜드 리스트 관리</h1>
		<ul style="list-style: none;">
		<li>
			<input type="button" value="브랜드 등록" onclick="location.href='DBServlet?command=admin_brand_write_form'">
		</li>
			<c:forEach var="brand" items="${brand }">
				<li>
					<img alt="${brand.bName }image" src="images/${brand.imgUrl }">
				</li>
				<input type="button" value="DELETE" onclick="deleteBrand('${brand.bName}');">
			</c:forEach>
		</ul>
		<input type="button" value="뒤로가기" onclick="location.href='DBServlet?command=product_management'">
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
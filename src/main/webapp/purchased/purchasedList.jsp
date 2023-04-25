<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<!-- Page Header Start -->
	<div class="container bg-secondary mb-3" style="max-width: 800px;">
		<div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
			<h1 class="font-weight-semi-bold text-uppercase mb-3">Purchased List</h1>
			<div class="d-inline-flex">
				<p class="m-0">진행 중인 주문내역</p>
			</div>
		</div>
	</div>
	<!-- Page Header End -->


	<div class="container bg-secondary mb-3" style="max-width: 900px;">
		<form name="frm" action="DBServlet">
		<input type="hidden" value="user_purchased_detail" name="command">
			<table class="table table-bordered text-center mb-0">
				<thead class="bg-secondary text-dark">
					<tr>
						<th>Order Number</th>
						<!-- 					<th>Products</th> -->
						<!-- 					<th>Total Price</th> -->
						<th>Order Date</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody class="align-middle">
					<c:forEach var="orderVO" items="${orderList }">
						<c:forEach var="ProductList" items="${ProductList }">
							<c:if test="${orderVO.num == ProductList.num}">
								<tr>
									<td class="align-middle"><a href="DBServlet?command=user_purchased_detail&orderNumber=${orderVO.orderNumber}">${orderVO.orderNumber }</a></td>
									<%-- 								<td class="align-middle">${ProductList.pName}</td> --%>
									<%-- 								<td class="align-middle"><fmt:formatNumber value="${ProductList.price*orderVO.quantity }" type="currency" /></td> --%>
									<td class="align-middle"><fmt:formatDate value="${orderVO.indate }" type="date" /></td>
									<td class="align-middle"><c:choose>
											<c:when test='${orderVO.result  =="1" }'>주문 확인 중</c:when>
											<c:when test='${orderVO.result  =="2" }'>처리 진행 중</c:when>
										</c:choose></td>
								</tr>
							</c:if>
						</c:forEach>
					</c:forEach>
			</table>
		</form>

		<div class="card-footer border-secondary bg-transparent" align="center">
			<button class="btn btn-block btn-primary my-3 py-3" style="width: 350px;" onclick="location.href='index.jsp'">Continue shopping</button>
		</div>
	</div>


	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
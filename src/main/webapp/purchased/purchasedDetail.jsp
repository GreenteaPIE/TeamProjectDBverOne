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
				<p class="m-0">주문내역 상세 확인</p>
			</div>
		</div>
	</div>
	<!-- Page Header End -->


	<div class="container bg-secondary mb-3" style="max-width: 800px;">
		<div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">

			<table class="table table-bordered text-center mb-0">
				<thead class="bg-secondary text-dark">
					<tr>
						<th>주문하시는 분</th>
						<td>${loginUser.name }</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${loginUser.phone }</td>
					</tr>
					<tr>
						<th>배송지</th>
						<td>${loginUser.zipcode}&nbsp;,&nbsp;${loginUser.address1 }&nbsp;&nbsp;${loginUser.address2 }</td>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div class="container bg-secondary mb-3" style="max-width: 1000px;">
		<table class="table table-bordered text-center mb-0">
			<thead class="bg-secondary text-dark">
				<tr>
					<th>No.</th>
					<th>Products</th>
					<th>size</th>
					<th>quantity</th>
					<th>Price</th>
					<th>Status</th>
					<th>Order Date</th>
				</tr>
			</thead>
			<tbody class="align-middle">

				<c:forEach var="orderView" items="${orderView}">
					<c:forEach var="cart" items="${cartList }">
						<c:forEach var="ProductList" items="${ProductList }">
							<c:if test="${cart.num eq orderView.num }">
								<c:if test="${ProductList.num eq cart.num }">
									<tr>
										<td class="align-middle">${ProductList.num}</td>
										<td class="align-middle"><a href="DBServlet?command=brand_Product_Detail&num=${ProductList.num }">${ProductList.pName}</a></td>
										<td class="align-middle"><c:choose>
												<c:when test="${cart.pSize=='s' }">S</c:when>
												<c:when test="${cart.pSize=='m' }">M</c:when>
												<c:when test="${cart.pSize=='l' }">L</c:when>
											</c:choose></td>
										<td class="align-middle">${orderView.quantity }</td>
										<td class="align-middle"><fmt:setLocale value="ko_KR" /> <fmt:formatNumber type="currency" value="${orderView.quantity * ProductList.price }" currencySymbol="₩" /></td>
										<td class="align-middle"><c:choose>
												<c:when test='${orderView.result  =="1" }'>주문 확인 중</c:when>
												<c:when test='${orderView.result  =="2" }'>처리 진행 중</c:when>
											</c:choose></td>
										<td class="align-middle">${orderView.indate }</td>
									</tr>
								</c:if>
							</c:if>
						</c:forEach>
					</c:forEach>
				</c:forEach>


				<c:set var="totalPrice" value="0" />
				<c:forEach var="orderVO" items="${orderView}">
					<c:forEach var="product" items="${ProductList}">
						<c:if test="${orderVO.num == product.num}">
							<c:set var="totalPrice" value="${totalPrice + (product.price * orderVO.quantity)}" />
						</c:if>
					</c:forEach>
				</c:forEach>

				<tr>
					<th colspan="4">Total Price</th>
					<td colspan="3"><fmt:formatNumber value="${totalPrice}" type="currency" /></td>
				<tr>
		</table>

		<div class="card-footer border-secondary bg-transparent" align="center">
			<button class="btn btn-block btn-primary my-3 py-3" style="width: 350px;" onclick="location.href='DBServlet?command=user_purchased_list'">Purchased List</button>
			<button class="btn btn-block btn-primary my-3 py-3" style="width: 350px;" onclick="location.href='index.jsp'">Continue shopping</button>
		</div>
	</div>



	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.order-info {
	margin-bottom: 20px;
}

.clear {
	clear: both;
}
</style>
</head>

<body>
	<jsp:include page="../header.jsp"></jsp:include>
	

	<div style="padding-top: 50px; padding-left:20px; padding-right:20px; align-self: center;" >
		<div class="center">
			<c:set var="totalPrice" value="0" />

			<div class="order-info">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">주문번호</th>
						<th style="background-color: #eeeeee; text-align: center;">주문자</th>
						<th style="background-color: #eeeeee; text-align: center;">주문일자</th>
						<th style="background-color: #eeeeee; text-align: center;">주문총액</th>
					</tr>
					<c:forEach var="orders" items="${orders}">
						<tr class="record">

							<th><a>${orders.orderNumber}</a></th>
							<th>${orders.userid}</th>
							<th>${orders.indate}</th>
							<th><c:forEach var="detail" items="${orderdetail}" varStatus="status">
									<c:if test="${orders.orderNumber == detail.orderNumber}">
										<fmt:formatNumber value="${detail.totalprice}" pattern="₩#,##0" />
										<c:set var="totalPrice" value="${totalPrice + detail.totalprice}" />
									</c:if>
								</c:forEach></th>
						</tr>
					</c:forEach>
				</table>

			</div>

			<div class="order-info">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<tr align="right" class="record">
						<th style="background-color: #eeeeee; text-align: center;">판매총액</th>
						<th><fmt:formatNumber value="${totalPrice}" pattern="₩#,##0" /></th>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="clear"></div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>

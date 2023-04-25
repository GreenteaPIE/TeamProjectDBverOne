<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
	/**
	 * @param val 가격정보 (Type. Number)
	 */
	function _fmtNumberKor(val) {
		var numKor = new Array("", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구",
				"십"); // 숫자 문자
		var danKor = new Array("", "십", "백", "천", "", "십", "백", "천", "", "십",
				"백", "천", "", "십", "백", "천"); // 만위 문자열
		var result = "";

		if (val && !isNaN(val)) {
			// CASE: 금액이 공란/NULL/문자가 포함된 경우가 아닌 경우에만 처리

			for (var i = 0; i < val.length; i++) {
				var str = "";
				var num = numKor[val.charAt(val.length - (i + 1))];
				if (num != "")
					str += num + danKor[i]; // 숫자가 0인 경우 텍스트를 표현하지 않음
				switch (i) {
				case 4:
					str += "만";
					break; // 4자리인 경우 '만'을 붙여줌 ex) 10000 -> 일만
				case 8:
					str += "억";
					break; // 8자리인 경우 '억'을 붙여줌 ex) 100000000 -> 일억
				case 12:
					str += "조";
					break; // 12자리인 경우 '조'를 붙여줌 ex) 1000000000000 -> 일조
				}

				result = str + result;
			}

			// Step. 불필요 단위 제거
			if (result.indexOf("억만") > 0)
				result = result.replace("억만", "억");
			if (result.indexOf("조만") > 0)
				result = result.replace("조만", "조");
			if (result.indexOf("조억") > 0)
				result = result.replace("조억", "조");

			result = result + "원";
		}

		return result;
	}
</script>


</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<!-- Page Header Start -->
	<div class="container bg-secondary mb-3" style="max-width: 800px;">
		<div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
			<h1 class="font-weight-semi-bold text-uppercase mb-3">Order List</h1>
			<div class="d-inline-flex">
				<p class="m-0">주문해주셔서 감사합니다.</p>
			</div>
		</div>
	</div>
	<!-- Page Header End -->



	<div class="container bg-secondary mb-3" style="max-width: 800px;">
		<table class="table table-bordered text-center mb-0">
			<thead class="bg-secondary text-dark">
				<tr>
					<th>Products</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Order Date</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody class="align-middle">
				<c:forEach var="orderVO" items="${orderList }">
					<c:forEach var="ProductList" items="${ProductList }">
						<c:if test="${orderVO.num == ProductList.num}">
							<tr>
								<td class="align-middle">${ProductList.pName }</td>
								<td class="align-middle">${orderVO.quantity }</td>
								<td class="align-middle"><fmt:formatNumber value="${total}" pattern="₩###,###" /></td>
								<td class="align-middle"><fmt:formatDate value="${orderVO.indate }" type="date" /></td>
								<td class="align-middle"><c:choose>
										<c:when test='${orderVO.result  =="1" }'>주문 확인 중</c:when>
										<c:when test='${orderVO.result  =="2" }'>처리 진행 중</c:when>
									</c:choose></td>
							</tr>
						</c:if>
					</c:forEach>
				</c:forEach>

				<c:set var="totalPrice" value="0" />
				<c:forEach var="orderVO" items="${orderList}">
					<c:forEach var="product" items="${ProductList}">
						<c:if test="${orderVO.num == product.num}">
							<c:set var="totalPrice" value="${totalPrice + (product.price * orderVO.quantity)}" />
						</c:if>
					</c:forEach>
				</c:forEach>
				<tr>
					<th>Total Price</th>
					<td colspan="4"><fmt:formatNumber value="${total}" pattern="₩###,###" /></td>
				</tr>
			</tbody>
		</table>


		<div class="card-footer border-secondary bg-transparent" align="center">
			<button class="btn btn-block btn-primary my-3 py-3" style="width: 350px;" onclick="location.href='index.jsp'">Continue shopping</button>
		</div>
	</div>


	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link href="css/shopping.css" rel="stylesheet">
<script type="text/javascript" src="script/cartList.js"></script>
</head>
<body>

	<div id="wrap" align="center">

		<h1>장바구니</h1>
		<form method="post">

			<table id="cartTable">

				<tr>
					<td colspan="5">배송지 : ${loginUser.zipcode} ${loginUser.address1 } ${loginUser.address2 } <input type="button" value="변경" onclick="location.href=''"></td>
				</tr>

				<c:choose>
					<c:when test="${cartList.size()==0}">
						<tr>
							<td>
								<h3>장바구니가 비었습니다.</h3>
							</td>
						</tr>
					</c:when>

					<c:otherwise>

						<tr>
							<td align="left" colspan="3"><input type="checkbox" value="">전체 선택</td>
							<td align="right" colspan="2"><input type="button" value="선택 삭제"></td>
						</tr>

						<tr>
							<th width="20px">선택</th>
							<th>브랜드</th>
							<th>상품명</th>
							<th>사이즈</th>
							<th>가격</th>
							<th>갯수</th>
						</tr>

						<c:forEach var="cart" items="${cartList }">
							<tr>
								<td><input type="checkbox" onclick="calculateTotalPrice()"></td>
								<td>${cart.bName }</td>
								<td><a href="DBServlet?command=product_View&pName=${ProductList.pName }">${cart.pName }</a></td>
								<td>${cart.pSize }</td>
								<td>${cart.price }</td>
								<td>${cart.balance }</td>
							</tr>
						</c:forEach>

					</c:otherwise>
				</c:choose>

			</table>
			<table>
				<tr>
					<td>선택상품 금액</td>
					<td>총 배송비</td>
					<td>할인 예상금액</td>
					<td>주문금액</td>
				</tr>
				<tr>
					<td id="totalPrice">0원</td>
					<td>b</td>
					<td>c</td>
					<Td>a+b+c</Td>
				</tr>



			</table>
			<table>
				<tr>
					<td colspan="5">장바구니 상품은 최대 30일간 저장됩니다.<br> 가격, 옵션 등 정보가 변경된 경우 주문이 불가할 수 있습니다.<br> 오늘출발 정보는 판매자가 설정한 정보에 의해 제공되며, 물류위탁 상품인 경우 물류사의 사정에 따라 실제와 다를 수 있습니다.<Br> 일부 상품의 경우 카드 할부기간이 카드사 제공 기간보다 적게 제공될 수 있습니다. by. 네이버 장바구니
					</Td>
				</tr>

			</table>

			<br> <br> <br> <input type="submit" value="결제"> <input type="reset" value="취소"> <input type="button" value="목록으로" onclick="location.href='index.jsp'">


		</form>

	</div>

</body>
</html>
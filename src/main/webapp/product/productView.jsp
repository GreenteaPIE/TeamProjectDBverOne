<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 정보</title>
<link href="css/shopping.css" rel="stylesheet">
<!-- <script type="text/javascript" src="script/cartList.js"></script> -->
<script type="text/javascript" src="/script/product.js"></script>


</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div id="wrap" align="center">

		<form name="frm" action="DBServlet">
			<input type="hidden" name="pName" value="${Product.pName }">
			<table>
				<tr>
					<td colspan="2" align="left">${Product.bName }</td>
				</tr>
				<tr>
					<td align="center">
						<c:choose>
							<c:when test="${empty Product.imgUrl }">
								<img src="images/logo.png">
							</c:when>
							<c:otherwise>
								<img src="images/${Product.imgUrl }">
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<table>
							<tr>
								<td>${Product.bName }</td>
							</tr>
							<tr>
								<Td>${Product.pName }</Td>
							</tr>
							<tr>

								<td>
									<c:choose>
										<c:when test='${Product.pGender =="1" }'>남성용</c:when>
										<c:when test='${Product.pGender =="2" }'>여성용</c:when>
										<c:otherwise>잡화</c:otherwise>
									</c:choose>
								</td>

							</tr>

							<tr>
								<th>판매가</th>
								<td>${Product.price }</td>
							</tr>

							<tr>
								<th>배송비</th>
								<td>100000원 이상 시 무료</td>
							</tr>

							<tr>
								<th>옵션 선택</th>
								<td>
									<select name="pSize">
										<c:forEach var="size" items="${pSize }">
											<option value="${size.pSize}">${size.pSize }</option>
											<c:if test="${pSize == null }">
												<option>X</option>
											</c:if>
										</c:forEach>
										<%-- 수정 by 종민 2023-04-14 10:45 --%>
									</select>
								</td>
							</tr>

							<tr>
								<th>개수</th>
								<td>
									<input type="number" name="purchasedNum" step="1" value="0"
										min="0"
									>
								</td>
							</tr>
						</table>
					</td>
			</table>
			<c:choose>
				<c:when test="${loginUser.grade == 1}">
					<input type="submit" value="상품수정" >
					<input type="hidden" name="command" value="product_edit">
				</c:when>
				<c:otherwise>
							<input type="submit" value="BUY NOW"
								onclick="return valueCheck()"
							>
							<input type="hidden" name="command" value="add_cart">
				</c:otherwise>
				<%--수정 by 종민 --%>
			</c:choose>
		</form>

		<h1>상세정보</h1>

		<table>
			<tr>
				<th>브랜드</th>
				<td>${Product.bName }</td>
			</tr>
			<tr>
				<th>상품명/모델명</th>
				<td>${Product.pName }</td>
			</tr>
			<tr>
				<td colspan="2">${Product.explain }</td>
			</tr>
		</table>
		<%--
		<c:if test="${loginUser.grade == 1}">
			<input type="button" value="상품수정"
				onclick="location.href='DBServlet?command=product_edit&pSize=${pSize}'"
			>
			<%-- 2023-04-13 종민 수정 작업중 
			<input type="button" value="상품삭제"
				onclick="loaction.href='DBServlet?command=product_delete&num=${Product.num}'"
			>
			<%-- 2023-04-13 종민 수정 작업중 
		</c:if>
		023-04-14 11:16 종민 삭제함
		 --%>
		<br>
		<input type="button" value="목록으로"
			onclick="location.href='DBServlet?command=product_list'"
		>


	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
	
	<script type="text/javascript">

function go_cart() {
	if (document.getElementById("purchasedNum").value == "0") {
		alert("수량을 입력하여 주세요.");
		document.frm.purchasedNum.focus();
	} else {
		document.frm.action = "DBServlet?command=add_cart";
		document.frm.submit();
	}

}

function go_edit() {
	var theForm = document.frm
	theForm.action = "DBServlet?command=product_edit";
	theForm.submit();
}
function go_cart() {
	var theForm = document.frm
	theForm.action = "DBServlet?command=add_cart";
	theForm.submit();
}

function valueCheck() {
	if (document.getElementById("purchasedNum").value == "0") {
		alert("수량을 입력하여 주세요.");
		document.frm.purchasedNum.focus();
		return false;
	}
	return true;
}

function sizeCheck() {
	if (document.frm.pSize.value == null){
		alert("사이즈를 선택해 주세요");
		document.frm.pSize.focus();
		return false;
	}
	return true;

}

</script>
</body>
</html>
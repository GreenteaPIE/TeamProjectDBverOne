<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/shopping.css" rel="stylesheet">
<script type="text/javascript" src="script/product.js"></script>
</head>
<body>

	<div id="wrap" align="center">

		<form action="DBServlet" name="frm">
			<input type="hidden" name="command" value="product_edit_complete">
			<table>
				<tr>
					<td>브랜드</td>
					<td>
						<select name="bName">
							<c:forEach var="brand" items="${brand }">
								<c:choose>
									<c:when test="${Product.pName eq brand.bName }">
										<option value="${brand.bName }" selected="selected">${brand.bName }</option>
									</c:when>
									<c:otherwise>
										<option value="${brand.bName }">${brand.bName }</option>
									</c:otherwise>

								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>

				<tr>
					<td>카테고리</td>
					<td>
						<select name="kind">
							<c:choose>
								<c:when test="${Product.kind ==1 }">
									<option value="1" selected="selected">상의</option>
									<option value="2">하의</option>
									<option value="3">잡화</option>
								</c:when>


								<c:when test="${Product.kind ==2 }">
									<option value="1">상의</option>
									<option value="2" selected="selected">하의</option>
									<option value="3">잡화</option>
								</c:when>

								<c:otherwise>
									<option value="1">상의</option>
									<option value="2">하의</option>
									<option value="3" selected="selected">잡화</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>

				<tr>
					<td>상품명</td>
					<td>
						<input type="text" name="pName" value="${Product.pName }">
					</td>
				</tr>

				<tr>
					<td>선택</td>
					<td colspan="2">
						<c:choose>
							<c:when test="${Product.pGender == 1 }">
								<label><input type="radio" name="pGender" value="1"
										checked="checked"
									>남성용</label>
						&nbsp;&nbsp;&nbsp; <label><input type="radio"
										name="pGender" value="2"
									>여성용</label> &nbsp;&nbsp;&nbsp; <label><input type="radio"
										name="pGender" value="3"
									>공용</label>
							</c:when>
							<c:when test="${Product.pGender == 2 }">
								<label><input type="radio" name="pGender" value="1">남성용</label>
						&nbsp;&nbsp;&nbsp; <label><input type="radio"
										name="pGender" value="2" checked="checked"
									>여성용</label> &nbsp;&nbsp;&nbsp; <label><input type="radio"
										name="pGender" value="3"
									>공용</label>
							</c:when>
							<c:otherwise>
								<label><input type="radio" name="pGender" value="1">남성용</label>
						&nbsp;&nbsp;&nbsp; <label><input type="radio"
										name="pGender" value="2"
									>여성용</label> &nbsp;&nbsp;&nbsp; <label><input type="radio"
										name="pGender" value="3" checked="checked"
									>공용</label>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>

				<tr>
					<td>판매가</td>
					<td>
						<input type="text" name="price" value="${Product.price }">
						원
					</td>
				</tr>

				<tr>
					<td>사이즈</td>
					<td>
					<input type="text" name="pSize" value="${pSize }" readonly="readonly" size="2"> 
					</td>
				</tr>
				<tr>
					<td>재고량</td>
					<td>
						<input type="text" name="balance" value="${Product.balance }">
					</td>
				</tr>

				<tr>
					<td>상품 설명</td>
					<td>
						<textarea cols="100" rows="5" name="explain">${Product.explain }</textarea>
					</td>
				</tr>


				<tr>
					<td>첨부파일</td>
					<td>
						<input type="file" name="imgUrl">
						<br>
						(이미지를 변경할때에만 사용)
					</td>
				</tr>

			</table>

			<br>
			<br>
			<br>
			<input type="submit" value="수정">
			<input type="button" value="삭제" onclick="location.href='DBServlet?command=product_delete&num=${Product.num}'">
			<input type="hidden" name="imgUrlOrigin" value="${Product.imgUrl}">
			<input type="hidden" name="num" value="${Product.num }">
			<input type="reset" value="취소">
			<input type="button" value="목록으로"
				onclick="location.href='DBServlet?command=product_List1'"
			>

		</form>


	</div>

</body>
</html>
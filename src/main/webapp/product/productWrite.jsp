<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

		<form action="DBServlet" method="post" name="frm" enctype="multipart/form-data">
			<table>
				<tr>
					<td>브랜드</td>
					<td><select name="bName">
							<option selected="selected">선택</option>
							<option value="louisvuitton">루이비통</option>
							<option value="Saint Laurent">입생로랑</option>
							<option value="prada">프라다</option>
							<option value="balenciaga">발렌시아가</option>
							<option value="hermes">에르메스</option>


					</select></td>
				</tr>

				<tr>
					<td>카테고리</td>
					<td><select name="kind">
							<option selected="selected">선택</option>
							<option value="1">상의</option>
							<option value="2">하의</option>
							<option value="3">잡화</option>
					</select></td>
				</tr>

				<tr>
					<td>상품명</td>
					<td><input type="text" name="pName"></td>
				</tr>

				<tr>
					<td>선택</td>
					<td colspan="2"><label><input type="radio" name="pGender" value="1">남성용</label> &nbsp;&nbsp;&nbsp; <label><input type="radio" name="pGender" value="2">여성용</label> &nbsp;&nbsp;&nbsp; <label><input type="radio" name="pGender" value="3">공용</label></td>
				</tr>

				<tr>
					<td>판매가</td>
					<td><input type="text" name="price">원</td>
				</tr>

				<tr>
					<td>사이즈</td>
					<td><select name="pSize">
							<option selected="selected">선택</option>
							<option value="S">S</option>
							<option value="M">M</option>
							<option value="L">L</option>
					</select></td>
				</tr>
				<tr>
					<td>재고량</td>
					<td><input type="text" name="balance"></td>
				</tr>

				<tr>
					<td>상품 설명</td>
					<td><textarea cols="100" rows="5" name="explain"></textarea></td>
				</tr>


				<tr>
					<td>첨부파일</td>
					<td><input type="file" name="imgUrl"></td>
				</tr>

			</table>

			<br> <br> <br> <input type="submit" value="작성" onclick="productWriteSubmit()"> <input type="reset" value="취소"> <input type="button" value="목록으로" onclick="location.href='DBServlet?command=product_List'">

		</form>


	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>브랜드 등록</title>
<script type="text/javascript" src="script/admin.js"></script>
</head>
<body>
<div>
<h1>브랜드 등록</h1>
	<form action="DBServlet" method="post" enctype="multipart/data-form" name="frm">
		<table>
		<tr>
		<td>브랜드 명</td>
		<td><input type="text" name="bName"></td>
		</tr>
		<tr>
		<td>브랜드 로고이미지</td>
		<td><input type="file" name="imgUrl"></td>
		</tr>
		<tr>
		<td colspan="2" align="center">
		<input type="submit" value="등록" onclick="if(brandCheck()) go_bw();">
		</td>
		</tr>
		</table>
	</form>
</div>

</body>
</html>
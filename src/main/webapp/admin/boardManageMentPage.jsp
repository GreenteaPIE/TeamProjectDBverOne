<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/adminTheme.css">
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<hr>


	<div class="mypage-container">
		<div class="mypage-header"></div>
		<div class="mypage-wrapper">
			<div class="mypage-header">
				<div class="mypage-userinfo">
					<h4>
						<strong>${loginUser.userid}</strong> 님
					</h4>
				</div>
				<div class="mypage-btns">
					<div class="mypage-menu">
						<ul>
							<button type="button" onclick="location.href='DBServlet?command=free_board_management'">자유게시판 관리</button>
							<button type="button" onclick="location.href='DBServlet?command=qna_board_management'">QnA게시판 관리</button>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>





	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
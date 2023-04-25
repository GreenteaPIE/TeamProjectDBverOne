<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Auction</title>
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

						<button type="button" onclick="location.href='DBServlet?command=admin_auction_form1'">옥션 관리</button>

					</div>
				</div>
			</div>
		</div>
	</div>





	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
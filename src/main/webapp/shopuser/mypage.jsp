<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>

<script>
	var myObj = {
		userid : "${loginUser.userid}",
		pass : "${loginUser.pass}"
	};
	sessionStorage.setItem("loginUser", JSON.stringify(myObj));
</script>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<div class="mypage-container">
		<div class="mypage-header">
			<div></div>
			<div class="mypage-wrapper">
				<div class="mypage-header">
					<div class="mypage-userinfo">
						<h4>
							<strong>${loginUser.name}</strong> 님
						</h4>
						<h4>
							<strong>등급 ${loginUser.grade} 회원</strong>
						</h4>
						<h5>현재 포인트 : ${loginUser.point}</h5>
					</div>
					<div class="mypage-btns">
						<button type="button" onclick="location.href='DBServlet?command=user_purchased_list'">주문 내역</button>
						<button type="button" onclick="location.href='DBServlet?command=My_Write&userid=${loginUser.userid}'">내가쓴글</button>
						<button type="button" onclick="openPassCheckModal()">내정보</button>
					</div>
				</div>
			</div>
		</div>


		<div class="mypage-content">
			<!-- 내 정보, 포인트, 적립금, 주문 내역, 상품 후기 등의 정보를 보여줄 컨텐츠 영역 -->
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>

	<!-- passCheckModal start -->
	<div class="modal" id="passCheckModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title" id="passCheckModalLabel">회원 정보를 확인합니다.</h1>
					<button type="button" class="close" onclick="closePassCheckModal()">x</button>
				</div>
				<form class="was-validated" action="#" name="frmModal" method="post">

					<div class="modal-body">

						<div>
							<label for="userid" class="form-label">아이디 : </label>
							<input type="text" name="userid" class="form-control" id="userid" value="${loginUser.userid}" required>
							<div class="invalid-feedback">아이디를 입력해주세요.</div>
						</div>
						<div>
							<label for="pass" class="form-label">비밀번호 : </label>
							<input type="password" name="pass" class="form-control" id="pass" value="" required>
							<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn" onclick="passwordCheck()">확인</button>
						<button type="button" class="btn" onclick="closePassCheckModal()">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- passCheckModal end -->
	<script src="script/mypage.js"></script>
</body>
</html>
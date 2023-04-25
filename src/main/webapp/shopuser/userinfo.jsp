<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 페이지</title>

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


	
	<form class="was-validated" action="DBServlet" method="post" name="frm2">
		<input type="hidden" name="command" value="update_user">


		<div>
			<label for="userid" class="form-label">아이디 : </label>
			<input type="text" name="userid" class="form-control" id="userid" readonly value="${loginUser.userid}">
		</div>
		<div>
			<label for="pass" class="form-label">비밀번호 : </label>
			<input type="password" name="pass" class="form-control" id="pass" value="" required>
			<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
		</div>
		<div>
			<label for="pass_conf" class="form-label">비밀번호 확인 : </label>
			<input type="password" name="pass_conf" class="form-control" id="pass_conf" value="" onchange="passconf()" required>
			<div class="invalid-feedback">비밀번호 확인을 입력해주세요.</div>
			<div class="invalid-feedback">비밀번호가 일치하지 않습니다.</div>
		</div>
		<div>
			<label for="name" class="form-label">이름 : </label>
			<input type="text" name="name" class="form-control" id="name" value="${loginUser.name}" readonly>

		</div>

		<label class="form-label">성별 : </label><br>
		<c:if test='${loginUser.gender=="1"}'>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="gender" id="male" value="1" checked>
				<label class="form-check-label" for="male">남자</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="gender" id="female" value="2">
				<label class="form-check-label" for="female">여자</label>
			</div>
		</c:if>
		<c:if test='${loginUser.gender=="2"}'>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="gender" id="male" value="1">
				<label class="form-check-label" for="male">남자</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="gender" id="female" value="2" checked>
				<label class="form-check-label" for="female">여자</label>
			</div>
		</c:if>
		<div>
			<label for="phone" class="form-label">전화번호 : </label>
			<input type="text" name="phone" class="form-control" id="phone" value="${loginUser.phone}">
		</div>
		<div>
			<label for="email" class="form-label">이메일 : </label>
			<input type="text" name="email" class="form-control" id="email" value="${loginUser.email}">
		</div>
		<div>
			<label for="zipcode" class="form-label">우편번호 : </label>
			<input type="text" name="zipcode" class="form-control" id="zipcode" value="${loginUser.zipcode}">
		</div>
		<div>
			<label for="address1" class="form-label">주소 : </label>
			<input type="text" name="address1" class="form-control" id="address1" value="${loginUser.address1}">
		</div>
		<div>
			<label for="address2" class="form-label">상세주소 : </label>
			<input type="text" name="address2" class="form-control" id="address2" value="${loginUser.address2}">
		</div>
		<div>
			<label for="enter" class="form-label">가입일 : </label>
			<input type="text" name="enter" class="form-control" id="enter" readonly value="${loginUser.enter}">
		</div>

		<input class="btn" type="submit" value="회원정보 수정">
		<input class="btn" type="button" value="탈퇴" onclick="confirmWithdrawal()">

<script>
function confirmWithdrawal() {
  if(confirm("정말 탈퇴하시겠습니까?")) {
    location.href='DBServlet?command=delete_user&userid=${loginUser.userid}';
  }
}
</script>

	</form>
	<br>
	<br>
	<br>

	<jsp:include page="../footer.jsp"></jsp:include>
	
	<!-- passCheckModal start -->
		<div class="modal" id="passCheckModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title" id="passCheckModalLabel">회원 정보를
							확인합니다.</h1>
						<button type="button" class="close" onclick="closePassCheckModal()">x</button>
					</div>
					<form class="was-validated" action="#" name="frmModal"
						method="post">

						<div class="modal-body">

							<div>
								<label for="userid" class="form-label">아이디 : </label> <input
									type="text" name="userid" class="form-control" id="userid"
									value="${loginUser.userid}" required>
								<div class="invalid-feedback">아이디를 입력해주세요.</div>
							</div>
							<div>
								<label for="pass" class="form-label">비밀번호 : </label> <input
									type="password" name="pass" class="form-control" id="pass"
									value="" required>
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
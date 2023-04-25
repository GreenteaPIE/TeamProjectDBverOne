<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>옥션 등록</title>
<script type="text/javascript" src="script/auction.js"></script>
<script>
	// 현재 날짜와 시간 가져오기
	var now = new Date().getTime();

	// 타이머가 끝날 날짜와 시간 설정 (예: 2023년 5월 1일)
	var countDownDate = ${auction.endTime.getTime()};

	// 매 초마다 실행될 함수
	var x = setInterval(function() {

		// 현재 날짜와 시간 가져오기
		var now = new Date().getTime();

		// 남은 시간 계산
		var distance = countDownDate - now;

		// 남은 시간을 초, 분, 시, 일 단위로 변환
		var days = Math.floor(distance / (1000 * 60 * 60 * 24));
		var hours = Math.floor((distance % (1000 * 60 * 60 * 24))
				/ (1000 * 60 * 60));
		var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		var seconds = Math.floor((distance % (1000 * 60)) / 1000);
		var milliseconds = distance % 10;

		// 표시할 텍스트 생성
		var countdownText = days + "일 " + hours + "시간 " + minutes + "분 "
				+ seconds + ". " + milliseconds + " 초 ";

		// 텍스트를 countdown ID를 가진 HTML 요소에 삽입
		document.getElementById("countdown").innerHTML = countdownText;

		// 타이머 종료 시 실행될 함수
		if (distance < 0) {
			clearInterval(x);
			document.getElementById("countdown").innerHTML = "기간이 만료된 경매입니다.";
			
		}
	}, 50);
</script>
</head>
<body onload="startTimer()">
	<jsp:include page="../header.jsp"></jsp:include>


	<!-- Page Header Start -->

	<div class="container bg-secondary mb-3" style="max-width: 800px;">
		<div
			class="d-flex flex-column align-items-center justify-content-center"
			style="min-height: 200px"
		>
			<h1 class="font-weight-semi-bold text-uppercase mb-3">
				Auction
			</h1>
		
			<h1>
				<span id="countdown"></span>
			</h1>
			<div class="d-inline-flex">
				<p class="m-0">
					<a href="index.jsp">Home</a>
				</p>
			</div>

		</div>
	</div>
	<!-- Page Header End -->


	<!-- Shop Detail Start -->
	<div class="container-fluid py-5">
		<div class="row px-xl-5">
			<div class="col-lg-5 pb-5">
				<div id="auction-carousel" class="carousel slide" data-ride="carousel">
					<div class="carousel-inner border">

						<img class="w-100 h-100" style="height: 280px" src="images/${auction.imgUrl}" alt="Image">
     

					</div>

				</div>
			</div>

			<div class="col-lg-7 pb-5">
				<h3 class="font-weight-semi-bold">${auction.pName }</h3>

				<h3 class="font-weight-semi-bold mb-4">

					원가 &#8361;
					<fmt:formatNumber value="${Integer.parseInt(originProduct.price)}" pattern="###,###" />
				</h3>
				<p class="mb-4">${originProduct.explain }</p>


				<form action="DBServlet" method="post" name="frm">
					<input type="hidden" name="command" value="auction_deal">

					<div class="custom-control custom-radio custom-control-inline">
						<p class="text-dark font-weight-medium mb-0 mr-3">사이즈: ${auction.pSize }</p>
					</div>
					<div class="d-flex mb-3" style="margin-top: 40px;">
						<h3 class="font-weight-semi-bold">시작가 &#8361; ${auction.startPrice }</h3>
					</div>
					<c:choose>
						<c:when test="${auction.onOff == 0 }">
							<div class="d-flex mb-3" style="margin-top: 40px;">
								<p style="font-size: 1.3em;" class="font-weight-semi-bold">낙찰자: ${auction.userId }</p>
							</div>
						</c:when>
						<c:otherwise>
							<div class="d-flex mb-3" style="margin-top: 40px;">
								<p style="font-size: 1.3em;" class="font-weight-semi-bold">현재 낙찰 예상자: ${auction.userId }</p>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="d-flex mb-3" style="margin-top: 40px;">
						<h3 class="font-weight-semi-bold">현재가 &#8361;${auction.price }</h3>
					</div>
					<c:if test="${auction.onOff== 1 }">
						<div style="display: flex;">
							<span style="font-size: 1.7em;">&#8361;</span>
							<input type="text" class="form-control" value="${auction.price }" name="price" maxlength="15" style="width: 300px;">
						</div>
					</c:if>

					<div class="d-flex align-items-center mb-4 pt-2">
						<input type="hidden" name="currentPrice" value="${auction.price }">
						<input type="hidden" name="num" value="${auction.num }">
						<input type="hidden" name="originProduct" value="${originProduct.pName }">
						<c:choose>
							<c:when test="${auction.onOff==0 }">
								<input type="button" value="뒤로가기" class="btn btn-primary px-3" onclick="location.href='DBServlet?command=auction_view'">
							</c:when>
							<c:otherwise>
								<c:if test="${not empty userid}">
									<input type="submit" class="btn btn-primary px-3" value="입찰" onclick="return priceCheck()">
									<input type="reset" class="btn btn-primary px-3" value="Reset">
								</c:if>
								<c:if test="${empty userid}">
									<input type="submit" class="btn btn-primary px-3" value="입찰" onclick="alert('로그인 후에 이용이 가능합니다.'); return false;">
									<input type="reset" class="btn btn-primary px-3" value="Reset">
								</c:if>
							</c:otherwise>
						</c:choose> 

					</div>

				</form>

			</div>
		</div>
	</div>




	<!-- Shop Detail End -->

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
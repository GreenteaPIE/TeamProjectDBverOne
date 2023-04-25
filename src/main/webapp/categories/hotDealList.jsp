<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

</head>

<body>

	<jsp:include page="../header.jsp"></jsp:include>
	<%@ page import="com.categorieslist.dao.CateGoriesListDAO"%>

	<%
	// DAO 클래스의 인스턴스 생성
	CateGoriesListDAO dao = CateGoriesListDAO.getInstance();

	//남은 시간 받아오기
	long remainingTime = dao.getCacheRemainingTime();
	%>



	<!-- JavaScript 변수에 남은 시간 저장 -->
	<script>
		var remainingTime =
	<%=remainingTime%>
		;

		// 일정 시간마다 남은 시간 업데이트
		setInterval(function() {
			remainingTime -= 1000; // 1초씩 감소
			if (remainingTime < 0) {
				remainingTime = 0;
			}
			// 0분 0초인 경우 페이지 새로고침
			if (remainingTime === 0) {
				location.reload();
			}

			// 분과 초 계산
			var minutes = Math.floor(remainingTime / 60000);
			var seconds = Math.floor((remainingTime % 60000) / 1000);

			// 두자리 숫자로 변환
			minutes = ("0" + minutes).slice(-2);
			seconds = ("0" + seconds).slice(-2);

			// 화면에 출력
			var remainingTimeElem = document.getElementById("remaining-time");
			remainingTimeElem.innerText = minutes + "분 " + seconds + "초";

			// 0분 0초인 경우 페이지 새로고침
			if (remainingTime === 0) {
				location.reload();
			}
		}, 1000);
	</script>

	<!-- Page Header Start -->
	<div class="container bg-secondary mb-3" style="max-width: 800px;">
		<div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 200px">
			<h1 class="font-weight-semi-bold text-uppercase mb-3">
				Hot Deal
				<hr>
				<div style="color: #9FA2A6" id="remaining-time"></div>
			</h1>
			<div class="d-inline-flex">
				<p class="m-0">
					<a href="index.jsp">Home</a>
				</p>
			</div>
		</div>
	</div>


	<!-- Page Header End -->


	<!-- Shop Start -->
	<div id="my-container" class="container-fluid pt-5">



		<!-- Shop Product Start -->
		<div class="col-lg-9 col-md-12">
			<div class="row pb-3">

				<%-- 할인율을 15%로 설정 --%>
				<c:set var="discountRate" value="0.1" />

				<c:forEach var="CateGoriesList" items="${CateGoriesList}">
					<div class="col-lg-4 col-md-6 col-sm-12 pb-1">
						<div class="card product-item border-0 mb-4">
							<div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
								<img class="img-fluid w-100" style="height: 280px" src="images/${CateGoriesList.imgUrl}" alt="">

							</div>
							<div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
								<h6 class="text-truncate mb-3">${CateGoriesList.pName}</h6>
								<div class="d-flex justify-content-center">
									<%-- 할인 전 가격 --%>
									<h6 class="text-muted ml-2">

										<fmt:formatNumber value="${discountRate}" pattern="##.#%" />
										Sale<br>

										<del>
											<fmt:formatNumber value="${Integer.parseInt(CateGoriesList.price)}" pattern="₩###,###" />
										</del>

									</h6>
								</div>
								<div class="d-flex justify-content-center">
									<%-- 할인 후 가격 계산 --%>
									<%-- 할인 전 가격을 가져온 후 할인율로 계산하여 할인 가격 계산 --%>
									<c:set var="discountedPrice" value="${Math.round(Integer.parseInt(CateGoriesList.price) - (Integer.parseInt(CateGoriesList.price) * discountRate))}" />
									<%-- 할인 가격과 할인율 출력 --%>
									<h6>

										<fmt:formatNumber value="${Math.round(discountedPrice/100)*100}" pattern="₩###,###" />
									</h6>

								</div>

							</div>
							<c:if test="${not empty loginUser}">
								<input type="button" value="구매" class="btn btn-primary px-3" onclick="location.href='DBServlet?command=HotDeal_Buy_Page&num=${CateGoriesList.num}&dp=${Math.round(discountedPrice/100)*100}'">
							</c:if>
							<c:if test="${empty loginUser}">
								<input type="button" value="구매" class="btn btn-primary px-3" onclick="alert('로그인 후에 이용이 가능합니다.'); return false;">
							</c:if>

						</div>


					</div>
				</c:forEach>
			</div>



		</div>
	</div>
	<!-- Shop Product End -->



	<!-- Shop End -->


	<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty loginUser }">
	<jsp:forward page="index.jsp" />
</c:if>


<!DOCTYPE html>
<html>
<head>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	
 <!-- Categories Start -->
	<div class="container-fluid pt-5">
		<div class="row px-xl-5 pb-3">
			<div class="col-lg-4 col-md-6 pb-1">
				<div class="cat-item text-center" style="padding: 30px;">
					<p class="text-right"></p>
					<a href="DBServlet?command=BRAND_LIST&bname=balenciaga" class="cat-img position-relative overflow-hidden mb-3"> <img class="img-fluid" src="images/balenciagalogo.png" alt="">
					</a>
					<h5 class="font-weight-semi-bold m-0"></h5>
				</div>
			</div>
			<div class="col-lg-4 col-md-6 pb-1">
				<div class="cat-item text-center" style="padding: 30px;">
					<p class="text-right"></p>
					<a href="DBServlet?command=BRAND_LIST&bname=hermes" class="cat-img position-relative overflow-hidden mb-3"> <img class="img-fluid" src="images/hermeslogo.png" alt="">
					</a>
					<h5 class="font-weight-semi-bold m-0"></h5>
				</div>
			</div>
			<div class="col-lg-4 col-md-6 pb-1">
				<div class="cat-item text-center" style="padding: 30px;">
					<p class="text-right"></p>
					<a href="DBServlet?command=BRAND_LIST&bname=louisvuitton" class="cat-img position-relative overflow-hidden mb-3"> <img class="img-fluid" src="images/louisvuittonlogo.png" alt="">
					</a>
					<h5 class="font-weight-semi-bold m-0"></h5>
				</div>

			</div>
			<div class="col-lg-4 col-md-6 pb-1">
				<div class="cat-item text-center" style="padding: 30px;">
					<p class="text-right"></p>
					<a href="DBServlet?command=BRAND_LIST&bname=prada" class="cat-img position-relative overflow-hidden mb-3"> <img class="img-fluid" src="images/pradalogo.png" alt="">
					</a>
					<h5 class="font-weight-semi-bold m-0"></h5>
				</div>

			</div>
			<div class="col-lg-4 col-md-6 pb-1">
				<div class="cat-item text-center" style="padding: 30px;">
					<p class="text-right"></p>
					<a href="DBServlet?command=BRAND_LIST&bname=Saint Laurent" class="cat-img position-relative overflow-hidden mb-3"> <img class="img-fluid" src="images/saintlaurentlogo.png" alt="">
					</a>
					<h5 class="font-weight-semi-bold m-0"></h5>
				</div>

			</div>


		</div>
	</div>
	<!-- Categories End -->
 

	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

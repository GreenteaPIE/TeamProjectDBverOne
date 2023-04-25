<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 상세보기</title>
<script type="text/javascript" src="script/freeboard.js"></script>
</head>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>


	<div class="container">


		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="4"
							style="background-color: #eeeeee; text-align: center;">게시판 글
							보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="1" style="width: 20%;">글 제목</td>
						<td colspan="1">${board.title}</td>
						<td colspan="1">조회수</td>
						<td colspan="1">${board.readcount }</td>
					</tr>
					<tr>
						<td colspan="1">작성일자</td>
						<td colspan="1"><fmt:formatDate value="${board.writedate }" /></td>
						<td colspan="1">작성자</td>
						<td colspan="1">${board.userid }</td>
						
					</tr>

					<tr>
						<td>내용</td>
						<td colspan="4" style="height: 200px; text-align: left;">
						
						${board.content} 
						
						<c:choose>
						
								<c:when test="${empty board.imgurl}">
								</c:when>
								<c:otherwise>
								<hr>
									<img src="images/${board.imgurl}" style="max-width: 80%; height: auto;">
								</c:otherwise>
							</c:choose></td>
						
					</tr>
					
				</tbody>

			</table>

			





		</div>
	</div>



	<div id="disqus_thread"></div>



	<script>
		/**
		 *  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
		 *  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables    */
		/*
		var disqus_config = function () {
		this.page.url = PAGE_URL;  // Replace PAGE_URL with your page's canonical URL variable
		this.page.identifier = PAGE_IDENTIFIER; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
		};
		 */
		(function() { // DON'T EDIT BELOW THIS LINE
			var d = document, s = d.createElement('script');
			s.src = 'https://diamonblack.disqus.com/embed.js';
			s.setAttribute('data-timestamp', +new Date());
			(d.head || d.body).appendChild(s);
		})();
	</script>
	<noscript>
		Please enable JavaScript to view the <a
			href="https://disqus.com/?ref_noscript">comments powered by
			Disqus.</a>
	</noscript>
	<div class="text-center mt-3">
				<c:if
					test="${board.userid eq sessionScope.userid or sessionScope.userid eq 'admin'}">
					<button class="btn btn-primary"
						onclick="location.href='DBServlet?command=freeboard_update_form&num=${board.num}'">게시글
						수정</button>
					<button class="btn btn-primary" onclick="deleteBoard(${board.num})">게시글
						삭제</button>
				</c:if>
				<button class="btn btn-primary"
					onclick="location.href='DBServlet?command=free_board_list'">게시글
					리스트</button>
				<c:if test="${not empty userid}">
					<button class="btn btn-primary"
						onclick="location.href='DBServlet?command=free_board_write_form'">게시글
						등록</button>
				</c:if>
				<c:if test="${empty userid}">
					<button class="btn btn-primary"
						onclick="alert('로그인 후에 이용이 가능합니다.');">게시글 등록</button>
				</c:if>
			</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>

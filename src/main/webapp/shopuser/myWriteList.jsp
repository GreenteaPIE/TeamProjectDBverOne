<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<%
int listcount = (Integer)(request.getAttribute("listcount"));
int nowpage = (Integer)(request.getAttribute("page"));
int maxpage = (Integer)(request.getAttribute("maxpage"));
int startpage = (Integer)(request.getAttribute("startpage"));
int endpage = (Integer)(request.getAttribute("endpage"));
%>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="UTF-8">
<title>자유게시판</title>
</head>
<body>
<hr>
   <div class="container">
      <div class="row">
         <table class="table table-striped"
            style="text-align: center; border: 1px solid #dddddd">
            <thead>
               <tr>
                  <th style="background-color: #eeeeee; text-align: center;">번호</th>
                  <th style="background-color: #eeeeee; text-align: center;">제목</th>
                  <th style="background-color: #eeeeee; text-align: center;">작성자</th>
                  <th style="background-color: #eeeeee; text-align: center;">작성일</th>
                  <th style="background-color: #eeeeee; text-align: center;">조회</th>
               </tr>
            </thead>
            <tbody>

               <tr>
                  <c:forEach var="freeboard" items="${boardList }">
                     <tr class="record"></tr>
                     <td>${freeboard.num }</td>
                     <td><a
                        href="DBServlet?command=free_board_view&num=${freeboard.num }">${freeboard.title }</a></td>
                     <td>${freeboard.userid }</td>
                     <td><fmt:formatDate value="${freeboard.writedate }" /></td>
                     <td>${freeboard.readcount }</td>
                  </c:forEach>

               </tr>
               

            </tbody>
   
         </table>
         <c:if test="${not empty userid}">
            <a href="DBServlet?command=free_board_write_form&userid=${userid}"
               class="btn btn-primary pull-right">게시글 등록</a>
         </c:if>
         <c:if test="${empty userid}">
            <a href="#" onclick="alert('로그인 후에 이용이 가능합니다.'); return false;"
            class="btn btn-primary pull-right">게시글
               등록</a>
         </c:if>
         <!-- 페이징 -->
         <div class="col-12 pb-1">
                        <nav aria-label="Page navigation">
                          <ul class="pagination justify-content-center mb-3">
                           
                            <%if(nowpage <= 1){ %>
                            <li class="page-item disabled">
                              <a class="page-link " href="DBServlet?command=free_board_list&page=<%= nowpage-1 %>" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                              </a>
                            </li>
                             <%}else{%>
                              <li class="page-item">
                              <a class="page-link" href="DBServlet?command=free_board_list&page=<%= nowpage-1 %>" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                              </a>
                            </li>
                              <%}%>
                          
                              <%
         for(int a= startpage;a<=endpage;a++){
            if(a == nowpage){
      %>
         <li class="page-item active"><a class="page-link " ><%= a %></a>&nbsp;</li>      
            <%}else{%>
                            <li class="page-item"><a class="page-link" href="DBServlet?command=free_board_list&page=<%= a %>"><%= a %></a>&nbsp;</li>
                              <%}%>
      <%}%>
                            
                             <% if(nowpage >= maxpage){ %> 
                             <li class="page-item disabled">
                              <a class="page-link" href="DBServlet?command=free_board_list&page=<%= nowpage+1 %>" aria-label="Next">
                              <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                                </a>
                            </li>
                             <%} else { %>
                             <li class="page-item">
                              <a class="page-link" href="DBServlet?command=free_board_list&page=<%= nowpage+1 %>" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                              </a>
                              <%}%>
                            </li>
                          </ul>
                        </nav>
                    </div>
      </div>

   </div>



<hr>

   <jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
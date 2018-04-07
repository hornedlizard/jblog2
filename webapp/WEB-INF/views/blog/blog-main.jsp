<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<style type="text/css">
.pager {
	width:100%;
	text-align:center;
}
.pager li {
	display: inline;
}
</style>
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:if test="${not empty posts[0] }">
						<h4>${posts[0].title }</h4>
						<p style="white-space: pre-line;">
							${posts[0].content }
						<p>
					</c:if>
				</div>
				<ul class="blog-list">
					<c:forEach items="${posts }" var="post">
						<li><a href="">${post.title }</a> <span>${post.regdate }</span>	</li>
					</c:forEach>
				</ul>
				<div class="pager">
			<ul>
				<c:if test="${page.prev}">
					<li><a href="${page.startPage-page.displayPage }">◀</a></li>
				</c:if>
				<c:forEach begin="${page.startPage }" end="${page.endPage }" var="index">
				<c:choose>
					<c:when test="${index <= page.totalPage }">
			            <li ${page.page == index ? 'class = "selected"' : ''}>
			            	<a href="">${index}</a>
			            </li>
		            </c:when>
		            <c:when test="${index > page.totalPage }">
			            <li ${page.page == index ? 'class = "selected"' : ''}>
			            	${index}
			            </li>
		            </c:when>
				</c:choose>
		        </c:forEach>
				<c:if test="${page.next}">
					<li><a href="${page.endPage+1 }">▶</a></li>
				</c:if>
			</ul>
		</div>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>
</body>
</html>
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
	float: left;
}
</style>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blog.title }</h1>
			<ul>
				<li><a href="">로그인</a></li>
				<li><a href="">로그아웃</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}/admin/basic">블로그 관리</a></li>
			</ul>
		</div>
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
		
		

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blog.logo}">
				<%-- <img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg"> --%>
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<li><a href="">닥치고 스프링</a></li>
				<li><a href="">스프링 스터디</a></li>
				<li><a href="">스프링 프로젝트</a></li>
				<li><a href="">기타</a></li>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>
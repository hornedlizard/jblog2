<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="extra">
	<div class="blog-logo">
		<img src="${pageContext.request.contextPath}${blog.logo}">
		<%-- <img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg"> --%>
	</div>
</div>
<div id="navigation">
	<h2>카테고리</h2>
	<ul>
		<c:forEach items="${category }" var="category">
			<li><a href="">${category.name }</a></li>
		</c:forEach>
	</ul>
</div>
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
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

function checkId(){
	$.ajax({
		url: "${pageContext.servletContext.contextPath }/api/user/checkid",
		dataType: "json",
		type: "post",
		data: $("#blog-id").serialize(),
		success: function(response){
			if (response.result != "success") {
				console.log(response.message);
				return;
			}
			if (response.data == "exist") {
				/* alert("사용중인 id 입니다."); */
				$("#blog-id").css("background-color", "#FFCECE");
				$("#checkId").html("<p>사용중인 id 입니다.</p>");
				$("#btn-join").attr("disabled", true);
				return;
			}
			if (response.data == "not exist") {
				$("#blog-id").css("background-color", "#FFFFFF");
				$("#checkId").html("<p>사용 가능한 ID입니다</p>");
				$("#btn-join").attr("disabled", false);
				/* $("#img-checkemail").show(); */
			}
		}
	});
};

</script>
</head>
<body>
	<div class="center-content">
		<a href="${pageContext.servletContext.contextPath }/main"><h1 class="logo">JBlog</h1></a>
		<ul class="menu">
			<c:choose>
				<c:when test="${empty authUser }">
					<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a></li>
					<%-- <li><a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a></li> --%>
					<li><a href="">내블로그</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
					<li><a href="">내블로그</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
		<form class="join-form" id="join-form" method="post" action="${pageContext.servletContext.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="" required="required">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" oninput="checkId()" type="text" required="required"> 
			<!-- <input id="btn-checkId" type="button" value="id 중복체크"> -->
			<%-- <img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png"> --%>
			<span id="checkId"></span>

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" required="required" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" disabled="disabled" id="btn-join" value="가입하기">

		</form>
	</div>
</body>
</html>

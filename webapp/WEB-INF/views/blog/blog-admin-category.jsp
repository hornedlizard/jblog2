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
$(document).ready(function(){
	$("#addCategory").click(function(){
		var name = $("#name").val();
		var desc = $("#desc").val();
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/${authUser.id}/api/category/add",
			dataType: "json",
			type: "post",
			data: {"name": name, "memo": desc},
			success:function(result) {
				var item = result.data;
				var num = $(".admin-cat tr").length;
				console.log(num);
				var cat = "";
					cat += "<tr id="+item.no+"><td>"+(num == null ? 1 : num)+"</td>"
					cat += "<td>"+item.name+"</td>"
					cat += "<td></td>"
					cat += "<td>"+item.memo+"</td>"
					cat += "<td><img data-no ="+item.no+" class="+"remove"+" src="+"${pageContext.request.contextPath}/assets/images/delete.jpg"+">"
				$(".admin-cat").append(cat);
			}
		});
	});
	
	$(document).on("click", ".remove", function(){
		var no = $(this).data("no");
		console.log(no);
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/${authUser.id}/api/category/remove",
			dataType: "json",
			type: "post",
			data: {"no": no},
			success:function(result) {
				console.log(result);
				$("#"+no).remove();
			}
		});
	});
	
});

</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>Spring 이야기</h1>
			<ul>
				<c:choose>
					<c:when test="${empty authUser }">
						<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/user/login">내블로그</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}/admin/basic">블로그 관리</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:forEach items="${list}" var="category" varStatus="status">
						<tr id="${category.no }">
							<td class="num">${status.index + 1 }</td>
							<td>${category.name }</td>
							<td></td>
							<td>${category.memo }</td>
							<td><img data-no ="${category.no}" class="remove" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
						</tr>  
		      		</c:forEach>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" id="name" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" id="desc" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="addCategory" type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>
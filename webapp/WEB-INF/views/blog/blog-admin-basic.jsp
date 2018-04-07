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
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script type="text/javascript">
function imgChange(){
	$("#image").attr("src", URL.createObjectURL($("#logoImg")[0].files[0]));
	/* var formData = new FormData()
	formData.append("logo-file", $("#logoImg")[0].files[0]);
	$.ajax({
		url: "${pageContext.servletContext.contextPath }/${authUser.id}/api/blog/img",
		type: "post",
		data: formData,
		contentType: false,
		processData: false,
		enctype: "multipart/form-data",
		success: function(response){
			$("#image").attr("src", "data:image/jpg;base64,"+response.data);
		}
	}); */
};
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp">
					<c:param name="menu" value="basic"/>
				</c:import>
				<form action="${pageContext.request.contextPath}/${authUser.id }/admin/basic" 
					method="post" enctype="multipart/form-data">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title" value="${blog.title }"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img id="image" src="${pageContext.request.contextPath}${blog.logo}"></td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input id="logoImg" type="file" accept="image" onchange="imgChange()" name="logo-file"></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>
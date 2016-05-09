<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${ctx}/static/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#submit").click(function(){
		alert("submit");
		$.post("user/logon","username="+$("#username").val()+"&password="+$("#password").val(),function(data,status){
			alert(data.errorCode);
		},"json");
	});
});
</script>
<title>logon</title>
</head>
<body>
	<h1>logon登录页面</h1>
		<label>用户名</label><input type="text" name="username" id="username"/>
		<label>密码</label><input type="text" name="password" id="password"/>
		<input type="button" value="确定" id="submit">
</body>
</html>
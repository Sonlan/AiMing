<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>logon</title>
</head>
<body>
	<h1>logon登录页面</h1>
	<form action="${ctx}/user/logon">
		<label>用户名</label><input type="text" name="username"/>
		<label>密码</label><input type="text" name="password"/>
		<input type="submit" value="确定">
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>main</title>
</head>
<body>
	main
	<a href="${ctx}/user/logon">登录</a>
	<a href="${ctx}/user/start">开始</a>
	<a href="${ctx}/user/stop">停止</a>
</body>
</html>
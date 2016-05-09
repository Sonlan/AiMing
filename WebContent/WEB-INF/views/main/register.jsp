<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
  form {
	padding: 20px 10px 20px 30%;
  }
  
  label {
	display: inline-block;
	width: 100px;
	text-align:right;
	font-weight: bold;
	margin: 0 15px 0 0;
	color: white;
  }
  
  p {
    padding: 2px;
  }
  
  #button {
    margin-left: 150px;
  }
</style>
</head>
<body>
<ul>
	<form id="reg">
		<p>
			<label for="username">用户名:</label>
			<input type="text" name="username" id="username">
		</p>
		<p>
			<label for="password">密码: </label>
			<input type="text" name="password" id="password">
		</p>
		<p>
			<label for="password">请再次输入密码: </label>
			<input type="text" name="confirm" id="confirm">
		</p>
		<p>
			<input type="submit" name="button" id="button" value="Submit" >
		</p>
	</form>
</ul>
</body>
</html>
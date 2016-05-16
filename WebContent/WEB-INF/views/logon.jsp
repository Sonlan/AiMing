<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Login</title>
<link type="text/css" href="${ctx}/static/css/main.css" rel="stylesheet">

</head>
<body>
<div class="wrapper">
	<div class="header">
		<p class="logo">爱民 <span>制药</span> <span class="mm">Air<br/>
			Filter<br/>
			Management</span></p>
	</div>
	<div class="login">
		<h1>超级用户登录</h1>
		<div id="formwrapper">
			<form method="get"  id="login">
				<p>
					<label for="username">用户名:</label>
					<input type="text" name="username" id="username">
				</p>
				<p>
					<label for="password">密码: </label>
					<input type="text" name="password" id="password">
				</p>
				<p>
					<input type="hidden" name="level" id="level" value=1>
				</p>
				<p>
					<input type="submit" name="button" id="button" value="Submit" >
				</p>
			</form>
		</div>
	</div>
	<div class="footer">
		<p>爱民 制药: 空调滤芯控制系统, by <a href="http://nacerc.hust.edu.cn/">国家防伪工程技术研究中心</a>. </p>
	</div>
</div>

<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>
$(document).ready(function() {
/*	$('#login').submit(function(evt){
	    evt.preventDefault();
	    var formData = $(this).serialize();
	    $.post("user/logon",formData,function(data,status){
	    	if(data.errorCode==0){
	    		alert("登录成功"+data.errorCode);
	    	}else{
	    		alert("登录失败"+data.errorCode);
	    	}
	    }); 
	},"json");*/
    $('#login').submit(function(evt){
    evt.preventDefault();
    var formData = $(this).serialize();
	$.get('../../AiMing/user/logon', formData, processData);
  });
  function processData(data) {
    $.each(data, function(name, value){
	  if(name == 'errorCode') {
	    if(value == 0) {
		  location.href=data.param;
		} else if (value == 1) {
		} else if (value == 2) {
		} 
	  }
	});
  } 
}); // end ready
</script>
</body>
</html>
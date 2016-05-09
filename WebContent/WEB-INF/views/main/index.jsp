<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<title>滤芯管理系统</title>
<link href="${ctx}/static/css/main.css" rel="stylesheet"/>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="${ctx}/static/js/index.js"></script>
</head>
<body>
<div class="wrapper">
	<div class="header">
		<p class="logo">爱明 <span>制药</span> <span class="mm">Air<br/>
			Filter<br/>
			Management</span></p>
	</div>
	<div class="navig">
	 <ul id='navigation'>
	   <li><a href = '${ctx}/main/toRegister'>注册</a></li>
	   <li><a href = '${ctx}/main/toCheck'>查询</a></li>
	   <li><a href = '${ctx}/main/toManul'>手动报废</a></li>
	   <li><a href = '${ctx}/main/toActive'>批量激活</a></li>
	   <li><a href = '${ctx}/timing/start'>开始计时</a></li>
	   <li><a href = '${ctx}/timing/stop'>停止计时</a></li>
	 </ul>
	</div>
	<div id="content">
	</div>
	<div class="footer">
		<p>爱明 制药: 空调滤芯控制系统, by <a href="http://nacerc.hust.edu.cn/">国家防伪工程技术研究中心</a>. </p>
	</div>
</div>
</body>
</html>
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
		<p class="logo">爱民 <span>制药</span> <span class="mm">Air<br/>
			Filter<br/>
			Management</span></p>
	</div>
	<div class="navig">
	 <ul id='navigation'>
	   <li><a href = 'register'>注册</a></li>
	   <li><a href = 'inqury'>查询</a></li>
	   <li><a href = 'invalid'>手动报废</a></li>
	   <li><a href = 'valid'>批量激活</a></li>
	   <li><a href = 'start'>停止计时</a></li>
	   <li><a href = 'logout'>退出登录</a></li>
	 </ul>
	</div>
	<div id="content">
	  <div id='defContent'>
		<table border='1' id='table1'>
		  <tr>
			  <th>滤芯号</th>
			  <th>空调号</th>
			  <th>清洗次数</th>
			  <th>使用时间</th>
			  <th>1:正在使用</th>
			  <th>1:未报废</th>
		  </tr>
		  <tr id='tr1'>
		      <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
		  </tr>
		  <tr id='tr2'>
		      <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
		  </tr>
		  <tr id='tr3'>
		      <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
		  </tr>
		  <tr id='tr4'>
		      <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
		  </tr>
		  <tr id='tr5'>
		      <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
		  </tr>
		  <tr id='tr6'>
		      <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
		  </tr>
		  <tr id='tr7'>
		      <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
		  </tr>
		  <tr id='tr8'>
		      <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
		  </tr>
		  <tr id='tr9'>
		      <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
		  </tr>
		</table>
		<div id='conPage'>
          第<select id='pageSel'>
		  </select>页
		</div>
		<div id='select'>
			<p>查询条件:</p>
			<div id='baofei'>
			  <input type='radio' name='baofei' value='0' checked>未报废</input>
			  <input type='radio' name='baofei' value='1'>已报废</input>
			</div>
			<div id='inuse'>
			  <input type='radio' name='inuse' value='0' checked>正在使用</input>
			  <input type='radio' name='inuse' value='1'>未使用</input>		  
			</div>
			<div id='adId'>
			  <input name='bianhao' type='radio' value='1' checked>一号空调</input>
			  <input name='bianhao' type='radio' value='2'>二号空调</input>
			  <input name='bianhao' type='radio' value='3'>三号空调</input>
			</div>
			<div>
			  剩余次数：
			  <select id='washRemain'>
			    <option value=0>--所有--</option>
			    <option value=1><=1</option>
				<option value=2><=2</option>
				<option value=3><=3</option>
				<option value=4><=4</option>
				<option value=5><=5</option>
			  </select>
			</div>
            <div>
			  剩余时限：
			  <select id='timeRemain'>
			    <option value=0>--所有--</option>
			    <option value=2><=2</option>
				<option value=4><=4</option>
				<option value=6><=6</option>
				<option value=8><=8</option>
				<option value=10><=10</option>
				<option value=12>>10</option>
			  </select>
			</div>
		</div>
		<div id='conbtn'>
		    <input type='button' value='上一页' id='btnPre'></input>
			<input type='button' value='下一页' id='btnNext'></input>
		</div>
	  </div>
	</div>
	<div class="footer">
		<p>爱民 制药: 空调滤芯控制系统, by <a href="http://nacerc.hust.edu.cn/">国家防伪工程技术研究中心</a>. </p>
	</div>
</div>
</body>
</html>
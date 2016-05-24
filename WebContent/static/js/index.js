$(document).ready(function() {
    /* 查询条件 */
    var currentPage = 0,	            //当前页面 首页为0
	    maxPage = 0,                    //最大页面
	    user_currentPage = 0;               //当前用户页面
        user_maxPage = 0;                   //用户最大页面
	    JSONData = {                    //查询请求JSON
			aliveTime : 0,
			washRemain : 0,
			inuse : 1,
			alive : 1,
			ac_id : '01',
			page : 0
	    };
	
	/* 需要变动的DOM */
    var content = document.getElementById('content'),
        defcon = document.getElementById('defContent'),
	    pageSel = document.getElementById('pageSel');
    
    function changeString(data){
    	var str = '';
    	str += 'aliveTime=' + data.aliveTime
    	    + '&washRemain=' + data.washRemain
    	    + '&inuse=' + data.inuse
    	    + '&alive=' + data.alive
    	    + '&ac_id=' + data.ac_id
    	    + '&page=' + data.page;
    	return str;
    }
		
	/* 注册模块 done*/
	var form1 = document.createElement('form'),
	    p1 = document.createElement('p'),
	    p2 = document.createElement('p'),
	    p3 = document.createElement('p'),
	    p4 = document.createElement('p'),
	    username = document.createElement('input'),
		label1 = document.createElement('label'),
		password = document.createElement('input'),
		label2 = document.createElement('label'),
		confirm = document.createElement('input'),
		label3 = document.createElement('label'),
		submit = document.createElement('input');
	//注册模块页面生成
	form1.id = 'register';
	username.type = 'text';
	username.name = 'username';
	username.id = 'username';
	label1.htmlFor = 'username';
	label1.appendChild(document.createTextNode('用户名：'));			
	p1.appendChild(label1);
	p1.appendChild(username);
	password.type = 'password';
	password.name = 'password';
	password.id = 'password';
	label2.htmlFor = 'password';
	label2.appendChild(document.createTextNode('密码：'));			
	p2.appendChild(label2);
	p2.appendChild(password);
	confirm.type = 'password';
	confirm.name = 'confirm';
	confirm.id = 'confirm';
	label3.htmlFor = 'confirm';
	label3.appendChild(document.createTextNode('确认密码：'));			
	p3.appendChild(label3);
	p3.appendChild(confirm);
	submit.type = 'submit';
	submit.id = 'regBtn';
	submit.name = 'submit';
	submit.value = 'Submit';
	p4.appendChild(submit);
	form1.appendChild(p1);
	form1.appendChild(p2);
	form1.appendChild(p3);
	form1.appendChild(p4);
	function userManage(){
	  var i, h=['<table border="1" width="70%">'];
	  h.push('<thead>');
	    h.push('<tr><th>用户名<\/th><th>操作<\/th><\/tr>');
	  h.push('<\/thead>');
	  h.push('<tbody id="regBody">');
	    for(i=0; i<5; i++) {
		  h.push('<tr><td>');
	      h.push('<\/td><td>');
		  h.push('<input type="button" value="删除" class="user_delete"/>');
		  h.push('<input type="button" value="修改密码" class="user_edit"/>');
		  h.push('<\/td><\/tr>');
		}
	  h.push('<\/tbody>');
	  h.push('<\/table>');
	  //h.push('<p><input type="button" value="上一页" id="user_pre"\/>');
	  //h.push('<input type="button" value="下一页" id="user_next"\/><\/p>');
	  
	  form1.innerHTML += h.join('');
	}
    userManage();
    var user_p = document.createElement('p');
    var btn_pre = document.createElement('input');
    var btn_next = document.createElement('input');
    btn_pre.type = "button";
    btn_pre.value = "上一页";
    btn_next.type = "button";
    btn_next.value = "下一页";
    user_p.appendChild(btn_pre);
    user_p.appendChild(btn_next);
    form1.appendChild(user_p);
	//注册模块提交按钮响应
    form1.onsubmit = function(evt){
	    evt.preventDefault();
		if($('#username').val() == '') {
		   alert('用户名不能为空！');
		   form1.reset();
		} else {
		  if($('#password').val() == $('#confirm').val()) {
		    //向后台提交
			var dataSend = 'username=' + this.querySelector("#username").value
			             + '&password=' + this.querySelector("#password").value;
			$.post('../../AiMin/user/login',dataSend,regProcess);
		  }else {
		    alert('两次密码不一致！');
			form1.reset();
		  }
		}
	};
	//注册回调函数
	function regProcess(data){
	    var regMessage = '';
	    $.each(data, function(name, value){
		    switch (name) {
			    case 'errorCode':
				    if(value == 0) {
					    regMessage += '注册成功！'
					}else if(value == 1){
					    regMessage += '注册失败！'
					}else if(value == 2){
					    regMessage += '后台异常！'
					}
					break;
				case 'errorMsg':
				    regMessage += value;
					break;
				default:
				    regMessage += 'default!';
				    break;
			}
		});
		form1.reset();
		alert(regMessage);
		user_currentPage = 0;
		var dataSend = 'page=' + user_currentPage;
		$.get('../../AiMin/user/query',dataSend,regCallBack);
		
	}
	//删除操作
	var delete_btn = form1.querySelectorAll('.user_delete');
	for(var i=0; i<5; i++) {
	  delete_btn[i].onclick = function(){
	    var con = window.confirm('确定删除该用户？');
		if(con) {
			var dataSend = 'username=' + this.parentNode.previousSibling.innerHTML;
			$.get('../../AiMin/user/delete',dataSend,deleteCallBack);
		}
	  };
	}
	function deleteCallBack(data){
		if(data.errorCode == 0) {
			var dataSend = 'page=' + user_currentPage;
    		$.get('../../AiMin/user/query',dataSend,regCallBack);
		}
	}
	//注册页面更新回调
	function regCallBack(data){
		//清零
		var trs = form1.querySelector('table').children[1].querySelectorAll('tr');
		for(var i=0; i<5; i++) {
			trs[i].children[0].innerHTML = '';
			trs[i].children[1].querySelectorAll('input')[0].style.display = "none";
			trs[i].children[1].querySelectorAll('input')[1].style.display = "none";
		}
		//显示
		if(data.errorCode == 0) {
			var len = data.errorMsg;
			//最大页数
			user_maxPage = Math.floor(data.errorMsg/5);
			if((data.errorMsg%5) == 0)
				user_maxPage -= 1;
			var disLen = (data.param.length < 5) ? data.param.length : 5;
			for(var j=0; j<disLen; j++) {
				trs[j].children[0].innerHTML = data.param[j].username;
				trs[j].children[1].querySelectorAll('input')[0].style.display = "";
				trs[j].children[1].querySelectorAll('input')[1].style.display = "";
			}
		}
	}
	//修改密码操作
	var edit_btn = form1.querySelectorAll('.user_edit');
	for (var i=0; i<5; i++) {
		edit_btn[i].onclick = function(){
			var newPassword ='';
			newPassword = window.prompt("请输入新密码","");
			if(newPassword!= null){
				//发送新密码
				var dataSend = 'username=' + this.parentNode.previousSibling.innerHTML
				             + '&password=' + newPassword;
				$.get('../../AiMin/user/edit', dataSend, editCallBack);
			}
		};
	}
	var editCallBack = function(data){
		if(data.errorCode == 0) {
			alert('密码修改成功！');
		}
	};
	//用户管理上下翻页
    btn_pre.onclick = function(){
    	if(user_currentPage == 0) {
    		
    	} else {
    		user_currentPage --;
    		var dataSend = 'page=' + user_currentPage;
    		$.get('../../AiMin/user/query',dataSend,regCallBack);
    		
    	}
    };
    btn_next.onclick = function(){
    	if(user_currentPage == user_maxPage) {
    		
    	} else {
    		user_currentPage ++;
    		var dataSend = 'page=' + user_currentPage;
    		$.get('../../AiMin/user/query',dataSend,regCallBack);  		
    	}
    };
		
	/* 手动报废 done*/
	var form2 = document.createElement('form'),
	    invP1 = document.createElement('p'),
	    invLab = document.createElement('label'),
		invInp = document.createElement('input'),
		invP2 = document.createElement('p'),
		invBtn = document.createElement('input');
		//手动报废页面生成
	form2.id = 'invalid';
	invLab.appendChild(document.createTextNode('报废条码'));
	invLab.htmlFor = 'invInp';
	invInp.type = 'text';
	invInp.id = 'invInp';
	invInp.name = 'invInp';
	invP1.appendChild(invLab);
	invP1.appendChild(invInp);
	invBtn.type = 'submit';
	invBtn.id = 'invBtn';
	invBtn.name = 'invBtn';
	invBtn.value = 'Submit';
	invP2.appendChild(invBtn);
	form2.appendChild(invP1);
	form2.appendChild(invP2);
	
	//$('#invInp').live("onkeyup", function(){
	//	this.value=this.value.replace(/\D/g,'');
	//});
	//手动报废页面提交按钮响应
	form2.onsubmit = function(evt){
	    evt.preventDefault();
	    var data1 = document.getElementById('invInp').value;
	    var data2 = data1.replace(/\D/g,'');
	    if(data1 != data2) {
	    	alert("输入有误，只能输入数字！");
	    	document.getElementById('invInp').value = "";
	    } else {
		    var formData ="data=['" + data1 + "']";
			$.post('../../AiMin/label/scrap',formData,invalidProcess,"json");
	    }	
	};
	function invalidProcess(data){
	    var invalidMsg = '';
	    $.each(data, function(name, value){
		    switch (name) {
			    case 'errorCode':
				    if(value == 0) {
					    invalidMsg += '上传成功！'
					}else if(value == 1){
					    invalidMsg += '上传失败！'
					}else if(value == 2){
					    invalidMsg += '后台异常！'
					}
					break;
				case 'errorMsg':
				    invalidMsg += value;
					break;
				default:
				    invalidMsg += 'default!';
				    break;
			}
		});
		form2.reset();
		alert(invalidMsg);
	}
	
	/* 手动激活 done*/
	var form3 = document.createElement('form'),
	    valiP1 = document.createElement('p'),
	    valiLab1 = document.createElement('label'),
		valiInp1 = document.createElement('input'),

		valiBtn = document.createElement('input');
	    valiP3 = document.createElement('p');
		//手动激活页面生成
	form3.id = 'valid';
	valiLab1.appendChild(document.createTextNode('激活条码'));
	valiLab1.htmlFor = 'valiInp1';
	valiInp1.type = 'text';
	valiInp1.id = 'valiInp1';
	valiInp1.name = 'valiInp1';
	valiP1.appendChild(valiLab1);
	valiP1.appendChild(valiInp1);  //p1
	valiBtn.type = 'submit';
	valiBtn.id = 'valiBtn';
	valiBtn.name = 'valiBtn';
	valiBtn.value = 'Submit';
	valiP3.appendChild(valiBtn);
	form3.appendChild(valiP1);
	form3.appendChild(valiP3);
	  //手动激活页面响应
	form3.onsubmit = function(evt){
	    evt.preventDefault();
	    var data1 = document.getElementById('valiInp1').value;
	    var data2 = data1.replace(/\D/g,'');
	    if(data1 != data2) {
	    	alert("输入有误，只能输入数字！");
	    	document.getElementById('valiInp1').value = "";
	    } else {
		    var formData = "data=['" + data1 + "']";
			$.post('../../AiMin/label/bind',formData,validProcess,"json");
	    }			
	};
	function validProcess(data){
	    var validMsg = '';
	    $.each(data, function(name, value){
	    switch (name) {
			    case 'errorCode':
				    if(value == 0) {
					    validMsg += '激活成功！'
					}else if(value == 1){
					    validMsg += '激活失败！'
					}else if(value == 2){
					    validMsg += '后台异常！'
					}
					break;
				case 'errorMsg':
				    validMsg += value;
					break;
				default:
				    validMsg += 'default!';
				    break;
			}
		});
		form3.reset();
		alert(validMsg);
	}
	
	/* 计时启停 */
	var timingP = document.createElement('p');
	timingP.id = 'timingP';
	var stopText = document.createTextNode('计时已停止...');
	var startText = document.createTextNode('计时已启动...');
	
	/* 查询条件 */
	//查询页面归零
	function toZero(){
		JSONData.page = 0;
		currentPage = 0;
		maxPage = 0;
	}
	//更改页码 done
	document.getElementById('pageSel').onchange = function(evt){
	    var index = pageSel.selectedIndex;
		var value = pageSel.options[index].value;
		currentPage = value;
		JSONData.page = currentPage;
		var JSONSend = changeString(JSONData);
		sendQuery(JSONSend);
	};
	//翻页 done
	document.getElementById('btnPre').onclick = function(evt) {
	    if(currentPage == 0) {
		} else {
		    //发送请求
		    currentPage --;
			JSONData.page = currentPage;
			var JSONSend = changeString(JSONData);
			sendQuery(JSONSend);
		}
	}
	document.getElementById('btnNext').onclick = function(evt) {
	    if(currentPage == maxPage) {
		} else {
		    //发送请求
		    currentPage ++;
			JSONData.page = currentPage;
			var JSONSend = changeString(JSONData);
			sendQuery(JSONSend);
		}
	}
	//是否报废 done
	document.getElementById('baofei').onchange = function(evt){
	    var check = document.getElementById('baofei');
		if(check.children[0].checked) {
		  JSONData.alive = 1;
		  toZero(); //归零查询页面 最大页面等信息
		  var JSONSend = changeString(JSONData);
		  sendQuery(JSONSend);
		}else {
		  JSONData.alive = 0;
		  toZero(); //归零查询页面 最大页面等信息
		  var JSONSend = changeString(JSONData);
		  sendQuery(JSONSend);
		}
	};
	//是否正在使用 done
	document.getElementById('inuse').onchange = function(evt){
	    var check = document.getElementById('inuse');
		if(check.children[0].checked) {
		  JSONData.inuse = 1;
		  toZero(); //归零查询页面 最大页面等信息
		  var JSONSend = changeString(JSONData);
		  sendQuery(JSONSend);		  
		}else {
		  JSONData.inuse = 0;
		  toZero(); //归零查询页面 最大页面等信息
		  var JSONSend = changeString(JSONData);
		  sendQuery(JSONSend);	
		}
	};
	//空调号选择
	document.getElementById('adId').onchange = function(evt){
	    var check = document.getElementById('adId');
		if(check.children[0].checked) {
		  JSONData.ac_id = '01';
		  toZero(); //归零查询页面 最大页面等信息
		  var JSONSend = changeString(JSONData);
		  sendQuery(JSONSend);			  
		} else if(check.children[1].checked) {
		  JSONData.ac_id = '02';
		  toZero(); //归零查询页面 最大页面等信息
		  var JSONSend = changeString(JSONData);
		  sendQuery(JSONSend);
		} else {
		  JSONData.ac_id = '03';
		  toZero(); //归零查询页面 最大页面等信息
		  var JSONSend = changeString(JSONData);
		  sendQuery(JSONSend);
		}
	};
	//剩余清洗次数 done
	document.getElementById('washRemain').onchange = function(evt){
	    var check = document.getElementById('washRemain');
		var index = check.selectedIndex;
		var value = check.options[index].value;
		JSONData.washRemain = value;
		toZero(); //归零查询页面 最大页面等信息
		var JSONSend = changeString(JSONData);
		sendQuery(JSONSend);
	};
	//剩余时间 done
	document.getElementById('timeRemain').onchange = function(evt){
	    var check = document.getElementById('timeRemain');
		var index = check.selectedIndex;
		var value = check.options[index].value;
		JSONData.aliveTime = value;
		toZero(); //归零查询页面 最大页面等信息
		var JSONSend = changeString(JSONData);
		sendQuery(JSONSend);
	};
	
	/* 查询请求 undefined*/ 
	function sendQuery(jsonData){
	    $.get('../../AiMin/label/query',jsonData,contentDisplay);
	}
	//回调显示函数
	function contentDisplay(data){
		var jsData = data;
		//清零
		var table1 = document.getElementById('table1');
		for(var i=0; i<9; i++) {
		  for(var j=0; j<6; j++) {
		    table1.children[0].children[i+1].children[j].innerHTML = '';
		  }
		}
	    //var jsData = JSON.parse(data);
		if(jsData.errorCode == 0) { //操作成功显示查询信息
		    //显示页码信息
		    maxPage = Math.floor(jsData.errorMsg / 9);
		    if((jsData.errorMsg % 9) == 0)
		    	maxPage -= 1;
		    document.getElementById('pageSel').innerHTML = '';
			for(var i=0; i<=maxPage; i++) {
			  var opt = document.createElement('option');
			  opt.value = i;
			  opt.innerHTML = i+1;
			  document.getElementById('pageSel').appendChild(opt);
			}
			pageSel.options[currentPage].selected = true;
			var itemNum = jsData.param.length;
			var table1 = document.getElementById('table1');
			for(var k=0; k<itemNum; k++) {
			  table1.children[0].children[k+1].children[0].innerHTML = jsData.param[k].id;
			  table1.children[0].children[k+1].children[1].innerHTML = jsData.param[k].id.substring(0,2);
              table1.children[0].children[k+1].children[2].innerHTML = jsData.param[k].washing_count;
			  table1.children[0].children[k+1].children[3].innerHTML = jsData.param[k].cumulative_time;
			  table1.children[0].children[k+1].children[4].innerHTML = jsData.param[k].inuse;
			  table1.children[0].children[k+1].children[5].innerHTML = jsData.param[k].alive;
			}
		}else {
			document.getElementById('pageSel').innerHTML = '';
			  var opt = document.createElement('option');
			  opt.value = 1;
			  opt.innerHTML = 1;
			  document.getElementById('pageSel').appendChild(opt);
			currentPage = 0;
			maxPage = 0;
			JSONData.page = currentPage;
		}
	}
	
	/* 计时启停回调 */
	function timingCallBack(data) {
		if(data.errorCode == 0) {
			var bool = window.confirm('当前计时开启，是否关闭计时');
			if(bool) {
				$.get('../../AiMin/timing/stop','',timeOptCallBack0,'json');
			}else {}
		}else if(data.errorCode == 1) {
			var bool = window.confirm('当前计时关闭，是否开启计时');
			if(bool) {
				$.get('../../AiMin/timing/start','',timeOptCallBack,'json');
			} else {}
		}
	}
	function timeOptCallBack0(data) {
		if(data.errorCode == 0) {
			alert('计时已关闭');
		} else if(data.errorCode == 0) {
			alert('计时关闭失败');
		}
	}
	function timeOptCallBack(data) {
		if(data.errorCode == 0) {
			alert('计时已开始');
		} else if(data.errorCode == 1) {
			alert('计时启动失败');
		}
	}
	/* 按钮点击 */
    $('#navigation li').click(function(evt){
	    evt.preventDefault();
	    
	    var url = this.children[0].getAttribute('href');
		//计时启停按钮
		if(url == 'start') {
			$.get('../../AiMin/timing/query','',timingCallBack,'json');
		}else if(url == 'register'){//注册按钮
			content.innerHTML = '';			
			document.getElementById('content').appendChild(form1);
			$.get('../../AiMin/user/query','page=0',regCallBack);
		}else if(url == 'invalid'){//手动报废
			content.innerHTML = '';			
			document.getElementById('content').appendChild(form2);
		}else if(url == 'valid') {//手动激活
		    content.innerHTML = '';			
			document.getElementById('content').appendChild(form3);
		}else if(url == 'logout') {
		    $.get('../../AiMin/user/logout');
			window.location.href = 'login.html';
		}else if(url == 'inqury') {
		    content.innerHTML = '';
			pageSel.innerHTML = '';
			content.appendChild(defcon);
			currentPage = 0;
			maxPage = 0;
			JSONData.aliveTime = 0;
			JSONData.washRemain = 0;
			JSONData.inuse = 1;
			JSONData.alive = 1;
			JSONData.ac_id = '01';
			JSONData.page = 0;
			//清零
			var table1 = document.getElementById('table1');
			for(var i=0; i<9; i++) {
			  for(var j=0; j<6; j++) {
			    table1.children[0].children[i+1].children[j].innerHTML = '';
			  }
			}
			var JSONSend = changeString(JSONData);
            sendQuery(JSONSend);
		}
	});
	$('#navigation a[href="inqury"]').click();
});
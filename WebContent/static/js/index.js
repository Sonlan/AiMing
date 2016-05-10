$(document).ready(function() {

    var content = document.getElementById('content');

	/* 注册模块 */
	var form1 = document.createElement('form');
	form1.id = 'register';	
	var p1 = document.createElement('p');
	var p2 = document.createElement('p');
	var p3 = document.createElement('p');
	var p4 = document.createElement('p');
	var username = document.createElement('input');
	username.type = 'text';
	username.name = 'username';
	username.id = 'username';
	var label1 = document.createElement('label');
	var label1text = document.createTextNode('用户名：');
	label1.htmlFor = 'username';
	label1.appendChild(label1text);			
	p1.appendChild(label1);
	p1.appendChild(username);
	var password = document.createElement('input');
	password.type = 'password';
	password.name = 'password';
	password.id = 'password';
	var label2 = document.createElement('label');
	var label2text = document.createTextNode('密码：');
	label2.htmlFor = 'password';
	label2.appendChild(label2text);			
	p2.appendChild(label2);
	p2.appendChild(password);
	var confirm = document.createElement('input');
	confirm.type = 'password';
	confirm.name = 'confirm';
	confirm.id = 'confirm';
	var label3 = document.createElement('label');
	var label3text = document.createTextNode('确认密码：');
	label3.htmlFor = 'confirm';
	label3.appendChild(label3text);			
	p3.appendChild(label3);
	p3.appendChild(confirm);
	var submit = document.createElement('input');
	submit.type = 'submit';
	submit.id = 'regBtn';
	submit.name = 'submit';
	submit.value = 'Submit';
	p4.appendChild(submit);
	form1.appendChild(p1);
	form1.appendChild(p2);
	form1.appendChild(p3);
	form1.appendChild(p4);
	
    form1.onsubmit = function(evt){
	    evt.preventDefault();
		if($('#username').val() == '') {
		   alert('用户名不能为空！');
		   form1.reset();
		} else {
		  if($('#password').val() == $('#confirm').val()) {
		    //向后台提交
			var formData = $('#register').serialize();
			$.post('../../AiMing/user/login',formData,regProcess);
		  }else {
		    alert('两次密码不一致！');
			form1.reset();
		  }
		}
	};
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
	}
	
	/* 手动报废 */
	var form2 = document.createElement('form');
	form2.id = 'invalid';
	var invP1 = document.createElement('p');
	var invLab = document.createElement('label');
	var invLabText = document.createTextNode('报废条码');
	invLab.appendChild(invLabText);
	invLab.htmlFor = 'invInp';
	var invInp = document.createElement('input');
	invInp.type = 'text';
	invInp.id = 'invInp';
	invInp.name = 'invInp';
	invP1.appendChild(invLab);
	invP1.appendChild(invInp);
	var invP2 = document.createElement('p');
	var invBtn = document.createElement('input');
	invBtn.type = 'submit';
	invBtn.id = 'invBtn';
	invBtn.name = 'invBtn';
	invBtn.value = 'Submit';
	invP2.appendChild(invBtn);
	form2.appendChild(invP1);
	form2.appendChild(invP2);//手动报废
	form2.onsubmit = function(evt){
	    evt.preventDefault();
		var formData = $('#invalid').serialize();
		$.post('../../AiMing/label/scrap',formData,invalidProcess);	
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
	
	/* 手动激活 */
	var form3 = document.createElement('form');
	form3.id = 'valid';
	var valiP1 = document.createElement('p');
	var valiLab1 = document.createElement('label');
	var valiLab1Text = document.createTextNode('激活条码');
	valiLab1.appendChild(valiLab1Text);
	valiLab1.htmlFor = 'valiInp1';
	var valiInp1 = document.createElement('input');
	valiInp1.type = 'text';
	valiInp1.id = 'valiInp1';
	valiInp1.name = 'valiInp1';
	valiP1.appendChild(valiLab1);
	valiP1.appendChild(valiInp1);  //p1
	var valiP2 = document.createElement('p');
	var select = document.createElement('select');
	select.id = 'select';
	var opt1 = document.createElement('option');
	opt1.value = 0;
	var opt1Text = document.createTextNode('0 初级滤芯');
	opt1.appendChild(opt1Text);
	var opt2 = document.createElement('option');
	opt2.value = 1;
	var opt2Text = document.createTextNode('1 中级滤芯');
	opt2.appendChild(opt2Text);
	select.appendChild(opt1);
	select.appendChild(opt2);
	var selectLab = document.createElement('label');
	selectLab.htmlFor = 'select';
	var selectText = document.createTextNode('滤芯类型');
	selectLab.appendChild(selectText);
	valiP2.appendChild(selectLab);
	valiP2.appendChild(select);  //p2 select
	var valiBtn = document.createElement('input');
	valiBtn.type = 'submit';
	valiBtn.id = 'valiBtn';
	valiBtn.name = 'valiBtn';
	valiBtn.value = 'Submit';
	form3.appendChild(valiP1);
	form3.appendChild(valiP2);
	form3.appendChild(valiBtn);//激活
	form3.onsubmit = function(evt){
	    evt.preventDefault();
		var formData = $('#valid').serialize();
		$.post('../../AiMing/label/bind',formData,validProcess);			
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

	/* 按钮点击 */
    $('#navigation a').click(function(evt){
	    evt.preventDefault();
	    var url = $(this).attr('href');
		if(url == 'start') {
		    if($(this).text() == '停止计时') {
			  $(this).text('开始计时');
			  content.innerHTML = '';		
              timingP.innerHTML = '';
              timingP.appendChild(stopText);				  
			  document.getElementById('content').appendChild(timingP);
			  $.get('../../AiMing/timing/stop');
			  
			}else {
			  $(this).text('停止计时');
			  content.innerHTML = '';		
              timingP.innerHTML = '';
              timingP.appendChild(startText);			  
			  document.getElementById('content').appendChild(timingP);
			  $.get('../../AiMing/timing/start');
			}
		}else if(url == 'register'){//注册
			content.innerHTML = '';			
			document.getElementById('content').appendChild(form1);
		}else if(url == 'invalid'){//手动报废
			content.innerHTML = '';			
			document.getElementById('content').appendChild(form2);
		}else if(url == 'valid') {
		    content.innerHTML = '';			
			document.getElementById('content').appendChild(form3);
		}else if(url == 'logout') {
		    $.get('../../AiMing/user/logout');
		}
	});


});
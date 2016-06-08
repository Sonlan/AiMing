$(document).ready(function(){

    //批量激活
	function validProcess(data){
	    $('div#validControl input').val("");
	    alert("OK");
	    if(data.errorCode == 0) {
		    alert('激活成功！');
		} else if (data.errorCode == 1) {
		    alert('激活失败！');
		} else if (data.errorCode == 2) {
		    alert('后台异常！');
		}
	}

    $('div#validControl button').bind('click',function(){
	    var dataSend = $('div#validControl input').val();
	    alert(dataSend);
		$.post('../../aimin/label/bind',"data=["+dataSend+"]",validProcess,"json");
	});


    //用户管理操作
	$('a[href="password"]').bind('click',function(evt){
	    evt.preventDefault();
		var passwordNew = window.prompt("输入新密码");
		//修改密码
	});
	$('a[href="logout"]').bind('click',function(evt){
	    evt.preventDefault();
	    var conf = window.confirm("是否退出登录？");
		if(conf) {
		    $.get('../../aimin/user/logout');
		} else {
		}
	});
});
$(document).ready(function(){

    function invalidProcess(){
	    $('div#invalidControl input').val("");
	    if(data.errorCode == 0) {
		    alert('报废成功！');
		} else if (data.errorCode == 1) {
		    alert('报废失败！');
		} else if (data.errorCode == 2) {
		    alert('后台异常！');
		}
	}
    //点击提交
	$('div#invalidControl button').bind('click',function(){
		var dataSend = $('div#invalidControl input').val();
		$.post('../../aimin/label/scrap',dataSend,invalidProcess,"json");
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
			window.location.href = 'login.html';
		} else {
		}
	});
});
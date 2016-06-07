$(document).ready(function(){
    
	function loginProcess(data){
	    if(data.errorCode == 0) {
		    window.location.href = data.param;
		} else {
		    alert('登录失败！');
			$('form#loginForm input#username').val('');
			$('form#loginForm input#password').val('');
		}
	}
	
	$('form#loginForm button').bind('click',function(evt){
	    evt.preventDefault();
		var dataSend = 'username=' + $('form#loginForm input#username').val()
		             + '&password=' + $('form#loginForm input#password').val();
		
		$.get('../../aimin/user/logon', dataSend, loginProcess);
	});
});
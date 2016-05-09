$(document).ready(function() {
    $('#navigation a').click(function(evt){
	    evt.preventDefault();
	    var url = $(this).attr('href');
		if(url == 'start') {
		    if($(this).text() == '停止计时') {
			  $(this).text('开始计时');
			}else {
			  $(this).text('停止计时');
			}
		}else{
		    $('#content').load(url);
		}
	});
});
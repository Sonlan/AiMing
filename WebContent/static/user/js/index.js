$(document).ready(function(){

    var 
	maxPage = 0,  //最大页数
	currentPage = 0,  //当前页数
	JSONData = {                    //查询请求JSON
		aliveTime_day : 1,  //剩余时间
		aliveTime_hour : 0,  
		aliveTime_min : 0,
		level : 0,  //滤芯等级 0 初级
		washRemain : 0,  //剩余清洗次数
		inuse : 1,  //是否使用,正在使用
		alive : 1,  //是否报废,未报废
		ac_id : '01',  //空调号
		page : 0  //当前页面
	};
	
	//构造发送串
	function changeString(){
	    var dataSend = "";

		JSONData.alive = $('div#inquiryStatus input[name="in-valid"]:checked').attr('value');
		JSONData.inuse = $('div#inquiryStatus input[name="in-use"]:checked').attr('value');
		JSONData.ac_id = $('select#selectAIRId option:selected').attr('value');
		JSONData.level = $('div#inquiryStatus input[name="filter-class"]:checked').attr('value');
		JSONData.washRemain = $('select#selectWashRemain option:selected').attr('value');
		JSONData.aliveTime_day = $('select#selectRemainDay option:selected').attr('value');
		JSONData.aliveTime_hour = $('select#selectRemainHour option:selected').attr('value');
		JSONData.aliveTime_min = $('select#selectRemainMin option:selected').attr('value');
		
		dataSend = 'aliveTime_day=' + JSONData.aliveTime_day
			 + '&aliveTime_hour=' + JSONData.aliveTime_hour
			 + '&aliveTime_min=' + JSONData.aliveTime_min
			 + '&washRemain=' + JSONData.washRemain
			 + '&level=' + JSONData.level
			 + '&inuse=' + JSONData.inuse
			 + '&alive=' + JSONData.alive
			 + '&ac_id=' + JSONData.ac_id
			 + '&page=' + JSONData.page;
			 
		return dataSend;
	}
	
	//查询回调函数
	function indexInquiryProcess(data){
		//清零
		var table = document.getElementById('mainTable');
		for(var i=0; i<9; i++) {
		  for(var j=0; j<7; j++) {
		    table.children[0].children[i+1].children[j].innerHTML = '';
		  }
		}

		if(data.errorCode == 0) { //操作成功显示查询信息
		    //显示页码信息
			alert(data.errorMsg);
			var Msg = eval ("(" + data.errorMsg + ")");
			alert(Msg.pageSize);
		    maxPage = Math.floor(Msg.pageSize / 9);
		    if((data.errorMsg % 9) == 0)
		    	maxPage -= 1;

			var itemNum = data.param.length;  //本次返回条目数
			for(var k=0; k<itemNum; k++) {//具体内容待定
			  table.children[0].children[k+1].children[0].innerHTML = data.param[k].id;  //滤芯id
			  if(data.param[k].id.substring(2,3) == '0') {
				  table.children[0].children[k+1].children[1].innerHTML = "初级滤芯";
			  }else if(data.param[k].id.substring(2,3) == '1') {
				  table.children[0].children[k+1].children[1].innerHTML = "中级滤芯";
			  }
              table.children[0].children[k+1].children[2].innerHTML = data.param[k].id.toString().substring(0,2);
			  table.children[0].children[k+1].children[3].innerHTML = data.param[k].washRemain;
			  table.children[0].children[k+1].children[4].innerHTML = data.param[k].aliveTime;
			  table.children[0].children[k+1].children[5].innerHTML = (data.param[k].inuse == 1) ? '正在使用' : '未使用';
			  table.children[0].children[k+1].children[6].innerHTML = (data.param[k].alive == 1) ? '未报废' : '已报废';
			}
		}else {

		}
	}
	
	//计时查询
	function timingStopProcess(data){
		if(data.errorCode == 0) {
		    $('p#timingStatus').html("当前状态：停止计时");
		}else if(data.errorCode == 1) {
		}
	}
	function timingStartProcess(data){
		if(data.errorCode == 0) {
			$('p#timingStatus').html("当前状态：开始计时");
		}
	}
	function timingQueryProcess(data){
		if(data.errorCode == 0) {
			$('p#timingStatus').html("当前状态：开始计时");
		}else if(data.errorCode == 1) {
			$('p#timingStatus').html("当前状态：停止计时");
		}
	}
	
	//计时操作 
	$('button#stopTiming').bind('click',function(evt){
	    $.get('../../aimin/timing/stop','',timingStopProcess,'json');
	});
	$('button#startTiming').bind('click',function(evt){
	    $.get('../../aimin/timing/start','',timingStartProcess,'json');
	});
	
	//翻页操作
	$('li>a[href="previous"]').bind('click',function(evt){
	    evt.preventDefault();
		if(currentPage == 0) {
		} else {
		  //发送请求
			currentPage --;
			JSONData.page = currentPage;
			var dataSend = changeString();
			$.get('../../aimin/label/query',sendData,indexInquiryProcess);
		}
	});
	$('li>a[href="next"]').bind('click',function(evt){
	    evt.preventDefault();
		if(currentPage == maxPage) {
		} else {
		  //发送请求
			currentPage ++;
			JSONData.page = currentPage;
			var JSONSend = changeString(JSONData);
			sendQuery(JSONSend);
		}
	});

    //提交查询
    $('div#inquiryStatus button[type="submit"]').bind('click',function(evt){
	    evt.preventDefault();
		var sendData = changeString();	
	    $.get('../../aimin/label/query',sendData,indexInquiryProcess);
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
	
	//初始页面查询
	function defaultInquiry(){
	    var dataSend = changeString();
		$.get('../../aimin/label/query',dataSend,indexInquiryProcess);
	}
	
	defaultInquiry();
	$.get('../../aimin/timing/query','',timingQueryProcess,'json');
	//空调动态查询
});
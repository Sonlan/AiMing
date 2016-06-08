$(document).ready(function(){

    var 
	maxPage = 0,  //最大页数
	currentPage = 0,  //当前页数
	
	maxDay_prim = 0;
    maxHour_prim = 0;
    maxMin_prim = 0;
    maxWash_prim = 0;  //最大清洗次数
	maxDay_mid = 0;
    maxHour_mid = 0;
    maxMin_mid = 0;
    maxWash_mid = 0;  //最大清洗次数
    maxAirNum = 0;  //最大空调数
    refreshRate = 0;  //刷新周期
    
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
	
	//剩余清洗次数刷新
	function refreshWashRemain(maxWash){
		$('select#selectWashRemain option:gt(0)').remove();
		for(var i = 0; i < maxWash + 1; i ++) {
			var opt = "<option value=" + i + ">" + i + "</option>";
			$('select#selectWashRemain').append(opt);
		}
	}
	
	//空调号查询
	function refreshAirNum(maxAir) {
		$('select#selectAIRId option').remove();
		for(var i = 0; i < maxAir; i ++) {
			var opt = "<option value=" + i + ">" + (i+1) + "</option>";
			$('select#selectAIRId').append(opt);
		}
	}
	
	//清洗时间
	function refreshTime(day) {
		$('select#selectRemainDay option').remove();
		for(var i = 0; i <= day; i ++) {
			var opt = "<option value=" + i + ">" + i + "</option>";
			$('select#selectRemainDay').append(opt);
		}
	}
	
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
            table.children[0].children[i+1].children[0].innerHTML = '';    
		}
	  $('input.input_air').val('').hide();
	  $('input.input_wash').val('').hide();
	  $('input.input_time').val('').hide();
	  $('select.select_filter').hide();
	  $('select.select_inuse').hide();
	  $('select.select_invalid').hide();
	  $('button.buttonEdit').hide();
	  $('button.buttonDone').hide();
		

		if(data.errorCode == 0) { //操作成功显示查询信息
		    //显示页码信息
			alert(data.errorMsg);
			//data.errorMsg转化为JSON对象
			var msg = eval ("(" + data.errorMsg + ")");
			//剩余时间信息
			var timeMsg_prim = msg.limitTime[0].split('-');
			maxDay_prim = timeMsg_prim[0];
			maxHour_prim = timeMsg_prim[1];
			maxMin_prim = timeMsg_prim[2];
			var timeMsg_mid = msg.limitTime[1].split('-');
			maxDay_mid = timeMsg_mid[0];
			maxHour_mid = timeMsg_mid[1];
			maxMin_mid = timeMsg_mid[2];
			
			//清洗次数信息
			maxWash_prim = msg.washCountLimit[0];
			maxWash_mid = msg.washCountLimit[1];
			
			//空调数
			maxAirNum = msg.ac_ids.length;
			
			//刷新周期
			refreshRate = msg.rate;
			
			
		    maxPage = Math.floor(msg.pageSize / 9);
		    if((data.errorMsg % 9) == 0)
		    	maxPage -= 1;
		    
		    /* 更新查询项 */
		    // 清洗次数及时间
		    //初级
		    if($('input[name="filter-class"]:checked').attr('value') == 0) {
		    	refreshWashRemain(maxWash_prim);
		    	refreshTime(maxDay_prim);
		    }else if($('input[name="filter-class"]:checked').attr('value') == 1) {
		    	refreshWashRemain(maxWash_mid);
		    	refreshTime(maxDay_mid);
		    }
		    // 空调号查询 
		    refreshAirNum(maxAirNum);

			var itemNum = data.param.length;  //本次返回条目数

			for(var k=0; k<itemNum; k++) {
			  $('.select_filter:eq(' + k + ')').show().attr('disabled', 'disabled');
			  $('.select_invalid:eq(' + k + ')').show().attr('disabled', 'disabled');
			  $('.select_inuse:eq(' + k + ')').show().attr('disabled', 'disabled');
			  $('button.buttonEdit:eq(' + k + ')').show();
			  $('button.buttonDone:eq(' + k + ')').show();
			  table.children[0].children[k+1].children[0].innerHTML = data.param[k].id;  //滤芯id
			  //滤芯等级
			  (data.param[k].id.substring(2,3) == '0') ?
					                     ($('.select_filter:eq(' + k + ') option:eq(0)').attr('selected','selected')) : 
		                                 ($('.select_filter:eq(' + k + ') option:eq(1)').attr('selected','selected'));
				                                    
              $('input.input_air:eq(' + k + ')').val(data.param[k].id.toString().substring(0,2)).show().attr('disabled', 'disabled');
              $('input.input_wash:eq(' + k + ')').val(data.param[k].washRemain).show().attr('disabled', 'disabled');
              $('input.input_time:eq(' + k + ')').val(data.param[k].aliveTime).show().attr('disabled', 'disabled');

			  (data.param[k].inuse == 1) ? 
					                     ($('.select_inuse:eq(' + k + ') option:eq(0)').attr('selected','selected')) : 
					                     ($('.select_inuse:eq(' + k + ') option:eq(1)').attr('selected','selected'));
			  
			  (data.param[k].alive == 1) ? 
					                     ($('.select_invalid:eq(' + k + ') option:eq(1)').attr('selected','selected')) : 
					                     ($('.select_invalid:eq(' + k + ') option:eq(0)').attr('selected','selected'));
			}
		}else {

		}
	}
	
	//表单编辑
	$('button.buttonEdit').bind('click', function(evt){
		var conf = window.confirm('数据将被修改，是否继续？');
		if(conf) {
			$('button.buttonDone').click();
			$(this).parent().parent().children('td:eq(1)').children('select').attr('disabled', false);
			$(this).parent().parent().children('td:eq(2)').children('input').attr('disabled', false);
			$(this).parent().parent().children('td:eq(3)').children('input').attr('disabled', false);
			$(this).parent().parent().children('td:eq(4)').children('input').attr('disabled', false);
			$(this).parent().parent().children('td:eq(5)').children('select').attr('disabled', false);
			$(this).parent().parent().children('td:eq(6)').children('select').attr('disabled', false);
		}else {
			
		}
	});
	$('button.buttonDone').bind('click', function(evt){
		$(this).parent().parent().children('td:eq(1)').children('select').attr('disabled', 'disabled');
		$(this).parent().parent().children('td:eq(2)').children('input').attr('disabled', 'disabled');
		$(this).parent().parent().children('td:eq(3)').children('input').attr('disabled', 'disabled');
		$(this).parent().parent().children('td:eq(4)').children('input').attr('disabled', 'disabled');
		$(this).parent().parent().children('td:eq(5)').children('select').attr('disabled', 'disabled');
		$(this).parent().parent().children('td:eq(6)').children('select').attr('disabled', 'disabled');
	});
	
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
	
	/* 操作 */
	$('select.select_invalid').bind('focus', function(evt){
		var conf = window.confirm('数据将被改变！是否继续？');
		if(!conf) {

		} 
	});
	$('td.td_invalid').bind('dblclick', function(evt){
	});
	
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
	
	//滤芯类型选择
	$('input[name="filter-class"]').bind('change',function(evt){
		if($(this).val() == 0) {//初级滤芯
			refreshWashRemain(maxWash_prim);
		}else if($(this).val() == 1) {//中级滤芯
			refreshWashRemain(maxWash_mid);
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
		//查询计时功能
		$.get('../../aimin/timing/query','',timingQueryProcess,'json');
	}
	
	defaultInquiry();
	
	//空调动态查询
});
layui.use(['form','layer','table','laytpl','laydate'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        laydate = layui.laydate,
        table = layui.table;

	
	laydate.render({
	 	elem: '#lastcheck'
	 	,value: $("#lastcheck").val()
	 	,position: "fixed"
	 	,showBottom: false
	 	,trigger: 'click' //采用click弹出
	 	,done: function(value){ //监听日期被选中
	 		var date = new Date(value);
	 		var value = value.split("-");
	     	var day = value[2];
		    
		    $("#newcheck").val(getMonthBeforeFormatAndDay(date,$("#uselife").val(),"-",day));
		}
	});
	var olduselife = $("#opuselife").val();
	jsonstr = {"name":"检测周期"};
	loadselect(jsonstr,"uselife");
	//加载下拉框
	function loadselect(jsonstr,id){		
		$.ajax({
		    type: 'POST',
		    url: baseUrl+'/toolsManage/dictionary/getDictionary',
		    data: JSON.stringify(jsonstr),
		    contentType: "application/json; charset=UTF-8",
		    dataType : "json",
	        success: function(result) {               
	            if (result.statusCode == 200 && result.data.length>0) {
	                $.each(result.data, function(index, item) {
	                	if(id == "uselife"){
		                	if(item.display == olduselife){
		                		$('#opuselife'+item.display+'').remove();
		                		$("#opuselife").val(olduselife);
		                		$("#opuselife").text(olduselife);
		                	}else{
		                    	//$('#'+id+'').append(new Option(item.display,item.display));
		                    	if($('#opuselife'+item.display+'').val()==item.display){
		                    		$('#opuselife'+item.display+'').remove();
		                    	}
		                    	$('#'+id+'').append("<option id='opuselife"+item.display+"' value='"+item.display+"'>"+item.display+"</option>");
		                    }
		                }
	                });
	            } else {
	                $('#opuselife').text("暂无数据");
	            }
	            form.render("select");
	        }
    	});
	}
	//编辑用户信息
    form.on("submit(edit)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
        	type:"POST",
        	url:baseUrl+"/toolsManage/toolslib/updatex",
        	data:JSON.stringify({
        		id:$("#id").val(),  //id
            	unit:$("#unit").val(),  //所属单位
            	sunits:$("#sunits").val(),  //三级单位
            	tooltype:$("#tooltype").val(),  //安全工器具类别
            	groupname:$("#groupname").val(),  //所属班组
            	scale:$("#scale").val(),  //规格、电压等级
            	toolname:$("#toolname").val(),  //安全工器具名称
            	devicestate:$("#devicestate").val(),    //安全工器具状态
            	qrcode:$("#qrcode").val(),    //安全工器具代码
            	uselife:$("#uselife").val(),    //检测周期
            	lastcheck:$("#lastcheck").val(),    //上次检测时间
            	newcheck:$("#newcheck").val()
        	}),
        	contentType: "application/json; charset=UTF-8",
        	dataType : "json",
        	success : function(res){
	        	setTimeout(function(){
		            top.layer.close(index);
		            top.layer.msg("保存成功！");
		            layer.closeAll("iframe");
		            //刷新父页面
		            parent.location.reload();
	        	},1000);
        	},error : function(xhr,status,error){
        		top.layer.close(index);
        		top.layer.msg("保存失败！稍候重试");
        	}
        })  
        return false;
	})
    $("#cancel").click(function(){
    	parent.layer.close($("#index").val());
    });
		
	//检测周期下拉选中事件
  	form.on('select(uselife)', function(data){
	  	var lastcheck = $("#lastcheck").val();
		var date = new Date(lastcheck);
		var value = lastcheck.split("-");
	 	var day = value[2];
	 	var newcheck = getMonthBeforeFormatAndDay(date,data.value,"-",day);
	 	var newcheck1 = newcheck;
	 	var newdate = new Date();
	 	newdate = dateToString(newdate);
	 	// 拆分年月日
		newcheck1 = newcheck1.split('-');
		// 得到月数
		num1 = parseInt(newcheck1[0]) * 12 + parseInt(newcheck1[1]);
		// 拆分年月日
		newdate = newdate.split('-');
		// 得到月数
		num2 = parseInt(newdate[0]) * 12 + parseInt(newdate[1]);
	 	if(num1 - num2 >= 1){
			$("#newcheck").val(newcheck);
			olduselife = data.value;
		}else{
			layer.msg("最新检测日期要大于当前日期");		
			//$('#'+id+'').append("");
			loadselect(jsonstr,"uselife");
			$("[class='layui-anim layui-anim-upbit'] dd[lay-value='"+data.value+"']").attr("class","");
			$("[name='uselife'] option[value='"+olduselife+"']").attr("selected","true");
			$("[class='layui-anim layui-anim-upbit'] dd[lay-value='"+olduselife+"']").attr("class","layui-this");
			form.render('select', 'uselife');
		}
	});
	function dateToString(date){ 
	  	var year = date.getFullYear(); 
	  	var month =(date.getMonth() + 1).toString(); 
	  	var day = (date.getDate()).toString();  
	  	if (month.length == 1) { 
	      	month = "0" + month; 
	  	} 
	  	if (day.length == 1) { 
	      day = "0" + day; 
	  	}
	  	var dateTime = year + "-" + month + "-" + day;
	  	return dateTime; 
	}
	//计算最后检测日期
  	function getMonthBeforeFormatAndDay(date,num, format, day) {
            date.setMonth(date.getMonth() + (num*1), 1);
            var mo = date.getMonth();
            //小月
            if (mo == 4 || mo == 6 || mo == 9 || mo == 11) {
                if (parseInt(day) > 30) {
                    day = 30
                }
            }
            //2月
            else if (mo == 2) {
                if (isLeapYear(date.getFullYear())) {
                    if (parseInt(day) > 29) {
                        day = 29
                    } else {
                        day = 28
                    }
                }
                if (parseInt(day) > 28) {
                    day = 28
                }
            }
            //大月
            else {
                if (parseInt(day) > 31) {
                    day = 31
                }
            }

            retureValue = date.format('yyyy' + format + 'MM' + format + day);

            return retureValue;
        }

        //JS判断闰年代码
        function isLeapYear(Year) {
            if (((Year % 4) == 0) && ((Year % 100) != 0) || ((Year % 400) == 0)) {
                return (true);
            } else { return (false); }
        }

        //日期格式化
        Date.prototype.format = function (format) {
            var o = {
                "M+": this.getMonth() + 1, // month
                "d+": this.getDate(), // day
                "h+": this.getHours(), // hour
                "m+": this.getMinutes(), // minute
                "s+": this.getSeconds(), // second
                "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
                "S": this.getMilliseconds()
                // millisecond
            }

            if (/(y+)/.test(format))
                format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(format))
                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            return format;
        }
})
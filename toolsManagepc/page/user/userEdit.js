layui.use(['form','layer','laytpl','laydate'],function(){
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
    	laytpl = layui.laytpl,
        laydate = layui.laydate;

	var pid;
	jsonstr = {"name":"国网衡阳供电公司"};
	loadselect(jsonstr,"sunits");
	
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
	                	if(id == "sunits"){
	                		if($('#opsunits').text() == item.display){
	                			pid = item.code;
	                			jsonstr = {"name":"国网衡阳供电公司","pid":pid};
								loadselect(jsonstr,"groupname");
							}else{
	                    		$('#'+id+'').append(new Option(item.display,item.code));
	                    	}
	                   	}else if(id == "groupname"&&$('#opgroupname').text() != item.display){
	                   		$('#'+id+'').append(new Option(item.display,item.display));
	                   	}
	                });
	            } else {
	                $("#"+id+"").append(new Option("暂无数据", ""));
	            }
	            layui.form.render("select");
	        }
    	});
	}
	//下拉选择事件
	form.on('select(sunits)', function(data){
		$("[name='groupname'] option").remove();
		if(data.value != ""){
			var code = data.value;//得到被选中的值
		  	jsonstr = {"name":"国网衡阳供电公司","pid":code};
			loadselect(jsonstr,"groupname");
		}else{
			form.render("select");
		}
	});
	//编辑用户信息
    form.on("submit(editUser)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
        	type:"POST",
        	url:baseUrl+"/toolsManage/user/update",
        	data:JSON.stringify({
        		 id : $("#id").val(),  //登录名
	             username : $("#username").val(),  //登录名
	             pwd : $("#pwd").val(),  //密码
	             sex : data.field.sex,  //性别
	             phone : $("#phone").val(),  //手机号码
	             truename : $("#truename").val(),    //真实姓名
	             sunits : $("[class='layui-anim layui-anim-upbit'] dd[lay-value='"+data.field.sunits+"']").text(),    //所属三级单位
	             groupname : data.field.groupname,    //所属班组
	             createtime : submitTime    //添加时间
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
    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})
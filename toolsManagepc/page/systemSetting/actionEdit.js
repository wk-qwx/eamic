layui.use(['form','layer','laytpl','laydate'],function(){
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
    	laytpl = layui.laytpl,
        laydate = layui.laydate;
	
	//编辑用户信息
    form.on("submit(editAction)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
        	type:"POST",
        	url:baseUrl+"/toolsManage/action/update",
        	data:JSON.stringify({
        		 id : $("#id").val(),  //id
	             actionname : $("#actionname").val(),  //权限动作名称
	             action : $("#action").val(),  //权限代码
	             viewmode : data.field.viewmode,  //是否启用
	             actioncolumnid : $("#actioncolumnid").val(),  //分栏菜单
        	}),
        	contentType: "application/json; charset=UTF-8",
        	dataType : "json",
        	success : function(res){
	        	setTimeout(function(){
	        		top.layer.close(index);
	        		if(res.data!=null && res.statusCode == 200){			            
			            top.layer.msg("保存成功！");
			            //刷新父页面
			        	parent.location.reload();
			        }else if(res.statusCode == 400 && res.message == "请求错误"){
			        	top.layer.msg("该权限代码已存在！");
			        }else{
			        	top.layer.msg("保存失败！稍候重试");
			        }		        
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
    
})
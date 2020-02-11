layui.use(['form','layer','laytpl','laydate'],function(){
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
    	laytpl = layui.laytpl,
        laydate = layui.laydate;
	
	//编辑角色信息
    form.on("submit(editRole)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
        	type:"POST",
        	url:baseUrl+"/toolsManage/role/update",
        	data:JSON.stringify({
        		 id : $("#id").val(),  //id
	             rolename : $("#rolename").val(),  //角色名称
	             roleinfo : $("#roleinfo").val(),  //角色描述
	             createtime : $("#createtime").val()
        	}),
        	contentType: "application/json; charset=UTF-8",
        	dataType : "json",
        	success : function(res){
	        	setTimeout(function(){
	        		top.layer.close(index);
	        		if(res.data!="参数错误"){			            
			            top.layer.msg("保存成功！");
			            //刷新父页面
			        	parent.location.reload();
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
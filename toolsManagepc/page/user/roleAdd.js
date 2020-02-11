layui.use(['form','layer','laytpl','laydate'],function(){
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
    	laytpl = layui.laytpl,
        laydate = layui.laydate;
	
	//添加角色信息
    form.on("submit(addRole)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
        	type:"POST",
        	url:baseUrl+"/toolsManage/role/add",
        	data:JSON.stringify({
	             rolename : $("#rolename").val(),  //角色名称
	             roleinfo : $("#roleinfo").val(),  //角色描述
	             masterid : $("#masterid").val(),  //创建者id
	             mastername : $("#mastername").val(),  //创建者
	             createtime : submitTime
        	}),
        	contentType: "application/json; charset=UTF-8",
        	dataType : "json",
        	success : function(res){
	        	setTimeout(function(){
		            top.layer.close(index);
	        		if(res.data!="参数错误"){			            
			            top.layer.msg("角色添加成功！");
			            //刷新父页面
			        	parent.location.reload();
			        }else{
			        	top.layer.msg("角色添加失败！稍候重试");
			        }		
	        	},1000);
        	},error : function(xhr,status,error){
        		top.layer.close(index);
        		top.layer.msg("角色添加失败！稍候重试");
        	}
        })  
        return false;
	})
    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //当前时间
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})
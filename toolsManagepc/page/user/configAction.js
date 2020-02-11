layui.use(['form','layer','util','laytpl','transfer'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        transfer = layui.transfer,
        util = layui.util;
	
	let treeData=[];
	//获取数据源
	function findall() {
	    $.ajax({
	        url: baseUrl+'/toolsManage/action/getall',
	        type: "GET",
	        contentType: "application/json",
	        async: false,
	        success: function(data) {
	        	for (var i=0; i<data.data.length; i++) {
	        		var jsonObj = new Object();
	        		jsonObj.value = data.data[i].id;
	        		jsonObj.title = data.data[i].action+"("+data.data[i].actionname+")";
	        		if(data.data[i].viewmode == "否")jsonObj.disabled =true;
	        		treeData.push(jsonObj);
	        	}
	            
	        }
	    })
	    return treeData;
	}
	function initAction(){
		let actionData=[];
	    $.ajax({
	        url: baseUrl+'/toolsManage/roleaction/getRoleAction/'+$("#id").val(),
	        type: "GET",
	        contentType: "application/json",
	        async: false,
	        success: function(data) {
	        	if(data.data==null)return actionData;
	        	for (var i=0; i<data.data.length; i++) {
	        		var jsonObj = new Object();
	        		actionData.push(data.data[i].actionid);
	        	}
	            
	        }
	    })
	    return actionData;
	}
    //实例调用
	 transfer.render({
	    elem: '#action'
	    ,data: findall()
	    ,value: initAction()
	    ,id: 'action' //定义唯一索引
	    ,title: ['权限列表', $("#rolename").val()]
    	,showSearch: true
	})
	//批量办法绑定事件
	util.event('lay-demoTransferActive', {
	    getData: function(othis){
	      var getData = transfer.getData('action'); //获取右侧数据
	      var actionAdd =[];
	      var actionDel =[];
	      var initArr = initAction();	 
	      //循环判断保存的项
	      for (var i = 0; i < getData.length; i++) {
	      	var flag = "0";
	      	for(var j = 0; j < initArr.length; j++){
		      	if(getData[i].value == initArr[j]){
		      		flag = "1";
			      	continue;
		    	}
		      	
	    	}
	      	
	      	if(flag == "0"){
		      	var jsonObj = new Object();
	    		jsonObj.actionid = getData[i].value;//用户id
	    		jsonObj.roleid = $("#id").val();//角色id
	    		jsonObj.action = getData[i].title.substring(0,getData[i].title.indexOf("("));
	    		jsonObj.createtime = submitTime;    //添加时间
	    		actionAdd.push(jsonObj);
	    	}
	      }
	      //循环判断删除的项
	      for (var i = 0; i < initArr.length; i++) {
	      	var flag = "0";
	      	for(var j = 0; j < getData.length; j++){
		      	if(getData[j].value == initArr[i]){
		      		flag = "1";
			      	continue;
		    	}
		      	
	    	}
	      	
	      	if(flag == "0"){
		      	var jsonObj = new Object();
	    		jsonObj.actionid = initArr[i];//用户id
	    		jsonObj.roleid = $("#id").val();//角色id
	    		actionDel.push(jsonObj);
	    	}
	      }
	      delRoleAction(actionDel,save(actionAdd));    	
	    }	    
	});
	
	//请求删除数据
	function delRoleAction(data){
		$.ajax({
        	type:"POST",
        	url:baseUrl+"/toolsManage/roleaction/delRoleAction",
        	data:JSON.stringify(data),
        	contentType: "application/json; charset=UTF-8",
        	dataType : "json",
        	success : function(res){
        		if(res.data=="true"){			            
		            return true;
		        }else{
		        	return false;
		        }	
        	},error : function(xhr,status,error){
        		return false;
        	}
        })  
	}
	//请求保存数据
	function save(data){
    	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
        	type:"POST",
        	url:baseUrl+"/toolsManage/roleaction/adds",
        	data:JSON.stringify(data),
        	contentType: "application/json; charset=UTF-8",
        	dataType : "json",
        	success : function(res){
	        	setTimeout(function(){
		            top.layer.close(index);
	        		if(res.data!="参数错误"){			            
			            top.layer.msg("角色保存成功！");
			            //刷新父页面
			        	parent.location.reload();
			        }else{
			        	top.layer.msg("角色保存失败！稍候重试");
			        }		
	        	},1000);
        	},error : function(xhr,status,error){
        		top.layer.close(index);
        		top.layer.msg("角色保存失败！稍候重试");
        	}
        })  
    };
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
    //当前时间
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})

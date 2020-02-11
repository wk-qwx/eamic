layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
	
	
    //列表
    var tableIns = table.render({
        elem: '#actionList',
        url : baseUrl+'/toolsManage/action/getActionList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        id : "actionListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'actionname', title: '权限名称', minWidth:100, align:"center"},
            {field: 'action', title: '权限代码', minWidth:100, align:"center"},
            {field: 'actioncolumnid', title: '分栏菜单', minWidth:100, align:"center"},
            {field: 'viewmode', title: '是否可用', minWidth:100, align:"center"},
            {title: '操作', minWidth:175, templet:'#actionListBar',fixed:"right",align:"center"}
        ]],
        parseData:function(res){
        
	        return {
	          "code":0,
	          "msg":"",
	          "count":res.data.pageInfo.totalCount, 
	          "data":res.data.dataSource
	        };
    	}
    });
    
	
	
    //搜索
    $(".search_btn").on("click",function(){
    	var searchStr = "";    	
    	
        if($(".searchVal").val() != ''){
        	searchStr += "actionname like '%"+$(".searchVal").val()+"%'"            
        }
        var index = layer.msg('正在查询，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
	        table.reload("actionListTable",{
	            	url: baseUrl+'/toolsManage/action/getActionListByFileter',
	                page: {
	                    curr: 1 //重新从第 1 页开始
	                },
	                where: {whereStr: searchStr  //搜索的关键字
	                }
	            },'data')
	        layer.close(index);
        },800);
        
    });
	
    //添加用户
    $(".addaction_btn").click(function(){
    	openDetial("", "添加");
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('actionListTable'),
            data = checkStatus.data;
        if(data.length > 0) {
            layer.confirm('确定删除选中的数据吗？',{icon:3, title:'提示信息'},function(index){
                $.ajax({
		    	  type: 'POST',
		    	  url: baseUrl+'/toolsManage/action/deletes',
		    	  data: JSON.stringify(data),
		    	  contentType: "application/json; charset=UTF-8",
		    	  dataType : "json",
		    	  success: function(result) {
		    	  	  tableIns.reload();
	                  layer.close(index);
		    	  }});
            });
        }else{
            layer.msg("请选择需要删除的数据");
        }
    })
	//编辑信息窗口
    function openDetial(data, action){
    	//iframe窗
		var index = layui.layer.open({
            title : action,
            type : 2,
            closeBtn: 2,
            area: ['35%', '70%'],
            content : action == "添加"?"actionAdd.html":"actionEdit.html",
            moveType: 0, //拖拽模式，0或者1
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(data){
                	body.find("#id").val(data.id);  //id
                    body.find("#actionname").val(data.actionname);  //手机号码
                    body.find("#actioncolumnid").val(data.actioncolumnid);  //真实姓名
                    body.find("#action").val(data.action);  //用户名
					data.viewmode == "是"?body.find("#yes").attr("checked","true"):body.find("#no").attr("checked","true");  //是否启用
					body.find("#index").val(index);  //弹出层
                    layui.form.render();
                }
                
            }
       })  
       
    }
    
    //列表操作
    table.on('tool(actionList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
		
		if(layEvent === 'edit'){ //编辑
            openDetial(data, "编辑");//编辑            
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除数据吗？',{icon:3, title:'提示信息'},function(index){
                $.ajax({
		    	  type: 'POST',
		    	  url: baseUrl+'/toolsManage/action/delAction',
		    	  data: JSON.stringify(data),
		    	  contentType: "application/json; charset=UTF-8",
		    	  dataType : "json",
		    	  success: function(result) {
		    	  	if(result.data!=null && result.data != ""){		    	  
		    	  	  	tableIns.reload();
	                  	layer.close(index);
	               	}else{
	               		tableIns.reload();
	                  	layer.close(index);
	                  	layer.msg("删除失败！");
	               	}
		    	  }});
            });
        }
    });

})

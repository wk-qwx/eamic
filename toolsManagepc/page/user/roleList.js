layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
		
	
    //列表
    var tableIns = table.render({
        elem: '#roleList',
        url : baseUrl+'/toolsManage/role/getRoleList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        id : "roleListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'createtime', title: '序号', width:80, type:"numbers", sort:true},
            {field: 'rolename', title: '角色名称', minWidth:100, align:"center"},
            {field: 'roleinfo', title: '角色描述', minWidth:100, align:"center"},                      
            {title: '操作', minWidth:175, templet:'#roleListBar',fixed:"right",align:"center"}
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
        	searchStr += "username like '%"+$(".searchVal").val()+"%'"            
        }
        var index = layer.msg('正在查询，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
	        table.reload("roleListTable",{
	            	url: baseUrl+'/toolsManage/role/getRoleListByFileter',
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
    $(".addRole_btn").click(function(){
    	openDetial("", "添加");
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('roleListTable'),
            data = checkStatus.data;
        if(data.length > 0) {
            layer.confirm('确定删除选中的数据吗？',{icon:3, title:'提示信息'},function(index){
                $.ajax({
		    	  type: 'POST',
		    	  url: baseUrl+'/toolsManage/role/deletes',
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
            area: ['30%', '50%'],
            content : action == "添加"?"roleAdd.html":"roleEdit.html",
            moveType: 0, //拖拽模式，0或者1
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(data){
                	body.find("#id").val(data.id);  //id
                    body.find("#rolename").val(data.rolename);  //角色名称
                    body.find("#roleinfo").val(data.roleinfo);  //角色信息   
                    body.find("#masterid").val(data.masterid);  //创建者id   
                    body.find("#mastername").val(data.mastername);  //创建者名称   
                    body.find("#createtime").val(data.createtime);  //创建时间
					body.find("#index").val(index);  //弹出层
                    layui.form.render();
                }
                
            }
       })  
       
    }
    //角色配置
    function openConfig(data,config){
    	//iframe窗
		var index = layui.layer.open({
            title : config,
            type : 2,
            closeBtn: 2,
            area: ['38%', '90%'],
            content : config == "配置用户"?"configUser.html":"configAction.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(data){
                	body.find("#id").val(data.id);  //id
                	body.find("#rolename").val(data.rolename);  //id
					body.find("#index").val(index);  //弹出层
                    layui.form.render();
                }
                
            }
       })  
       
    }
    //列表操作
    table.on('tool(roleList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
		if(layEvent === 'configUser'){ //配置用户
            openConfig(data,"配置用户");
		}if(layEvent === 'configAction'){ //配置权限
            openConfig(data,"配置权限");
		}if(layEvent === 'edit'){ //编辑
            openDetial(data, "编辑");//编辑            
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除数据吗？',{icon:3, title:'提示信息'},function(index){
                $.ajax({
		    	  type: 'POST',
		    	  url: baseUrl+'/toolsManage/role/delRole',
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

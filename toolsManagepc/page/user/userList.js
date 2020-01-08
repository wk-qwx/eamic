layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
	
	
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
	                    $('#'+id+'').append(new Option(item.display,item.display));
	                });
	            } else {
	                $("#"+id+"").append(new Option("暂无数据", ""));
	            }
	            layui.form.render("select");
	        }
    	});
	}
	
	
    //列表
    var tableIns = table.render({
        elem: '#userList',
        url : baseUrl+'/toolsManage/user/getUserList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'username', title: '用户名', minWidth:100, align:"center"},
            {field: 'pwd', title: '密码', minWidth:100, align:"center"},
            {field: 'truename', title: '真实姓名', minWidth:100, align:"center"},
            {field: 'sex', title: '性别', minWidth:100, align:"center"},
            {field: 'phone', title: '手机号码', minWidth:100, align:"center"},
            {field: 'sunits', title: '三级单位', minWidth:100, align:"center"},
            {field: 'groupname', title: '所属班组/供电所', minWidth:200, align:'center'},            
            {title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
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
    	var sunits = $("#sunits option:selected").val();//获取工器具所属单位下拉文本 
    	var searchStr = "";    	
    	if(sunits != ''){
    		searchStr += "sunits = '"+sunits+"' and ";
    	}
        if($(".searchVal").val() != ''){
        	searchStr += "username like '%"+$(".searchVal").val()+"%'"            
        }else{
        	if(searchStr == ""){
        		
        	}else{
        		//字符串截取结尾的and
        		searchStr = searchStr.substring(0,searchStr.length-4);
        	} 
        }
        var index = layer.msg('正在查询，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
	        table.reload("userListTable",{
	            	url: baseUrl+'/toolsManage/user/getUserListByFileter',
	                page: {
	                    curr: 1 //重新从第 1 页开始
	                },
	                where: {whereStr: searchStr  //搜索的关键字
	                }
	            },'data')
	        layer.close(index);
        },900);
        
    });
	
    //添加用户
    $(".addUser_btn").click(function(){
    	openDetial("", "添加");
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data;
        if(data.length > 0) {
            layer.confirm('确定删除选中的数据吗？',{icon:3, title:'提示信息'},function(index){
                $.ajax({
		    	  type: 'POST',
		    	  url: baseUrl+'/toolsManage/user/deletes',
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
            area: ['30%', '80%'],
            content : action == "添加"?"userAdd.html":"userEdit.html",
            moveType: 0, //拖拽模式，0或者1
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(data){
                	body.find("#id").val(data.id);  //id
                    body.find("#opsunits").val(data.sunits);  //三级单位
                    body.find("#opsunits").text(data.sunits);  //三级单位
                    body.find("#opgroupname").val(data.groupname);  //所属班组
                    body.find("#opgroupname").text(data.groupname);  //所属班组
                    body.find("#phone").val(data.phone);  //手机号码
                    body.find("#truename").val(data.truename);  //真实姓名
                    body.find("#username").val(data.username);  //用户名
					data.sex == "男"?body.find("#man").attr("checked","true"):body.find("#woman").attr("checked","true");  //性别
					body.find("#pwd").val(data.pwd);  //密码
					body.find("#index").val(index);  //弹出层
                    layui.form.render();
                }
                
            }
       })  
       
    }
    
    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
		
		if(layEvent === 'edit'){ //编辑
            openDetial(data, "编辑");//编辑            
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除数据吗？',{icon:3, title:'提示信息'},function(index){
                $.ajax({
		    	  type: 'POST',
		    	  url: baseUrl+'/toolsManage/user/delUser',
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

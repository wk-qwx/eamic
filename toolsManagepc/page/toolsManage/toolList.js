layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
	
	var jsonstr = {"name":"安全工器具类别"};
	loadselect(jsonstr,"tooltype");
	jsonstr = {"name":"国网衡阳供电公司"};
	loadselect(jsonstr,"unit");
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
        elem: '#toolList',
        url : baseUrl+'/toolsManage/toolslibv/getToolList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30,50],
        id : "toolListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'qrcode', title: '序号', width:80, type:"numbers", sort:true},
            {field: 'unit', title: '单位', minWidth:100, align:"center"},
            {field: 'sunits', title: '三级单位', minWidth:120, align:'center'},
            {field: 'groupname', title: '所属班组', minWidth:150, align:'center'},
            {field: 'tooltype', title: '安全工器具类别', minWidth:150, align:'center'},
            {field: 'scale', title: '规格', minWidth:100, align:'center'},
            {field: 'toolname', title: '安全工器具名称', minWidth:280, align:'center'},
            {field: 'qrcode', title: 'qrcode', minWidth:120, align:'center'},            
            {field: 'devicestate2', title: '安全工器具状态', align:'center',minWidth:150, sort:true},
            {field: 'uselife', title: '检测周期(月)', align:'center',minWidth:150},
            {field: 'lastcheck', title: '上一次检测时间', align:'center',minWidth:150, sort:true},
            {field: 'newcheck', title: '下一次检测时间', align:'center',minWidth:150, sort:true},
            {title: '操作', minWidth:175, templet:'#toolListBar',fixed:"right",align:"center"}
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
    	var toolstate = $("#toolstate option:selected").val();//获取工器具状态下拉文本
    	var tooltype = $("#tooltype option:selected").val();//获取工器具类别下拉文本
    	var unit = $("#unit option:selected").val();//获取工器具所属单位下拉文本 
    	var searchStr = "";
    	if(toolstate != ''){
    		searchStr += "devicestate2 = '"+toolstate+"' and ";
    	}
    	if(tooltype != ''){
    		searchStr += "tooltype = '"+tooltype+"' and ";
    	}
    	if(unit != ''){
    		searchStr += "sunits = '"+unit+"' and ";
    	}
        if($(".searchVal").val() != ''){
        	searchStr += "qrcode like '%"+$(".searchVal").val()+"%'"            
        }else{
        	if(searchStr == ""){
        		
        	}else{
        		//字符串截取结尾的and
        		searchStr = searchStr.substring(0,searchStr.length-4);
        	} 
        }
        var index = layer.msg('正在查询，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
	        table.reload("toolListTable",{
	            	url: baseUrl+'/toolsManage/toolslibv/getListByFilter',
	                page: {
	                    curr: 1 //重新从第 1 页开始
	                },
	                where: {whereStr: searchStr  //搜索的关键字
	                }
	            },'data')
	        layer.close(index);
        },1000);
        
    });
	
    

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('toolListTable'),
            data = checkStatus.data;
        if(data.length > 0) {
            layer.confirm('确定删除选中的数据吗？',{icon:3, title:'提示信息'},function(index){
                $.ajax({
		    	  type: 'POST',
		    	  url: baseUrl+'/toolsManage/toolslib/deletes',
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
	//编辑工器具信息
    function openDetial(data){
    	//iframe窗
		var index = layui.layer.open({
            title : "编辑",
            type : 2,
            closeBtn: 2,
            area: ['55%', '75%'],
            content : "toolinfo.html",
            moveType: 0, //拖拽模式，0或者1
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(data){
                	body.find(".id").val(data.id);  //id
                    body.find(".unit").val(data.unit);  //所属单位
                    body.find(".sunits").val(data.sunits);  //三级单位
                    body.find(".tooltype").val(data.tooltype);  //安全工器具类别
                    body.find(".groupname").val(data.groupname);  //所属班组
                    body.find(".scale").val(data.scale);  //规格、电压等级
                    body.find(".toolname").val(data.toolname);  //安全工器具名称
                    body.find(".devicestate").val(data.devicestate);    //安全工器具状态
                    body.find(".qrcode").val(data.qrcode);    //安全工器具代码     
                    body.find("#opuselife").val(data.uselife);  //检测周期
                    body.find("#opuselife").text(data.uselife);  //检测周期
                    body.find(".lastcheck").val(data.lastcheck);    //上次检测时间
                    body.find(".newcheck").val(data.newcheck);    //下次检测时间
                    body.find("#index").val(index);  //弹出层
                    form.render();
                }
                
            }
       })        
    }
    
    //列表操作
    table.on('tool(toolList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            openDetial(data);//编辑            
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除数据吗？',{icon:3, title:'提示信息'},function(index){
                $.get(baseUrl+"/toolsManage/toolslib/deletebyid/"+data.id+"",null,function(data){
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });

})

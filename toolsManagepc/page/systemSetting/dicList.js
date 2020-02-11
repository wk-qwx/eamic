layui.use(['form','layer','table','laytpl','tree'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        tree = layui.tree,
        table = layui.table;
	
	
	var dicname="安全工器具类别";
	var level="1";
	let treeData;
	//获取数据源
	function findall() {
	    $.ajax({
	        url: baseUrl+'/toolsManage/dictionary/loadTree',
	        type: "GET",
	        contentType: "application/json",
	        async: false,
	        success: function(data) {
	            treeData = data.data;
	        }
	    })
	    return treeData;
	}
	tree.render({
	    elem: '#dicTree'
	    ,data: findall()
	    ,onlyIconControl: true  //是否仅允许节点左侧图标控制展开收缩
	    ,click: function(obj){
	    	$(".searchVal").val();
	    	dicname = obj.data.name;
	    	level = Number(Number(obj.data.level)+Number(1));
	      	table.reload("dicListTable",{
	        	url: baseUrl+'/toolsManage/dictionary/dicReload?whereStr='+' name = "'+dicname+'" and level = "'+level+'" and pid = "'+obj.data.code+'"'
	        	,page: {
	                    curr: 1 //重新从第 1 页开始
	                }
	      	},'data')
	    }
	 });
	
    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : baseUrl+'/toolsManage/dictionary/dicReload?whereStr='+' name = "'+dicname+'" and level = "'+level+'"',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        id : "dicListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'code', title: '序号', width:80, type:"numbers", sort:true},
            {field: 'display', title: '字典项', minWidth:100, align:"center"},
            {field: 'code', title: '代码', minWidth:100, align:"center"},
            {field: 'name', title: '字典名称', minWidth:100, align:"center"},
            {field: 'level', title: '级别', minWidth:100, align:"center"}
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
    
	
    //添加用户
    $(".adddic_btn").click(function(){
    	openDetial("", "添加字典项");
    });

    
	//编辑信息窗口
    function openDetial(data, dic){
    	//iframe窗
		var index = layui.layer.open({
            title : dic,
            type : 2,
            closeBtn: 2,
            area: ['30%', '70%'],
            content : "dicAdd.html",
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
        

})

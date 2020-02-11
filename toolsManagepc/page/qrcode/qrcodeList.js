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
        elem: '#qrcodeList',
        url : baseUrl+'/toolsManage/qrcodev/getQrcodeView',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30,50],
        id : "qrcodeListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'batch', title: '序号', width:80, type:"numbers", sort:true},
            {field: 'unit', title: '单位', minWidth:120, align:"center"},
            {field: 'sunits', title: '三级单位', minWidth:120, align:'center'},
            {field: 'groupname', title: '所属班组', minWidth:120, align:'center'},
            {field: 'tooltype', title: '安全工器具类别', minWidth:150, align:'center'},
            {field: 'toolname', title: '安全工器具名称', minWidth:280, align:'center'},
            {field: 'qrcode', title: 'qrcode', minWidth:120, align:'center'},
            {field: 'createtime', title: '生成时间', align:'center',minWidth:200, sort:true},
            {field: 'batch', title: '批次', align:'center',minWidth:80},
            {field: 'remarks', title: '备注', align:'center',minWidth:200}
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
    	var tooltype = $("#tooltype option:selected").val();//获取工器具类别下拉文本
    	var unit = $("#unit option:selected").val();//获取工器具所属单位下拉文本 
    	var searchStr = "";
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
	        table.reload("qrcodeListTable",{
	            	url: baseUrl+'/toolsManage/qrcodev/getListByFilter',
	                page: {
	                    curr: 1 //重新从第 1 页开始
	                },
	                where: {whereStr: searchStr  //搜索的关键字
	                }
	            },'data')
	        layer.close(index);
        },1000);
        
    });

    

    //批量导出文件
    $(".exprot_btn").click(function(){
        var checkStatus = table.checkStatus('qrcodeListTable'),
            data = checkStatus.data;
        if(data.length > 0) {           
            layer.confirm('确定导出选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
                downloadFile(data);
                tableIns.reload();
                layer.close(index);
            })
        }else{
            layer.msg("请选择需要导出的数据");
        }
    })
	//下载二维码文件
    function downloadFile(row){
    	var index;
        $.ajax({
	    	  type: 'POST',
	    	  url: baseUrl+'/toolsManage/qrcodev/exportFile/2',
	    	  data: JSON.stringify(row),
	    	  contentType: "application/json; charset=UTF-8",
	    	  dataType : "json",
	    	  success: function(result) {
	    	  	  setTimeout(function(){
		            layer.close(index);
		            var data = result.data;//返回的结果
	    		    if(data!=null){
	    		  	   window.open(baseUrl+data);
	    		    }else{
	    		  	   layer.alert("下载异常！");
	    		    }		            
		        },1000);	    		  
	    	  },
	    	  beforeSend:function(XMLHttpRequest){
		        index = layer.msg('正在下载中，请稍候',{icon: 16,time:false,shade:0.8});
	    	  },
	    	  error:function(){
                layer.close(index);
                layer.alert("下载异常");
            }

	    });
    }
    //列表操作
    table.on('tool(qrcodeList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此用户？",
                btnText = "已禁用";
            if(_this.text()=="已禁用"){
                usableText = "是否确定启用此用户？",
                btnText = "已启用";
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                _this.text(btnText);
                layer.close(index);
            },function(index){
                layer.close(index);
            });
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
                // $.get("删除文章接口",{
                //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                    tableIns.reload();
                    layer.close(index);
                // })
            });
        }
    });

})

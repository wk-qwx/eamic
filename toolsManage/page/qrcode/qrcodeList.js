layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;


	var jsonstr = {"name":"工器具类别"};
	loadselect(jsonstr,"tooltype");
	jsonstr = {"name":"国网衡阳供电公司"};
	loadselect(jsonstr,"unit");
	//加载下拉框
	function loadselect(jsonstr,id){
		$.ajax({
		    type: 'POST',
		    url: 'http://localhost:8086/toolsManage/dictionary/getDictionary',
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
        url : 'http://localhost:8086/toolsManage/qrcodev/getQrcodeView',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        id : "qrcodeListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'unit', title: '单位', minWidth:120, align:"center"},
            {field: 'sunits', title: '三级单位', minWidth:200, align:'center'},
            {field: 'groupname', title: '所属班组', minWidth:150, align:'center'},
            {field: 'tooltype', title: '安全工器具类别', minWidth:150, align:'center'},
            {field: 'toolname', title: '安全工器具名称', minWidth:200, align:'center'},
            {field: 'qrcode', title: 'qrcode', minWidth:100, align:'center'},
            {field: 'createtime', title: '生成时间', align:'center',minWidth:150},
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
	/**
	 *根据data数据解密 
	 * @param {Object} data
	 */
	function decryptBydata(data){
		var newData = [];
		for(var i=0; i<data.length; i++){
			var row = {};
			if(data[i] != null){
				row.unit = data[i].unit == null || data[i].unit == ""?data[i].unit:parent.Decrypt(data[i].unit);
				row.sunits = data[i].sunits == null || data[i].sunits == ""?data[i].sunits:parent.Decrypt(data[i].sunits);
				row.tooltype = data[i].tooltype == null || data[i].tooltype == ""?data[i].tooltype:parent.Decrypt(data[i].tooltype);
				row.toolname = data[i].toolname == null || data[i].toolname == ""?data[i].toolname:parent.Decrypt(data[i].toolname);
				row.item = data[i].item == null || data[i].item == ""?data[i].item:parent.Decrypt(data[i].item);
				row.createtime = data[i].createtime == null || data[i].createtime == ""?data[i].createtime:parent.Decrypt(data[i].createtime);
				row.filepath = data[i].filepath == null || data[i].filepath == ""?data[i].filepath:parent.Decrypt(data[i].filepath);
				row.remarks = data[i].remarks == null || data[i].remarks == ""?data[i].remarks:parent.Decrypt(data[i].remarks);
				newData.push(row);
			}
		}
		return newData;
	}
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
	            	url: 'http://localhost:8086/toolsManage/qrcodev/getListByFilter',
	                page: {
	                    curr: 1 //重新从第 1 页开始
	                },
	                where: {whereStr: searchStr  //搜索的关键字
	                }
	            },'data')
	        layer.close(index);
        },900);
        
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
	    	  url: 'http://localhost:8086/toolsManage/qrcodev/exportFile/2',
	    	  data: JSON.stringify(row),
	    	  contentType: "application/json; charset=UTF-8",
	    	  dataType : "json",
	    	  success: function(result) {
	    	  	  setTimeout(function(){
		            layer.close(index);
		            var data = result.data;//返回的结果
	    		    if(data!=null){
	    		  	   window.open(data);
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

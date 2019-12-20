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
        url : 'http://localhost:8086/toolsManage/qrcode/getQrcodeFileList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        id : "qrcodeListTable",
        cols : [[
            {field: 'unit', title: '单位', minWidth:100, align:"center"},
            {field: 'sunits', title: '三级单位', minWidth:150, align:'center'},
            {field: 'tooltype', title: '安全工器具类别', minWidth:100, align:'center'},
            {field: 'batch', title: '批次', align:'center', width:80},
            {field: 'item', title: '数量', align:'center', width:80},
            {field: 'createtime', title: '生成时间', align:'center',minWidth:150},
            {field: 'remarks', title: '备注', align:'center',minWidth:200},
            {title: '操作', minWidth:175, templet:'#qrcodeListBar',fixed:"right",align:"center"}
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
        	searchStr += "batch like '%"+$(".searchVal").val()+"%'"            
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
	            	url: 'http://localhost:8086/toolsManage/qrcode/getListByFilter',
	                page: {
	                    curr: 1 //重新从第 1 页开始
	                },
	                where: {whereStr: searchStr  //搜索的关键字
	                }
	            },'data')
	        layer.close(index);
        },900);
        
    });

	
    //下载二维码文件
    function downloadFile(row){
    	var index;
        $.ajax({
	    	  type: 'POST',
	    	  url: 'http://localhost:8086/toolsManage/qrcodev/exportFile/1',
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
    //查看二维码信息
    function qrcodeShow(data){
        var index = layui.layer.open({
            title : "信息查看",
            type : 2,
            content : "qrcodeinfo.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(data){
                    body.find(".unit").val(data.unit);  //所属单位
                    body.find(".sunits").val(data.sunits);  //三级单位
                    body.find(".tooltype").val(data.tooltype);  //安全工器具类别
                    body.find(".batch").val(data.batch);  //批次
                    body.find(".createtime").val(data.createtime);  //生成时间
                    body.find(".item").val(data.item);    //数量
                    body.find(".remarks").text(data.remarks?null:"");    //备注
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('qrcodeListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        }else{
            layer.msg("请选择需要删除的数据");
        }
    })

    //列表操作
    table.on('tool(qrcodeList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
			
        if(layEvent === 'download'){ //下载
        	var arr = [];
			arr.push(data);
            downloadFile(arr);
        }else if(layEvent === 'show'){ //查看
            qrcodeShow(data);//查看
        }
    });

})

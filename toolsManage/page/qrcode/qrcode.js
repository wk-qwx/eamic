layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#qrcodeList',
        url : 'http://localhost:8086/toolsManage/qrcode/getall/1/10',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        id : "qrcodeListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'unit', title: '单位', minWidth:100, align:"center"},
            {field: 'sunits', title: '三级单位', minWidth:150, align:'center'},
            {field: 'tooltype', title: '工器具类别', minWidth:100, align:'center'},
            {field: 'batch', title: '批次', align:'center', width:80},
            {field: 'item', title: '数量', align:'center', width:80},
            {field: 'createtime', title: '生成时间', align:'center',minWidth:150},
            {field: 'remarks', title: '备注', align:'center',minWidth:200},
            {title: '操作', minWidth:175, templet:'#qrcodeListBar',fixed:"right",align:"center"}
        ]],
        parseData:function(res){
        console.log(res);
        
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
        if($(".searchVal").val() != ''){
            table.reload("qrcodeListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	key:{
                    	toolname: $(".searchVal").val()  //搜索的关键字
                    }
                }
            },'data')
        }else{
            layer.msg("请输入搜索的工器具名称");
        }
    });
	var _index;
    var _lp_baseTime = 0;
    var _lp_startTime = 0;
    function updateLoadProgress() {
        if (_lp_baseTime < 0) {
            layer.close(_index);
            return;
        }
        var dval = parseInt(new Date().valueOf())- parseInt(_lp_startTime);
        var timeDifference = (dval / _lp_baseTime).toFixed(2);
        var lp = timeDifference < 1 ? timeDifference * 100 : 99;
        $("#loadProgress").html(parseInt(lp));
        setTimeout(function () { updateLoadProgress(); return; }, 650);
    }
    //下载二维码文件
    function downFile(row){
        $.ajax({
	    	  type: 'POST',
	    	  url: 'http://localhost:8086/toolsManage/qrcodev/exportFile',
	    	  data: JSON.stringify(row),
	    	  contentType: "application/json; charset=UTF-8",
	    	  dataType : "json",
	    	  success: function(result) {
	    	  	  _lp_baseTime = -1;
	    	  	  $("#loadProgress").html("100");
	    	  	  layer.close(_index);
	    		  var data = result.data;//返回的结果
	    		  if(data!=null){
	    		  	 window.open("http://47.106.193.135:8080/static/export/缺陷信息2019-11-21.xls");
	    		  }else{
	    		  	 layer.alert("下载异常");
	    		  }
	    	  },
	    	  beforeSend:function(XMLHttpRequest){
		        _index = layer.load(1, {
		                content: "<div style='margin-left:-23px;padding-top:44px;width:120px;color:#FFF;'>正在下载(<span id='loadProgress' >0</span>%)</div>",
		                shade: [0.5, '#000']
		            });
		        //这里是预估的时间，因为整个进度都是根据消耗时间计算的 = =
		        //这里的start,end,stns都是原始项目里带的。这个的作用是计算要等待的时长的，可以给一个固定值或者其他的东西公式，保证结果是正整数，单位是毫秒即可。
		        _lp_baseTime = Math.abs(new Date(10).valueOf() - new Date(5).valueOf()) / 3600000 * 5 * 150;
		            _lp_startTime = new Date().valueOf();
		 
		           setTimeout(function () { updateLoadProgress(); return; }, 60);
	    	  },
	    	  error:function(){
                _lp_baseTime = -1;
                layer.close(_index);
            }

	    });
    }
    $(".addNews_btn").click(function(){
        addUser();
    })

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

        if(layEvent === 'download'){ //编辑
            downFile(data);
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

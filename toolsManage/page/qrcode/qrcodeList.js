layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

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
            {field: 'unit', title: '单位', minWidth:100, align:"center"},
            {field: 'sunits', title: '三级单位', minWidth:150, align:'center'},
            {field: 'groupname', title: '所属班组', minWidth:150, align:'center'},
            {field: 'tooltype', title: '工器具类别', minWidth:100, align:'center'},
            {field: 'toolname', title: '工器具名称', minWidth:100, align:'center'},
            {field: 'qrcode', title: 'code', minWidth:200, align:'center'},
            {field: 'createtime', title: '生成时间', align:'center',minWidth:150},
            {field: 'batch', title: '批次', align:'center',minWidth:100},
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

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加用户",
            type : 2,
            content : "userAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".userName").val(edit.userName);  //登录名
                    body.find(".userEmail").val(edit.userEmail);  //邮箱
                    body.find(".userSex input[value="+edit.userSex+"]").prop("checked","checked");  //性别
                    body.find(".userGrade").val(edit.userGrade);  //会员等级
                    body.find(".userStatus").val(edit.userStatus);    //用户状态
                    body.find(".userDesc").text(edit.userDesc);    //用户简介
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

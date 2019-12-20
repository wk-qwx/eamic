layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

	var batchTxt = $("#batch").val();
    //列表
    var tableIns = table.render({
        elem: '#qrcodeInfo',
        title:"",
        url : 'http://localhost:8086/toolsManage/toolslibv/getListByBatch?batch='+batchTxt,
        cellMinWidth : 65,
        page : true,
        height : "full-105",
        limits : [10,15,25],
        id : "qrcodeInfoTable",
        cols : [[
            {field: 'groupname', title: '所属班组', minWidth:150, align:'center'},
            {field: 'toolname', title: '安全工器具名称', minWidth:200, align:'center'},
            {field: 'qrcode', title: 'qrcode', minWidth:120, align:'center'},            
            {field: 'devicestate2', title: '安全工器具状态', align:'center',minWidth:150},
            {field: 'uselife', title: '检测周期(月)', align:'center',minWidth:150},
            {field: 'lastcheck', title: '上一次检测时间', align:'center',minWidth:150},
            {field: 'newcheck', title: '下一次检测时间', align:'center',minWidth:150}
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
})
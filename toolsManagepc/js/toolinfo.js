layui.use(['form','layer'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
	
	loadinfo();
	//加载表单
	function loadinfo(){
		var code = getParams("code");
		
    	$.get("http://47.106.193.135:8081/toolsManage/toolslibv/getToolInfoByWx/"+code+"",null,function(data){
                    var data = data.data[0];
                    var body = $(".childrenBody");
                    body.find(".unit").val(data.unit);  //所属单位
                    body.find(".sunits").val(data.sunits);  //三级单位
                    body.find(".tooltype").val(data.tooltype);  //安全工器具类别
                    body.find(".groupname").val(data.groupname);  //所属班组
                    body.find(".toolname").val(data.toolname);  //安全工器具名称
                    body.find(".devicestate").val(data.devicestate2);    //安全工器具状态
                    body.find(".qrcode").val(data.qrcode);    //安全工器具代码
                    body.find(".uselife").val(data.uselife);    //检测周期
                    body.find(".lastcheck").val(data.lastcheck);    //上次检测时间
                    body.find(".newcheck").val(data.newcheck);    //下次检测时间
               });
	}
	//获取url参数
	function getParams(name) {
	    var pos, str, para, parastr;
	    var array = []
	    str = window.location.href;
	    if (str.split("?")[1] != undefined && str.split("=")[1] != undefined) {
	      parastr = str.split("?")[1];
	      parastr=decodeURIComponent(parastr);
	      var arr = []
	      arr = parastr.split("&");
	      
	      for (var i = 0; i < arr.length; i++) {
	        array[arr[i].split("=")[0]] = arr[i].split("=")[1];
	      }
	 
	    }
	 
	    return array[name];//project为所要获取的参数
 
 }
})
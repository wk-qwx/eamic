//全局接口服务地址
var baseUrl = "http://localhost:8086";
layui.use(['layer','jquery'],function(){
        $ = layui.jquery;

//判断并验证是否有效登录
if(localStorage.getItem("token")=="" || localStorage.getItem("token")==null){
	top.window.location.href = "/toolsManagepc/page/login/login.html";
}else{			
	$.ajax({
		type:"POST",
		url:baseUrl+"/toolsManage/user/checkUser?username="+
				"&pwd=&token="+localStorage.getItem("token"),
		data:"",
		contentType: "application/json; charset=UTF-8",
		dataType : "json",
		success : function(res){
			if(res.data=="true"){ 
				if(localStorage.getItem("truename")!=null){
					if(top.$(".loginName").length > 0){
						$(".loginName").text(localStorage.getItem("truename"));
					}else{
						top.window.location.href = "/toolsManagepc/page/login/login.html";
					}
				}
			}else{	    			
				top.layer.msg("用户登录已失效请重新验证登录>>>返回登录页！"); 
				setTimeout(function(){
					top.window.location.href = "/toolsManagepc/page/login/login.html";
				},1000)	    			
			}
	}});	
}
})
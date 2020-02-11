layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    
	var baseUrl = "http://localhost:8086";
    //登录按钮
    form.on("submit(login)",function(data){
    	var username = $("#username").val();
    	var pwd = $("#password").val();
        $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        setTimeout(function(){
        	$.ajax({
        	type:"POST",
        	url:baseUrl+"/toolsManage/user/checkUser?username="+username+"&pwd="+pwd+"&token=1",
        	data:"",
        	contentType: "application/json; charset=UTF-8",
        	dataType : "json",
        	success : function(res){
        		if(res.data=="[]"){
        			$("#login").text("登录").removeAttr("disabled","").removeClass("layui-disabled");
        			top.layer.msg("用户名或密码错误！");        			
        		}else{
        			var user = eval('('+res.data+')');
        			localStorage.setItem("truename",user[0].truename);
        			//localStorage.setItem("pwd",user[0].pwd);
        			localStorage.setItem("token",res.token);
        			window.location.href = "/toolsManagepc/index.html";
        		}
        	}});
        },1000);
        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})

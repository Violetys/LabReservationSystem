$(function() {
    //开始处
	$(document).click(function() {//点击页面非登录区 返回
        sessionStorage.setItem("page","1")//表示登录选用账号密码
        offUserMedia();//之前打开了摄像头的话，在这里关闭
		$('#face-login').css({
			"height": "50px",
			"width": "50px",
			"border-radius": "50%",
			"z-index": "1",
			"background": "rgb(240,173,78)",
			"color": "white"
		});
		$("#video").css({
			"display": "none",
			"height": "50px",
			"width": "50px"
		});
		$('#register-page').css({
			"height": "50px",
			"width": "50px",
			"border-radius": "50%",
			"z-index": "1",
			"background": "rgb(92,184,92)",
			"color": "white"
		});
        pop_window_1_off();
	});

    $(".login-box").click(function (event) {//阻止上面document的冒泡
        event.stopPropagation();
    })
    //人脸登录窗口打开
	$('#face-login').click(function() {
	    sessionStorage.setItem("page","2")//表示登录选用人脸
		$('#face-login').css({
			"height": "400px",
			"width": "300px",
			"border-radius": "25px",
			"z-index": "2",
			"background": "white",
			"color": "black"
		});
		$("#video").css({
			"display": "inline",
			"height": "298px",
			"width": "100%"
		});
        pop_window_1_open();
        /*
        打开摄像头
         */
        openUserMedia();
    //    请求登录
        Facelogin();
	});
    //注册页面打开
	$('#register-page').click(function() {
		$('#register-page').css({
			"height": "400px",
			"width": "300px",
			"border-radius": "25px",
			"z-index": "2",
			"background": "white",
			"color": "black"
		});
        pop_window_1_open();
	});
	//验证码
	$('#submit-y').click(function() {
		var i = 59;
		$('#submit-y').attr("disabled", true);
		time = setInterval(function() {
			$('#submit-y').val(i);
			i--;
			if(i < 0) {
				$('#submit-y').val('获取验证码');
				$("#submit-y").attr("disabled", false);
				clearInterval(time);
			}
		}, 1000);
	});
	登录请求
    $("#submit").click(function () {
        var idvalue=$("#inputId").val()
        var psvalue=$("#inputPs").val()
        if(idvalue!=""&&psvalue!=""){
            //后台默认为密码登录请求
            Passwordlogin();//密码不为空 请求后台验证
        }
        else{
            Hintwindow("账号或密码不能为空");
        }
    })
//    注册
    $("#submit-reg").click(function () {
        if(RegVerification()){//注册验证
            Userregister();
        }
    })
//结束处
});
/*
定义的函数
 */
var check = function() { //表单验证 输入框为空不执行提交
	var idvalue = $("#inputId").val()
	var psvalue = $("#inputPs").val()
	var idvalue_z = $("#inputId-z").val()
	var psvalue_z = $("#inputPs-z").val()
	var psvalue_q = $("#inputPs-q").val()
	var psvalue_y = $("#inputPs-y").val()
	if(idvalue == "" || psvalue == "" || idvalue_z == "" || psvalue_z == "" || psvalue_q == "" || psvalue_y == "") {
		return false;
	}
	return true;
}

function pop_window_1_open() {//打开”轻触返回提示“
    $('#pop-window-1').css({
        "opacity": "1"
    });
}
//
function pop_window_1_off() {//隐藏”轻触返回提示“
    $('#pop-window-1').css({
        "opacity": "0"
    });
}
var time;//计时器，在每次调用函数之前 需要清空
function Hintwindow(hintlanguage) {//提示窗
    clearInterval(time);
    $("#pop-window").html(hintlanguage);
    $("#pop-window").animate({
        top:"0"
    },1000);
    time=setInterval(function () {//账号密码不能为空 提示框  消失函数
        $("#pop-window").animate({
            top:"-60px"
        },300)
    },2000)
}
function RegVerification() {
    var phonenum=$("#inputId-z").val();
    var password_z=$("#inputPs-z").val();
    var password_q=$("#inputPs-q").val();
    if(phonenum!=""&&password_q!=""&&password_z!=""){
        if(password_z===password_q){
            sessionStorage.setItem("phonenum",phonenum);
            sessionStorage.setItem("password_q",password_q);
            return true;
        }
        else {
            Hintwindow("密码不一致,重新输入");
            $("#inputPs-q").val("");
            return false;
        }
    }else{
        Hintwindow("注册信息不能为空");
        return false;
    }
}
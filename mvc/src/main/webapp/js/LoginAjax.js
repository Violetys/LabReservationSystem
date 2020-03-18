function Facelogin() {
    setTimeout(function () {
        img = getFace();
        $.ajax({
            type:"post",
            url:"http://localhost:8080/mvc/FaceLoginServlet",
            dataType:"json",
            data:{
                "page":"2",
                "imgpath":img,
                "imgType":"BASE64"
            },
            success:function (data) {
            	var error_msg=data['error_msg'];
            	console.log(error_msg);
                if("SUCCESS"===error_msg){
                	var user_id=data['user_id'];
                	//JsonParser parse=new JsonParser();//json解析器
                	
                	var link ="AdminServlet" + '?face_id='+user_id;
                	window.location.href=link;
                    
                }
                else{
                    Facelogin();
                }
            },
            error:function () {
                alert("连接服务器失败")
            },
            async:true
        })
    },500);
}


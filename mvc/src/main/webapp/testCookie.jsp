<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
<style type="text/css">    
     body{    
        background-image: url(http://img.pptjia.com/image/20180117/767f4b74a8d7b453b149430ee364c9ce.jpg);    
        background-size:cover;  
     }    
 </style>


</head>
<body bgcolor="pink">

<%
Cookie cookies[]=null;
if((cookies=request.getCookies())!=null){//读出Cookie，并将所有的Cookie放到一个cookie对象数组里面	
	Cookie Cookie=null;
	String value=null;
	String name=null;
	for(int i=0;i<cookies.length;i++){ //用一个循环遍历Cookie对象数组
		Cookie=cookies[i]; //取出数组中的一个Cookie对象
		name=Cookie.getName(); //取得这个Cookie的名字
		value=Cookie.getValue(); //取得这个Cookie的内容
		if("xys.com_user_id".equals(name)){		//判断有无此项目的cookie
			System.out.println(name+"  "+value);
			request.setAttribute("token", value);
			request.getRequestDispatcher("RedisAutoLogin").forward(request, response);
		}
		//System.out.println(name+"  "+value);
}
}
%>


<%
String errMsg=(String)session.getAttribute("err");
if( errMsg!=null ) { %>

<div style="color:red;"><%=errMsg %></div>

<% session.removeAttribute("err");
} %>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<div margin:300px;>
<center>登录
<form action="testCookie" method="post">
id&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="stu_id"><br/>
密码<input type="password" name="stu_password"><br/>
<input type="submit" value="登录">
<input type="reset" value="重填">
</form> 


</center>


</div>
</body>
</html>
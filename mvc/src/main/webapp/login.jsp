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
if((cookies=request.getCookies())!=null){//读出用户硬盘上的Cookie，并将所有的Cookie放到一个cookie对象数组里面	
	Cookie sCookie=null;
	String svalue=null;
	String sname=null;
	for(int i=0;i<cookies.length;i++){ //用一个循环语句遍历刚才建立的Cookie对象数组
		sCookie=cookies[i]; //取出数组中的一个Cookie对象
		sname=sCookie.getName(); //取得这个Cookie的名字
		svalue=sCookie.getValue(); //取得这个Cookie的内容
		if("xys.com_user_id".equals(sname)){
			System.out.println(sname+"  "+svalue);
			request.setAttribute("token", svalue);
			request.getRequestDispatcher("RedisAutoLogin").forward(request, response);
		}
		//System.out.println(sname+"  "+svalue);
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
<center>管理员登录
<form action="AdminServlet" method="post">
id&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="id"><br/>
密码<input type="password" name="password"><br/>
<input type="submit" value="登录">
<input type="reset" value="重填">
</form> 

<button onclick="location.href='http://localhost:8080/mvc/index.jsp'" type="button"> 人脸识别登录 </button>
</center>


</div>
</body>
</html>
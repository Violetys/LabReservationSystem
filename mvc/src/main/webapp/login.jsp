<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">    
     body{    
        background-image: url(http://img.pptjia.com/image/20180117/767f4b74a8d7b453b149430ee364c9ce.jpg);    
        background-size:cover;  
     }    
 </style>

</head>
<body bgcolor="pink">

<%
String errMsg=(String)session.getAttribute("err");
if( errMsg!=null ) { %>
<div style="color:red;"><%=errMsg %></div>
<% session.removeAttribute("err");
} %>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<div margin:300px;>
<center>管理员登录
<form action="AdminServlet">
id&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="id"><br/>
密码<input type="password" name="password"><br/>
<input type="submit" value="登录">
<input type="reset" value="重填">
</form> 
</center>
</div>
</body>
</html>
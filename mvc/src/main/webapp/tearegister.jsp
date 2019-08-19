<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="pink">
<br><br><br><br><br><br><br><br><br><br><br><br><br>
<center>教师注册
<%
String errMsg=(String)session.getAttribute("err");
if( errMsg!=null ) { %>
<div style="color:red;"><%=errMsg %></div>
<% session.removeAttribute("err");
}%>
<form action="RegServlet">
教师号<input onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="9" name="tea_id"><br/>
密码&nbsp;&nbsp;&nbsp;<input type="password" name="tea_password"><br/>
姓名&nbsp;&nbsp;&nbsp;<input type="text" name="tea_name"><br/>
<input name="st_flag" type="hidden" value="tea" />
<input type="submit" value="注册" >
<input type="reset" value="重填">
</form> 
</center>
</body>
</html>
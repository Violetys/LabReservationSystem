<%@ page language="java" import="beans.Tea" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="pink">
<br><br><br><br><br><br><br><br><br><br><br><br><br>
<center>修改教师信息
<% Tea tea=(Tea)session.getAttribute("tea"); %>
<form action="SaveServlet">
教师号<input type="text" name="tea_id" value="<%=tea.getTea_id() %>" readonly="readonly" style="background-color:lightgrey;"> <br/>
姓名&nbsp;&nbsp;&nbsp;<input type="text" name="tea_name" value="<%=tea.getTea_name() %>" ><br/>
密码&nbsp;&nbsp;&nbsp;<input type="text" name="tea_password" value="<%=tea.getTea_password() %>" ><br/>

<input name="st_flag" type="hidden" value="tea" />
<button type="submit">提交</button>
</form>
<%
String err=(String)session.getAttribute("err"); 
if(err!=null){
	out.print("<div style='color:red'>"+err+"</div>");
	session.removeAttribute("err");
}	
%>

</center>
</body>
</html>
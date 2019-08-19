<%@ page language="java" import="beans.Stu" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="pink">
<br><br><br><br><br><br><br><br><br><br><br><br><br>
<center>修改学生信息
<% Stu stu=(Stu)session.getAttribute("stu"); %>
<form action="SaveServlet">
学号&nbsp;<input type="text" name="stu_id" value="<%=stu.getStu_id() %>" readonly="readonly" style="background-color:lightgrey;"> <br/>
姓名&nbsp;<input type="text" name="stu_name" value="<%=stu.getStu_name() %>" > <br/>
密码&nbsp;<input type="text" name="stu_password" value="<%=stu.getStu_password() %>" > <br/>
专业&nbsp;<input type="text" name="stu_major" value="<%=stu.getStu_major() %>" > <br/>
年级&nbsp;<input type="text" name="stu_grade" value="<%=stu.getStu_grade()%>" > <br/>
班级&nbsp;<input type="text" name="stu_class" value="<%=stu.getStu_class() %>" > <br/>
<input name="st_flag" type="hidden" value="stu" />
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
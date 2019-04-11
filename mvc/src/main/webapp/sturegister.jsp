<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String errMsg=(String)session.getAttribute("err");
if( errMsg!=null ) { %>
<div style="color:red;"><%=errMsg %></div>
<% session.removeAttribute("err");
}%>
<form action="RegServlet">
学号<input onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="9" name="stu_id"><br/>
密码<input type="password" name="stu_password"><br/>
名字<input type="text" name="stu_name"><br/>
专业<input type="text" name="stu_major"><br/>
年级<input onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="9" name="stu_grade"><br/>
班级<input onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="9" name="stu_class"><br/>
<input name="st_flag" type="hidden" value="stu" />
<input type="submit" value="注册" >
<input type="reset" value="重填">
</form> 

</body>
</html>
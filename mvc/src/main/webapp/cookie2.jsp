<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,beans.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
<% 
Stu stu=null;
if(request.getAttribute("stu")!=null){
	stu=(Stu)request.getAttribute("stu");
		
}


//String token=(String)request.getAttribute("token");
//Cookie cookie=new Cookie("xys.com_user_id",token);
//cookie.setMaxAge(300); //存活期为300秒
//response.addCookie(cookie);
//System.out.println(cookie.getName()+"  "+cookie.getValue());

%>
学生ID: <%=stu.getStu_id() %>   学生姓名: <%=stu.getStu_name() %>
<%

/*Cookie cookies[]=request.getCookies(); //读出用户硬盘上的Cookie，并将所有的Cookie放到一个cookie对象数组里面
Cookie sCookie=null;
String svalue=null;
String sname=null;
System.out.println(cookies.length);
for(int i=0;i<cookies.length;i++){ //用一个循环语句遍历刚才建立的Cookie对象数组
sCookie=cookies[i]; //取出数组中的一个Cookie对象
sname=sCookie.getName(); //取得这个Cookie的名字
svalue=sCookie.getValue(); //取得这个Cookie的内容
/*if("xys.com_user_id".equals(sname)){
	System.out.println(svalue);
	System.out.println("直接登录");
	request.setAttribute("token", svalue);
	request.getRequestDispatcher("cookie2.jsp").forward(request, response);
}
System.out.println(sname+"  "+svalue);

}
*/
%>

</body>
</html>
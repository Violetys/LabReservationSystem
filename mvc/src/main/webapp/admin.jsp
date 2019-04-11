<%@ page language="java" import="java.util.*,beans.*" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%	List<Tea> tea_list = (List<Tea>) session.getAttribute("tea_list"); %>
<%	List<Stu> stu_list = (List<Stu>) session.getAttribute("stu_list"); %>
<%	List<Lab> lab_list = (List<Lab>) session.getAttribute("lab_list"); %>
<%	List<Equipment> eq_list = (List<Equipment>) session.getAttribute("eq_list"); %>
<%	List<Stu_Eq> stu_eq_list = (List<Stu_Eq>) session.getAttribute("stu_eq_list"); %>
<%	List<Tea_Lab> tea_lab_list = (List<Tea_Lab>) session.getAttribute("tea_lab_list"); %>
<div class="text" style=" text-align:center;">教师信息
<table border="1" align="center">
	<tr>
		<th>教师号</th>	<th>用户名</th>	<th>密码</th>   <th colspan="2">操作</th>
	</tr>
	<%
		if (tea_list != null) {
			for (Tea t : tea_list) {
	%>
	<tr>
		<td>  <%=t.getTea_id()%>  </td>
		<td>  <%=t.getTea_name()%>  </td>
		<td>  <%=t.getTea_password()%>  </td>
		<td>	  <a href="EditServlet?st_flag=tea&tea_id=<%=t.getTea_id()%>">编辑</a>  </td>
		<td>	  <a href="DeleteServlet?st_flag=tea&tea_id=<%=t.getTea_id()%>" onclick="return confirm('删除吗？')">删除</a>  </td>
	</tr>
	<%
		} //end for
			//session.removeAttribute("tea_list");
		} //end if
		else{
			out.print("xxx");
		}
	%>
</table>
<input type="button" value="添加教师" onclick="window.location.href='tearegister.jsp'">
</div>
<br/>



<div class="text" style=" text-align:center;">学生信息
<table border="1" align="center">
	<tr>
		<th>学号</th>	<th>用户名</th>	<th>密码</th>  <th>专业</th>	 <th>年级</th>	<th>班级</th>	 <th colspan="2">操作</th>
	</tr>
	<%
		if (stu_list != null) {
			for (Stu s : stu_list) {
	%>
	<tr>
		<td>  <%=s.getStu_id()%>  </td>
		<td>  <%=s.getStu_name()%>  </td>
		<td>  <%=s.getStu_password()%>  </td>
		<td>  <%=s.getStu_major()%>  </td>
		<td>  <%=s.getStu_grade()%>  </td>
		<td>  <%=s.getStu_class()%>  </td>
		<td>	  <a href="EditServlet?st_flag=stu&stu_id=<%=s.getStu_id()%>">编辑</a>  </td>
		<td>	  <a href="DeleteServlet?st_flag=stu&stu_id=<%=s.getStu_id()%>" onclick="return confirm('删除吗？')">删除</a>  </td>
	</tr>
	<%
		} //end for
			//session.removeAttribute("stu_list");
		} //end if
		else{
			out.print("xxx");
		}
	%>
</table>
<input type="button" value="添加学生" onclick="window.location.href='sturegister.jsp'">
</div>
<br/>



<div class="text" style=" text-align:center;">实验室信息
<table border="1" align="center">
	<tr>
		<th>实验室号</th>	<th>状态</th>  
	</tr>
	<%
		if (lab_list != null) {
			for (Lab l : lab_list) {
	%>
	<tr>
		<td>  <%=l.getLab_id()%>  </td>
		<td>  <%if(l.getLab_state()==0){%>开启<%}else{%>关闭<%} %>  </td>

	</tr>
	<%
		} //end for
			//session.removeAttribute("lab_list");
		} //end if
		else{
			out.print("xxx");
		}
	%>
</table>
</div>
<br/>


<div class="text" style=" text-align:center;">设备信息
<table border="1" align="center">
	<tr>
		<th>设备号</th>  <th>所在实验室</th>	<th>状态</th> 
	</tr>
	<%
		if (eq_list != null) {
			for (Equipment e : eq_list) {
	%>
	<tr>
		<td>  <%=e.getEq_id()%>  </td>
		<td>  <%=e.getEq_lab()%>  </td>
		<td>  <%if(e.getEq_state()==0){%>停止<%}else{ %>运行<%} %></td>
			
	</tr>
	<%
		} //end for
			//session.removeAttribute("eq_list");
		} //end if
		else{
			out.print("xxx");
		}
	%>
</table>
</div>
<br/>


<div class="text" style=" text-align:center;">教师预约信息
<table border="1" align="center">
	<tr>
		<th>教师号</th>  <th>实验室</th>	 <th>时间</th>   
	<%
		if (tea_lab_list != null) {
			for (Tea_Lab t_l : tea_lab_list) {
	%>
	<tr>
		<td>  <%=t_l.getTea_id()%>  </td>
		<td>  <%=t_l.getLab_id()%>  </td>
		<td>  <%=t_l.getTea_lab_week()+"周星期"+t_l.getTea_lab_day()+"第"+t_l.getTea_lab_time()+"节"%>  </td>
		
		
	</tr>
	<%
		} //end for
			//session.removeAttribute("tea_lab_list");
		} //end if
		else{
			out.print("xxx");
		}
	%>
</table>
</div>
<br/>


<div class="text" style=" text-align:center;">学生预约信息
<table border="1" align="center">
	<tr>
		<th>学号</th>  <th>设备</th>	 <th>所在实验室</th> <th>时间</th>  
	</tr>
	<%
		if (stu_eq_list != null) {
			for (Stu_Eq stu_eq : stu_eq_list) {
	%>
	<tr>
		<td>  <%=stu_eq.getStu_id()%>  </td>
		<td>  <%=stu_eq.getEq_id()%>  </td>
		<td>  <%=stu_eq.getLab_id()%>  </td>
		<td>  <%=stu_eq.getStu_eq_week()+"周星期"+stu_eq.getStu_eq_day()+"第"+stu_eq.getStu_eq_time()+"节"%>  </td>
			
	</tr>
	<%
		} //end for
			//session.removeAttribute("stu_eq_list");
		} //end if
		else{
			out.print("xxx");
		}
	%>
</table>
</div>

</body>
</html>
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Stu;
import dao.StuDAO;
import beans.Tea;
import dao.TeaDAO;
/**
 * Servlet implementation class RegServlet
 */
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    		response.setContentType("text/html;charset=utf-8");
    		String st_flag=request.getParameter("st_flag");
    		if(st_flag.equals("stu")) {
	    		int stu_id=Integer.parseInt(request.getParameter("stu_id"));
	    		String stu_password=request.getParameter("stu_password");
	    		String stu_name= request.getParameter("stu_name");
	    		String stu_major= request.getParameter("stu_major");
	    		int stu_grade= Integer.parseInt(request.getParameter("stu_grade")) ;
	    		int stu_class= Integer.parseInt(request.getParameter("stu_class"));
	    		Stu stu=new Stu();
	    		stu.setStu_id(stu_id);;
	    		stu.setStu_name(stu_name);
	    		stu.setStu_password(stu_password);
	    		stu.setStu_major(stu_major);
	    		stu.setStu_grade(stu_grade);
	    		stu.setStu_class(stu_class);
	    		StuDAO studao=new StuDAO();
	    		
	    		if( studao.isStuidExists(stu_id)){
	    		request.getSession().setAttribute("err", "用户名已存在");
	    		response.sendRedirect("sturegister.jsp");
	    		}
	    		else{
	    		boolean flag=studao.addStu(stu);
	    		if(flag){
	    		request.getSession().setAttribute("err", "添加成功");
	    		HttpSession session = request.getSession();
				String admin_id=(String)session.getAttribute("admin_id");
				String admin_password=(String)session.getAttribute("admin_password");
				response.sendRedirect("AdminServlet?"+"id="+admin_id+"&password="+admin_password);
	    		}
	    		else{
	    		request.getSession().setAttribute("err", "注册失败");
	    		response.sendRedirect("sturegister.jsp");
	    		}
	    		}
    		}
    		else if(st_flag.equals("tea")){
    			int tea_id=Integer.parseInt(request.getParameter("tea_id"));
	    		String tea_password=request.getParameter("tea_password");
	    		String tea_name= request.getParameter("tea_name");
	    		
	    		Tea tea=new Tea();
	    		tea.setTea_id(tea_id);;
	    		tea.setTea_name(tea_name);
	    		tea.setTea_password(tea_password);
	    		
	    		TeaDAO teadao=new TeaDAO();
	    		
	    		if( teadao.isTeaidExists(tea_id)){
	    		request.getSession().setAttribute("err", "用户名已存在");
	    		response.sendRedirect("tearegister.jsp");
	    		}
	    		else{
	    		boolean flag=teadao.addTea(tea);
	    		if(flag){
	    		request.getSession().setAttribute("err", "添加成功");
	    		HttpSession session = request.getSession();
				String admin_id=(String)session.getAttribute("admin_id");
				String admin_password=(String)session.getAttribute("admin_password");
				response.sendRedirect("AdminServlet?"+"id="+admin_id+"&password="+admin_password);
	    		}
	    		else{
	    		request.getSession().setAttribute("err", "注册失败");
	    		response.sendRedirect("tearegister.jsp");
	    		}
	    		}
    		}
    		else {
    			request.getSession().setAttribute("err", "修改异常");
        		response.sendRedirect("register.jsp");
    		}
    		} // end service
	
}

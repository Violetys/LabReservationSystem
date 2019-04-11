package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Stu;
import beans.Tea;
import dao.StuDAO;
import dao.TeaDAO;

/**
 * Servlet implementation class SaveUserServlet
 */
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	response.setContentType("text/html;charset=utf-8");
    	String st_flag=request.getParameter("st_flag");
		if(st_flag.equals("stu")) {
			HttpSession session = request.getSession();
			int stu_id = Integer.parseInt(request.getParameter("stu_id"));
			String stu_name = request.getParameter("stu_name");
			String stu_password = request.getParameter("stu_password");
			String stu_major = request.getParameter("stu_major");
			int stu_grade = Integer.parseInt(request.getParameter("stu_grade"));
			int stu_class = Integer.parseInt(request.getParameter("stu_class"));
			Stu stu=new Stu(stu_id,stu_name,stu_password,stu_major,stu_grade,stu_class);
			boolean flag=new StuDAO().updateStu(stu);
			if(flag) {
				String admin_id=(String)session.getAttribute("admin_id");
				String admin_password=(String)session.getAttribute("admin_password");
				response.sendRedirect("AdminServlet?"+"id="+admin_id+"&password="+admin_password);
			}else {
				session.setAttribute("err", "保存失败，请重修修改");
				session.setAttribute("stu", stu);
				response.sendRedirect("stuedit.jsp");
			}
		}
		else if(st_flag.equals("tea")) {
			HttpSession session = request.getSession();
			int tea_id = Integer.parseInt(request.getParameter("tea_id"));
			String tea_name = request.getParameter("tea_name");
			String tea_password = request.getParameter("tea_password");
			Tea tea=new Tea(tea_id,tea_name,tea_password);
			boolean flag=new TeaDAO().updateTea(tea);
			if(flag) {
				String admin_id=(String)session.getAttribute("admin_id");
				String admin_password=(String)session.getAttribute("admin_password");
				response.sendRedirect("AdminServlet?"+"id="+admin_id+"&password="+admin_password);
			}else {
				session.setAttribute("err", "保存失败，请重修修改");
				session.setAttribute("tea", tea);
				response.sendRedirect("teaedit.jsp");
			}
		}
    	
		
		
}

}

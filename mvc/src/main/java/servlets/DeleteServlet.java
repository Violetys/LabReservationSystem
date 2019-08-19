package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StuDAO;
import dao.TeaDAO;
/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
		if(st_flag.equals("stu")) {//删除学生信息
			String stu_id=request.getParameter("stu_id");
			boolean flag=new StuDAO().delStuById(Integer.parseInt(stu_id));//实例化，用其中函数
			if(flag) {
				HttpSession session = request.getSession();
				String admin_id=(String)session.getAttribute("admin_id");
				String admin_password=(String)session.getAttribute("admin_password");
				response.sendRedirect("AdminServlet?"+"id="+admin_id+"&password="+admin_password);
			}
		}
		else if(st_flag.equals("tea")) {//删除教师信息
			String tea_id=request.getParameter("tea_id");
			boolean flag=new TeaDAO().delTeaById(Integer.parseInt(tea_id));//实例化，用其中函数
			if(flag) {
				HttpSession session = request.getSession();
				String admin_id=(String)session.getAttribute("admin_id");
				String admin_password=(String)session.getAttribute("admin_password");
				response.sendRedirect("AdminServlet?"+"id="+admin_id+"&password="+admin_password);
			}
		}
}
}

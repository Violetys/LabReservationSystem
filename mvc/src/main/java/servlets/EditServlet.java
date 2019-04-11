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
 * Servlet implementation class EditUserServlet
 */
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		String st_flag=request.getParameter("st_flag");
		if(st_flag.equals("stu")) {
			String stu_id=request.getParameter("stu_id");
			Stu stu=new StuDAO().getStuById(Integer.parseInt(stu_id));
			if(stu!=null) {
				session.setAttribute("stu", stu);
				response.sendRedirect("stuedit.jsp");
			}
		}
		else if(st_flag.equals("tea")) {
			String tea_id=request.getParameter("tea_id");
			Tea tea=new TeaDAO().getTeaById(Integer.parseInt(tea_id));
			if(tea!=null) {
				session.setAttribute("tea", tea);
				response.sendRedirect("teaedit.jsp");
			}
		}
		else {
			
		}
		}


}

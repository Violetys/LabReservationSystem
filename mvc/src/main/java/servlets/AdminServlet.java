package servlets;

import java.awt.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;
import dao.EqDAO;
import dao.LabDAO;
import dao.StuDAO;
import dao.Stu_EqDAO;
import dao.TeaDAO;
import dao.Tea_LabDAO;


/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	response.setContentType("text/html;charset=utf-8");
    	HttpSession session = request.getSession();
    	String admin_id=request.getParameter("id");
		String admin_password=request.getParameter("password");
		request.getSession().setAttribute("admin_id", admin_id);
		request.getSession().setAttribute("admin_password", admin_password);
		AdminDAO admindao=new AdminDAO();
		TeaDAO teadao= new TeaDAO();
		StuDAO studao= new StuDAO();
		LabDAO labdao= new LabDAO();
		EqDAO eqdao= new EqDAO();
		Stu_EqDAO stu_eqdao= new Stu_EqDAO();
		Tea_LabDAO tea_labdao= new Tea_LabDAO();
		boolean flag=admindao.findAdmin(admin_id, admin_password);
		if( flag ) {
		request.getSession().setAttribute("admin_id", admin_id);
		request.getSession().setAttribute("tea_list", teadao.getAllTeas());
		request.getSession().setAttribute("stu_list", studao.getAllStus());
		request.getSession().setAttribute("lab_list", labdao.getAllLabs());
		request.getSession().setAttribute("eq_list", eqdao.getAllEquipments());
		request.getSession().setAttribute("stu_eq_list", stu_eqdao.getAllStu_Eqs());
		request.getSession().setAttribute("tea_lab_list", tea_labdao.getAllTea_Labs());
		response.sendRedirect("admin.jsp");
		}
		else{
			session.setAttribute("err", "用户名或密码错误");
			response.sendRedirect("login.jsp");
		}
		
}

    

}

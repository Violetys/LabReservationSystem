package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Stu_Eq;
import beans.Tea_Lab;
import dao.StuDAO;
import dao.Stu_EqDAO;
import dao.TeaDAO;
import dao.Tea_LabDAO;
import net.sf.json.JSONObject;
/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteorderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteorderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	JSONObject jsonObject = new JSONObject();	//创建json
    	response.setContentType("text/html;charset=utf-8");
    	String st_flag=request.getParameter("st_flag");		//获取url中信息
		if(st_flag.equals("stu")) {			//flag是stu删除学生预约信息
			int stu_id=Integer.parseInt(request.getParameter("stu_id"));
			int eq_id=Integer.parseInt(request.getParameter("eq_id"));
			int lab_id=Integer.parseInt(request.getParameter("lab_id"));
			int stu_eq_week=Integer.parseInt(request.getParameter("stu_eq_week"));
			int stu_eq_day=Integer.parseInt(request.getParameter("stu_eq_day"));
			String stu_eq_time=request.getParameter("stu_eq_time");
			Stu_Eq stu_eq=new Stu_Eq(stu_id, eq_id, lab_id, stu_eq_week, stu_eq_day, stu_eq_time);
			
			boolean flag=new Stu_EqDAO().delstu_eq(stu_eq);		//实例化，用其中函数
			if(flag) {
				jsonObject.put("rs", "修改成功");
			}else {
				jsonObject.put("rs", "修改失败");
			}
		}
		else if(st_flag.equals("tea")) {//flag是tea删除教师预约信息
			int tea_id=Integer.parseInt(request.getParameter("tea_id"));
			int lab_id=Integer.parseInt(request.getParameter("lab_id"));
			int tea_lab_week=Integer.parseInt(request.getParameter("tea_lab_week"));
			int tea_lab_day=Integer.parseInt(request.getParameter("tea_lab_day"));
			String tea_lab_time=request.getParameter("tea_lab_time");
			Tea_Lab tea_lab=new Tea_Lab(tea_id,lab_id,tea_lab_week,tea_lab_day,tea_lab_time);
			boolean flag=new Tea_LabDAO().deltea_lab(tea_lab);//实例化，用其中函数
			if(flag) {
				jsonObject.put("rs", "修改成功");
			}else {
				jsonObject.put("rs", "修改失败");
			}
		}
		response.getWriter().print(jsonObject);
}
}

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Stu_Eq;
import beans.Tea_Lab;
import dao.StuDAO;
import dao.Stu_EqDAO;
import dao.Tea_LabDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
public class TeaorderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaorderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {				//教师预约
    		response.setContentType("text/html;charset=utf-8");
    		
    		JSONObject jsonObject = new JSONObject();
    		int tea_id=Integer.parseInt(request.getParameter("tea_id"));
    		int lab_id=Integer.parseInt(request.getParameter("lab_id"));
    		int tea_lab_week=Integer.parseInt(request.getParameter("tea_lab_week"));
    		int tea_lab_day=Integer.parseInt(request.getParameter("tea_lab_day"));
    		String tea_lab_time=request.getParameter("tea_lab_time");
    		Tea_Lab tea_lab=new Tea_Lab(tea_id,lab_id,tea_lab_week,tea_lab_day,tea_lab_time);
    		
    		Tea_LabDAO tea_labdao=new Tea_LabDAO();
    		boolean flag=tea_labdao.findtea_lab(tea_id, tea_lab_week, tea_lab_day, tea_lab_time);
    		if( !flag ) {
    		boolean x=tea_labdao.addtea_lab(tea_id, lab_id, tea_lab_week, tea_lab_day, tea_lab_time);
    		if(x) {	
	    		jsonObject.put("rs", "预约成功");
    		}
    		else {
    			jsonObject.put("rs", "预约失败");
    		}
    		}
    		else{
    		jsonObject.put("rs", "同一时间段只能预约一台设备");
    		}
    		response.getWriter().print(jsonObject);	
    		
    } 
    		
}

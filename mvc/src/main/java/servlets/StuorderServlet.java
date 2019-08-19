package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Stu_Eq;
import dao.StuDAO;
import dao.Stu_EqDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
public class StuorderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuorderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {				//学生预约
    		response.setContentType("text/html;charset=utf-8");
    		
    		JSONObject jsonObject = new JSONObject();
    		int stu_id=Integer.parseInt(request.getParameter("stu_id"));
    		int eq_id=Integer.parseInt(request.getParameter("eq_id"));
    		int lab_id=Integer.parseInt(request.getParameter("lab_id"));
    		int stu_eq_week=Integer.parseInt(request.getParameter("stu_eq_week"));
    		int stu_eq_day=Integer.parseInt(request.getParameter("stu_eq_day"));
    		String stu_eq_time=request.getParameter("stu_eq_time");
    		Stu_Eq stu_eq=new Stu_Eq(stu_id,eq_id,lab_id,stu_eq_week,stu_eq_day,stu_eq_time);
    		
    		Stu_EqDAO stu_eqdao=new Stu_EqDAO();
    		boolean flag=stu_eqdao.findstu_eq(stu_id, stu_eq_week, stu_eq_day, stu_eq_time);
    		if( !flag ) {
    		boolean x=stu_eqdao.addstu_eq(stu_eq);
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

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderableDAO;
import dao.StuDAO;
import dao.Stu_EqDAO;
import dao.TeaOrderableDAO;
import dao.Tea_LabDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
public class OrderedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {			//已经预约信息
    		response.setContentType("text/html;charset=utf-8");
    		JSONObject jsonObject = new JSONObject();
    		String st_flag=request.getParameter("st_flag");
    		if(st_flag.equals("stu")) {			//学生
    			int stu_id=Integer.parseInt(request.getParameter("stu_id"));
	    		Stu_EqDAO stu_eqdao=new Stu_EqDAO();		 			
	    		jsonObject.put("orderedeqs", stu_eqdao.getStu_EqsBystu(stu_id));
	    	}else {			//教师
	    		int tea_id=Integer.parseInt(request.getParameter("tea_id"));
	    		Tea_LabDAO ordereddao= new Tea_LabDAO();
	    		jsonObject.put("orderedlabs", ordereddao.getTea_LabsBytea(tea_id));
		    	
	    	}
    		
    		response.getWriter().print(jsonObject);		
    } 
    		
}

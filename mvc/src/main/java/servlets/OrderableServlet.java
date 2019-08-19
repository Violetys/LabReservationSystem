package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderableDAO;
import dao.StuDAO;
import dao.TeaOrderableDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
public class OrderableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {			//可预约信息
    		response.setContentType("text/html;charset=utf-8");
    		JSONObject jsonObject = new JSONObject();		//创建json
    		int selectweek=Integer.parseInt(request.getParameter("selectweek"));
    		int selectday=Integer.parseInt(request.getParameter("selectday"));
    		String selecttime=request.getParameter("selecttime");
    		String st_flag=request.getParameter("st_flag");
    		if(st_flag.equals("stu")) {					//学生可预约信息
	    		OrderableDAO orderdao=new OrderableDAO();		 			
	    		jsonObject.put("orderableeqs", orderdao.getOrderableEqs(selectweek, selectday, selecttime));
	    	}else {				//教师可预约信息
	    		TeaOrderableDAO teaorderdao= new TeaOrderableDAO();
	    		jsonObject.put("orderableeqs", teaorderdao.getOrderableLabs(selectweek, selectday, selecttime));
		    	
	    	}
    		
    		response.getWriter().print(jsonObject);		
    } 
    		
}

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EqDAO;
import dao.LabDAO;
import dao.StuDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
public class LabstateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LabstateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {			//更改实验室状态
    		response.setContentType("text/html;charset=utf-8");
    		JSONObject jsonObject = new JSONObject();
    		int lab_id=Integer.parseInt(request.getParameter("lab_id"));   		
    		int lab_state=Integer.parseInt(request.getParameter("lab_state"));
    		LabDAO labdao= new LabDAO();
    		boolean flag=labdao.changeLabstate(lab_id, lab_state);
    		if( flag ) {
    		jsonObject.put("rs", "切换成功");
    		}
    		else{
    		jsonObject.put("rs", "切换失败");
    		}
    		response.getWriter().print(jsonObject);		
    } 
    		
}

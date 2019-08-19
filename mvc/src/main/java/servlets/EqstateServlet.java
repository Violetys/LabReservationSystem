package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EqDAO;
import dao.StuDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
public class EqstateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EqstateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {				//更改设备状态
    		response.setContentType("text/html;charset=utf-8");
    		JSONObject jsonObject = new JSONObject();
    		int eq_id=Integer.parseInt(request.getParameter("eq_id"));
    		int eq_lab=Integer.parseInt(request.getParameter("eq_lab"));
    		int eq_state=Integer.parseInt(request.getParameter("eq_state"));
    		EqDAO eqdao= new EqDAO();
    		boolean flag=eqdao.ChangeEqstate(eq_id, eq_lab, eq_state);
    		if( flag ) {
    		jsonObject.put("rs", "切换成功");
    		}
    		else{
    		jsonObject.put("rs", "切换失败");
    		}
    		response.getWriter().print(jsonObject);		
    } 
    		
}

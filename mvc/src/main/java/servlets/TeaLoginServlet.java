package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.TeaDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
public class TeaLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {			//教师登录
    		response.setContentType("text/html;charset=utf-8");
    		JSONObject jsonObject = new JSONObject();
    		String tea_id=request.getParameter("tea_id");
    		String tea_password=request.getParameter("tea_password");
    		TeaDAO teadao=new TeaDAO();
    		boolean flag=teadao.findTea(tea_id, tea_password);
    		if( flag ) {
    		//request.getSession().setAttribute("tea_id", tea_id);
    		jsonObject.put("rs", "登陆成功");
    		}
    		else{
    		jsonObject.put("rs", "用户名或密码错误，请重新输入");
    		}
    		response.getWriter().print(jsonObject);		
    } 
    		
}

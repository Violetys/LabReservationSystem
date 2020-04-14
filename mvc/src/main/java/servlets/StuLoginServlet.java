package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Stu;
import dao.StuDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
public class StuLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {			//学生登录
    		response.setContentType("text/html;charset=utf-8");
    		JSONObject jsonObject = new JSONObject();
    		int stu_id=Integer.parseInt(request.getParameter("stu_id"));
    		String stu_password=request.getParameter("stu_password");
    		StuDAO studao=new StuDAO();
    		Stu stu=new Stu();
    		boolean flag=studao.findStu(stu_id, stu_password);
    		System.out.println(flag);
    		if( flag ) {
	    		stu=new StuDAO().getStuById(stu_id);
	    		System.out.println(stu);
	    		jsonObject.put("stuinfo", stu);
	    		jsonObject.put("week", 3);
    		}
    		else{
    			jsonObject.put("rs", "用户名或密码错误，请重新输入");
    		}
    		response.getWriter().print(jsonObject);		
    } 
    		
}

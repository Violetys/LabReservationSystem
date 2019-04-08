package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    		String username=request.getParameter("username");
    		String password=request.getParameter("password");
    		UserDAO userdao=new UserDAO();
    		boolean flag=userdao.findUser(username, password);
    		if( flag ) {
    		request.getSession().setAttribute("username", username);
    		response.sendRedirect("welcome.jsp");
    		}
    		else{
    		request.getSession().setAttribute("err", "用户名或密码不正确！");
    		response.sendRedirect("login.jsp");
    		} } 

}

package servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
public class AlertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	try {
    		ServerSocket ss=new ServerSocket(1000);
         //采用循环不断接受来自客户端的请求,服务器端也对应产生一个Socket
    		if(ss.isClosed()) {
	             Socket s=ss.accept();
	             OutputStream os=s.getOutputStream();
	             os.write("警报！\n".getBytes("utf-8"));
	             os.close();
	             s.close();
	             ss.close();
	             response.sendRedirect("admin.jsp");
    		}else {
    			ss.close();
    		}
    	}catch (IOException e) {
            e.printStackTrace();
        }
    } 
    		
}

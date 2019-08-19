package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.net.*;
 
class SocketServer extends Thread
{
	private Socket socket;
	public SocketServer(Socket socket)
	{
		this.socket = socket;
	}
 
	public void run()
	{
		String output = "";
		try
		{
			//Socket s=ss.accept();
            OutputStream os=socket.getOutputStream();
            os.write("警报！\n".getBytes("utf-8"));
            os.close();
            socket.close();	
            
		}
		catch (IOException ioex)
		{
			ioex.printStackTrace();
		}
	}
}
 
public class HelloWorld extends HttpServlet {
	private String message;
 
	public void init() throws ServletException
	{
		
		new Thread() {
			public void run()
			{
				try
				{
					ServerSocket server = new ServerSocket(30000);
					Socket socket = null;
					while (true)
					{
						socket = server.accept();
						SocketServer ss = new SocketServer(socket);
						ss.start();	
					}
				}
				catch (IOException ex)
				{
					System.out.println(ex.getMessage());
				}
			}
		}.start();
  }
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
	  response.setHeader("refresh","3;URL=admin.jsp");
     //response.sendRedirect("admin.jsp");
  }
  
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
     doGet(request, response);
  }
 
  
  public void destroy()
  {
  }
}

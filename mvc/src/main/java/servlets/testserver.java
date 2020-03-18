package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ServerThread;
import beans.Stu_Eq;
import dao.Stu_EqDAO;
import net.sf.json.JSONObject;

public class testserver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testserver() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {				//服务器警报发出
    	try {
			//创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket=new ServerSocket(30000);
            Socket socket=null;
            //记录客户端的数量
            int count=0;
            System.out.println("***服务器警报启动，等待客户端的连接***");
            //循环监听等待客户端的连接
            while(true){
                //调用accept()方法开始监听，等待客户端的连接
                socket=serverSocket.accept();
                //创建一个新的线程
                ServerThread serverThread=new ServerThread(socket);
                //启动线程
                serverThread.start();

                count++;//统计客户端的数量
                System.out.println("客户端的数量："+count);
                
                InetAddress address=socket.getInetAddress();               
                System.out.println("当前客户端的IP："+address.getHostAddress());
                
               
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    } 
    		
}

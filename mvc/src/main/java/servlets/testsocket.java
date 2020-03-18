package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ServerThread;
import net.sf.json.JSONObject;


public class testsocket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testsocket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {				//警报客户端servlet
    		response.setContentType("text/html;charset=utf-8");	//解决后台Json到页面中中文问号显示的问题
    	try {
			//创建客户端Socket，指定服务器地址和端口
			Socket socket=new Socket("192.168.31.230",30000);
            //获取输出流，向服务器端发送信息
//            OutputStream os=socket.getOutputStream();//字节输出流
//            PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
//            pw.write("测试客户端发出信息");
//            pw.flush();
//            socket.shutdownOutput();//关闭输出流
            //获取输入流，并读取服务器端的响应信息
            InputStream is=socket.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String info=null;
            while((info=br.readLine())!=null){
            	
            	JSONObject jsonObject = new JSONObject();
            	jsonObject.put("msg", info);
            	response.getWriter().print(jsonObject);
            }
            //关闭资源
            br.close();
            is.close();
//            pw.close();
//            os.close();
            socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
    		
    } 
    		
}

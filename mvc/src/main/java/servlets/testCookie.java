package servlets;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.JedisPoolUtil;
import beans.SerializeUtil;
import beans.Stu;
import dao.StuDAO;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/*
 * 测试使用
 * */
public class testCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
    	response.setContentType("text/html;charset=utf-8");		//设置编码格式
    	Jedis jedis=null;
    	JedisPool jedisPool=JedisPoolUtil.getInstance();
    	
    	JSONObject jsonObject = new JSONObject();
		int stu_id=Integer.parseInt(request.getParameter("stu_id"));
		String stu_password=request.getParameter("stu_password");
		StuDAO studao=new StuDAO();
		Stu stu=new Stu();
		boolean flag=studao.findStu(stu_id, stu_password);
		if( flag ) {
		stu=studao.getStuById(stu_id);
		
		String token = UUID.randomUUID().toString();
		Cookie cookie=new Cookie("xys.com_user_id",token);
		cookie.setMaxAge(300); //存活期为300秒
 		response.addCookie(cookie);
		
 		
 		jedis=jedisPool.getResource();		
		//jedis.setex("user_info:"+token, 300, stu.getStu_id()+"");
		jedis.setex(("user_info:"+token).getBytes(), 300, SerializeUtil.serialize(stu));
		jedis.close();
		
		
		
		request.setAttribute("stu", stu);
		request.getRequestDispatcher("cookie2.jsp").forward(request, response);
		
		}
		else{
		jsonObject.put("rs", "用户名或密码错误，请重新输入");
		}
		response.getWriter().print(jsonObject);
			
//			//String user_id=request.getParameter("user_id");
//    		String token = UUID.randomUUID().toString();
//    		Cookie cookie=new Cookie("xys.com_user_id",token);
//    		cookie.setMaxAge(300); //存活期为300秒
//     		response.addCookie(cookie);
// //   		request.setAttribute("token", token);
//     		System.out.println("sevlet:"+cookie.getName()+" "+cookie.getValue());
//    		
//    		Jedis jedis=new Jedis("127.0.0.1",6379);
//    		jedis.auth("xc98923iverson");
//    		jedis.setex("user_info:"+token, 300, new Tea(123,"zxc","asdasd").toString());
//			
//
//    		request.getRequestDispatcher("cookie2.jsp").forward(request, response);
//    		
			
			
			
    		
    		
    } 
    		
}

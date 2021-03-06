package servlets;

import java.awt.List;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Admin;
import beans.JedisPoolUtil;
import beans.SerializeUtil;
import dao.AdminDAO;
import dao.EqDAO;
import dao.LabDAO;
import dao.StuDAO;
import dao.Stu_EqDAO;
import dao.TeaDAO;
import dao.Tea_LabDAO;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	response.setContentType("text/html;charset=utf-8");		//设置编码格式
    	HttpSession session = request.getSession();
    	
    	Jedis jedis=null;
    	JedisPool jedisPool=JedisPoolUtil.getInstance();
    	    	
    	String admin_faceid;
    	AdminDAO admindao=new AdminDAO();	//实例化admin
		TeaDAO teadao= new TeaDAO();//实例化...
		StuDAO studao= new StuDAO();//实例化...
		LabDAO labdao= new LabDAO();//实例化...
		EqDAO eqdao= new EqDAO();//实例化...
		Stu_EqDAO stu_eqdao= new Stu_EqDAO();//实例化...
		Tea_LabDAO tea_labdao= new Tea_LabDAO();//实例化...
    	if((admin_faceid=request.getParameter("face_id"))!= null) {
    		request.getSession().setAttribute("admin_id", admin_faceid);	//session存adminid
    		request.getSession().setAttribute("tea_list", teadao.getAllTeas());//session存teacher信息
    		request.getSession().setAttribute("stu_list", studao.getAllStus());//session存student信息
    		request.getSession().setAttribute("lab_list", labdao.getAllLabs());//session存lab信息
    		request.getSession().setAttribute("eq_list", eqdao.getAllEquipments());//...
    		request.getSession().setAttribute("stu_eq_list", stu_eqdao.getAllStu_Eqs());
    		request.getSession().setAttribute("tea_lab_list", tea_labdao.getAllTea_Labs());
    		
    		Admin ad=admindao.getAdById(admin_faceid);
			request.setAttribute("ad", ad);
			
			
			String token = UUID.randomUUID().toString();
			Cookie cookie=new Cookie("xys.com_user_id",token);
			cookie.setMaxAge(300); //存活期为300秒
	 		response.addCookie(cookie);
			
	 		
	 		jedis=jedisPool.getResource();		
			//jedis.setex("user_info:"+token, 300, stu.getStu_id()+"");
			jedis.setex(("user_info:"+token).getBytes(), 300, SerializeUtil.serialize(ad));
			jedis.close();
			
			
			response.sendRedirect("admin.jsp");//跳到admin.jsp
			//request.setAttribute("ad", ad);
			//request.getRequestDispatcher("admin.jsp").forward(request, response);
    		
    	}else {
	    	String admin_id=request.getParameter("id");			//获取url中的信息
			String admin_password=request.getParameter("password");			//获取url中的信息
			request.getSession().setAttribute("admin_id", admin_id);//用session存信息
			request.getSession().setAttribute("admin_password", admin_password);
			
			boolean flag=admindao.findAdmin(admin_id, admin_password);//验证用户名密码			
			if( flag ) {		//验证成功
			request.getSession().setAttribute("admin_id", admin_id);	//session存adminid
			request.getSession().setAttribute("tea_list", teadao.getAllTeas());//session存teacher信息
			request.getSession().setAttribute("stu_list", studao.getAllStus());//session存student信息
			request.getSession().setAttribute("lab_list", labdao.getAllLabs());//session存lab信息
			request.getSession().setAttribute("eq_list", eqdao.getAllEquipments());//...
			request.getSession().setAttribute("stu_eq_list", stu_eqdao.getAllStu_Eqs());
			request.getSession().setAttribute("tea_lab_list", tea_labdao.getAllTea_Labs());
			
			Admin ad=admindao.getAdById(admin_id);
			//request.setAttribute("ad", ad);
			
			
			String token = UUID.randomUUID().toString();
			Cookie cookie=new Cookie("xys.com_user_id",token);
			cookie.setMaxAge(300); //存活期为300秒
	 		response.addCookie(cookie);
			
	 		
	 		jedis=jedisPool.getResource();		
			//jedis.setex("user_info:"+token, 300, stu.getStu_id()+"");
			jedis.setex(("user_info:"+token).getBytes(), 300, SerializeUtil.serialize(ad));
			jedis.close();
			
			
			
			//request.setAttribute("ad", ad);
			//request.getRequestDispatcher("admin.jsp").forward(request, response);
			
			
			
			response.sendRedirect("admin.jsp");//跳到admin.jsp
			}
			else{	//验证失败
				session.setAttribute("err", "用户名或密码错误");
				response.sendRedirect("login.jsp");
			}
    	}
		
}

    

}

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.SerializeUtil;
import beans.Stu;
import beans.Tea;
import beans.Admin;
import beans.JedisPoolUtil;

import dao.AdminDAO;
import dao.EqDAO;
import dao.LabDAO;
import dao.StuDAO;
import dao.Stu_EqDAO;
import dao.TeaDAO;
import dao.Tea_LabDAO;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisAutoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedisAutoLogin() {
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
    	String token=null;
    	
    	if((token=(String) request.getAttribute("token"))!=null) {
    		jedis=jedisPool.getResource();
    		Object obj=SerializeUtil.unSerialize(jedis.get(("user_info:"+token).getBytes()));
    			
    		if(obj.getClass()==Admin.class) {						//判断是否是管理员
    			AdminDAO admindao=new AdminDAO();	
    			TeaDAO teadao= new TeaDAO();
    			StuDAO studao= new StuDAO();
    			LabDAO labdao= new LabDAO();
    			EqDAO eqdao= new EqDAO();
    			Stu_EqDAO stu_eqdao= new Stu_EqDAO();
    			Tea_LabDAO tea_labdao= new Tea_LabDAO();
    			  			
    			Admin ad=(Admin) obj;
    			request.getSession().setAttribute("admin_id", ad.getAd_id());	//session存adminid
    			request.getSession().setAttribute("tea_list", teadao.getAllTeas());//session存teacher信息
    			request.getSession().setAttribute("stu_list", studao.getAllStus());//session存student信息
    			request.getSession().setAttribute("lab_list", labdao.getAllLabs());//session存lab信息
    			request.getSession().setAttribute("eq_list", eqdao.getAllEquipments());//...
    			request.getSession().setAttribute("stu_eq_list", stu_eqdao.getAllStu_Eqs());
    			request.getSession().setAttribute("tea_lab_list", tea_labdao.getAllTea_Labs());
			
    			//request.setAttribute("admin", ad);   
    			
    			
    			request.getRequestDispatcher("admin.jsp").forward(request, response);
    		}if(obj.getClass()==Stu.class) {    								//判断是否是学生
	    		Stu stu=(Stu) obj;
	    		System.out.println(stu);
	    		request.setAttribute("stu", stu);
	    		request.getRequestDispatcher("cookie2.jsp").forward(request, response);
    		}if(obj.getClass()==Tea.class) {									//判断是否是教师
    			Tea tea=(Tea) obj;
	    		request.setAttribute("tea", tea);
	    		request.getRequestDispatcher("cookie2.jsp").forward(request, response);
    		}
    		
    		jedis.close();
    	}
    		
    		
    } 
    		
}








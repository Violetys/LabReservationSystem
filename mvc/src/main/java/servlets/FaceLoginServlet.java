package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import beans.AccountIfo;
import beans.Image;
import beans.SerquestIfo;
import faceuntils.DetectionFace;
import faceuntils.UserFace;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class FaceLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaceLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, HaiYi-Access-Token");
		/*
		 * 验证登录
		 */
		//从数据库查询数据
		JSONObject resjson= new JSONObject();
		AccountIfo user= new AccountIfo();
		SerquestIfo reques=new SerquestIfo();//请求对象
		Image imageU=new Image();
		//将账号密码放入 账户信息 对象
		imageU.setImage(request.getParameter("imgpath"));
		imageU.setImageType(request.getParameter("imgType"));
		reques.setPage(request.getParameter("page"));
		reques.setUser(user);
		reques.setImageU(imageU);
		if("2".equals(reques.getPage())){//人脸登录
			resjson=Faclogin(reques.getImageU());
		}
		else{
			
		}
		response.getWriter().print(resjson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/* @SuppressWarnings("unchecked")
	public static JSONObject Paslogin(AccountIfo user){//密码登录
		Connection conn=Dbutil.getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/ddserver", "root", "");
		String sql="select account from UserIfo where account=? and password=?";
		String param[] = new String[2];
		JSONObject json=new JSONObject();
		param[0]=user.getDd_account();
		param[1]=user.getDd_password();
		ResultSet res=Dbutil.executQuery(conn, sql, param);
		json.put("error_msg", "error");
		try {
			while(res.next()){
				json.put("error_msg", "SUCCESS");
				json.put("user_id",user.getDd_account());
				break;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			json.put("error_msg", "身份信息不匹配");
		}
		
		return json;
	}*/
	 @SuppressWarnings("unchecked")
	public static JSONObject Faclogin(Image imageU){//人脸登录
		 JSONObject json=new JSONObject();
		if(imageU!=null){
			JSONObject dfjson=DetectionFace.Detection(imageU);//合格照片检查
			if("SUCCESS".equals(dfjson.get("error_msg"))){
				JSONObject uf=UserFace.User(imageU);//提交
				if("SUCCESS".equals(uf.get("error_msg").toString())){//查询出结果
					Double score=new Double(uf.get("score").toString());
					if(score>70){
						json.put("error_msg", "SUCCESS");
						json.put("user_id", uf.get("user_id").toString());
						
					}
					else {
						//
						json.put("error_msg", "登录失败");
					}
				}else{
					//
					json.put("error_msg", "没有人脸数据");
				}
			}
			else{
				json.put("error_msg", "人脸不合格");
			}
		}
		return json;
	}
	public String Reglogin(){//注册
		
		return "";
	}

}

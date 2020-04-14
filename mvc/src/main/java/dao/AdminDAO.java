package dao; 
import java.sql.*;
import java.util.*;

import beans.Admin;
import beans.Stu;
import beans.Tea;
import beans.MysqlConn;

public class AdminDAO {			//管理员DAO
	
	Connection conn=null;
	private PreparedStatement pStat=null;
	private ResultSet rs=null;
	
	public void close() {
		try{
			if( rs!=null ) rs.close();
			if( pStat!=null ) pStat.close();
			if( conn!=null ) conn.close();
			}catch(Exception e){ e.printStackTrace(); }
	}
	
	public boolean findAdmin(String ad_id, String ad_password){			//查询验证用户名密码用于管理员登录
		Connection conn=MysqlConn.getConnectionn();
		try {
			pStat =conn.prepareStatement("select * from admin where ad_id=? and ad_password=?");	//sql语句
			pStat.setString(1, ad_id);		//设置上述sql语句中第一个？的值
			pStat.setString(2, ad_password);	//设置上述sql语句中第二个？的值
			rs=pStat.executeQuery();	//执行sql语句
			if( rs.next() ) return true;	//如果有结果返回true
			else return false;		//否则换回false
		}
		catch (Exception e) { return false; }
		finally{
			close();
		}
	} //end findUser
	
	public String getAdminName(String ad_id) {
		Connection conn=MysqlConn.getConnectionn();
		try {
			pStat =conn.prepareStatement("select ad_name from admin where ad_id=?");	//sql语句
			pStat.setString(1, ad_id);		//设置上述sql语句中第一个？的值		
			rs=pStat.executeQuery();	//执行sql语句
			if( rs.next() ) return rs.getString("ad_name");	//如果有结果返回true
			else return "";		//否则换回false
		}
		catch (Exception e) { return ""; }
		finally{
			close();
		}
		
	}
	
	public Admin getAdById(String ad_id) {			//根据学号查询学生信息，返回值为学生对象
		Connection conn=MysqlConn.getConnectionn();
		try {
			pStat = conn.prepareStatement("select * from admin where ad_id=?");
			pStat.setString(1, ad_id);
			rs= pStat.executeQuery();	
			if(rs.next()) {
				Admin ad=new Admin(
						rs.getString("ad_id"),
						rs.getString("ad_name"),
						rs.getString("ad_password")					
						);				
				return ad;			//返回
			}
			else 
				return null;
		} catch (Exception e) {
			return null;
		} finally {
			close();
		}
	}
	
	
}
package dao; 
import java.sql.*;
import java.util.*;
import beans.Tea;
public class AdminDAO {			//管理员DAO
public static final String DRIVER="org.gjt.mm.mysql.Driver";		//数据库连接
public static final String DBURL="jdbc:mysql://localhost:3306/lab";
public static final String DBUSER="root";
public static final String DBPASS="xys9811xc98923";
//private static final Stu  = null;
private Connection conn=null;
private PreparedStatement pStat=null;
private ResultSet rs=null;
public Connection getConnectionn(){
try{
Class.forName(DRIVER).newInstance();
return DriverManager.getConnection(DBURL,DBUSER,DBPASS);
}catch(Exception e){
return null;
}
}
public void close(){
try{
if( rs!=null ) rs.close();
if( pStat!=null ) pStat.close();
if( conn!=null ) conn.close();
}catch(Exception e){ e.printStackTrace(); }
} //end close

public boolean findAdmin(String ad_id, String ad_password){			//查询验证用户名密码用于管理员登录
	conn=getConnectionn();
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


}
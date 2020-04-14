package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MysqlConn {
	public static final String DRIVER="org.gjt.mm.mysql.Driver";		//数据库连接
	public static final String DBURL="jdbc:mysql://localhost:3306/lab";
	public static final String DBUSER="root";
	public static final String DBPASS="xys9811xc98923";
	//private static final Stu  = null;
	private Connection conn=null;
	private PreparedStatement pStat=null;
	private ResultSet rs=null;
	
public static Connection getConnectionn(){
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

}

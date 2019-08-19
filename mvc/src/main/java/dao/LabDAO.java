package dao; 
import java.sql.*;
import java.util.*;

import beans.Lab;
import beans.Tea;

public class LabDAO {		//实验室dao
public static final String DRIVER="org.gjt.mm.mysql.Driver";
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

public boolean findLab(String lab_id){		//查询实验室是否存在
	conn=getConnectionn();
	try {
	pStat =conn.prepareStatement("select * from labs where lab_id=?");		//sql语句
	pStat.setString(1, lab_id);					//设置？的值
	rs=pStat.executeQuery();
	if( rs.next() ) return true;
	else return false;
	}
	catch (Exception e) { return false; }
	finally{
	close();
	}
} //end findUser

public boolean changeLabstate(int lab_id,int lab_state){		//改变设备状态
	conn=getConnectionn();
	try {
	pStat =conn.prepareStatement("UPDATE `labs` SET `lab_state`=? WHERE `lab_id`=?");	//sql语句
	pStat.setInt(1, lab_state);			//设置第一个？的值
	pStat.setInt(2, lab_id);			//设置第二个？的值
	int cnt= pStat.executeUpdate();			//执行
	if(cnt>0) 			//影响行数大于0 返回true
		return true;
	else 
		return false;
	}
	catch (Exception e) { return false; }
	finally{
	close();
	}
} 

public List<Lab> getAllLabs() {			//返回所有lab信息
	List<Lab> lab_list = new ArrayList<Lab>();    // 结果集存放在List集合中
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("select * from labs where 1");		//取得所有信息
		rs = pStat.executeQuery();
		while (rs.next()) {
			int lab_id = rs.getInt("lab_id");
			int lab_state = rs.getInt("lab_state");
			
			
			lab_list.add(new Lab(lab_id, lab_state));		//存入list
		}
		return lab_list;		//返回list
	} catch (Exception e) {
		return null;
	} finally {
		close();
	}
}

}
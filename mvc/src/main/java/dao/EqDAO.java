package dao; 
import java.sql.*;
import java.util.*;

import beans.Equipment;


public class EqDAO {		//设备DAO
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

public boolean findEq(String eq_id){		//查询设备是否存在
	conn=getConnectionn();
	try {
	pStat =conn.prepareStatement("select * from equipments where eq_id=?");  //sql语句
	pStat.setString(1, eq_id);		//设置sql语句？的值
	rs=pStat.executeQuery();		//执行sql语句
	if( rs.next() ) return true;
	else return false;
	}
	catch (Exception e) { return false; }
	finally{
	close();
	}
} 

public boolean ChangeEqstate(int eq_id, int eq_lab, int eq_state){		//改变设备状态
	conn=getConnectionn();
	try {
	pStat =conn.prepareStatement("UPDATE equipments SET eq_state=? WHERE eq_id=? and eq_lab=?");	//用于改变状态的sql语句
	pStat.setInt(1, eq_state);		//设置sql语句中第一个？的值
	pStat.setInt(2, eq_id);			//设置sql语句中第二个？的值
	pStat.setInt(3, eq_lab);		//设置sql语句中第三个？的值
	int cnt= pStat.executeUpdate();		//执行
	if(cnt>0) 				//若影响行数大于0  说明sql执行成功 返回true
		return true;
	else 
		return false;
	}
	catch (Exception e) { return false; }
	finally{
	close();
	}
}

public List<Equipment> getAllEquipments() {			//获取所有设备信息
	List<Equipment> eq_list = new ArrayList<Equipment>();    // 结果集存放在List集合中
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("select * from equipments where 1");	//...
		rs = pStat.executeQuery();		//...
		while (rs.next()) {			
			int eq_id = rs.getInt("eq_id");	//从执行得到的结果中吧设备号，设备状态，所在实验室取出来
			int eq_state = rs.getInt("eq_state");
			int eq_lab= rs.getInt("eq_lab");
			
			eq_list.add(new Equipment(eq_id, eq_state, eq_lab));	//将得到的信息存入list中
		}
		return eq_list;				//返回list
	} catch (Exception e) {
		return null;
	} finally {
		close();
	}
}

}
package dao; 
import java.sql.*;
import java.util.*;

import beans.Equipment;
import beans.Stu_Eq;


public class OrderableDAO {				//可预约信息DAO
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

public List<Equipment> getAllEqs() {		
	List<Equipment> eq_list = new ArrayList<Equipment>();    // 结果集存放在List集合中
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("select * from equipments where 1");
		rs = pStat.executeQuery();
		while (rs.next()) {	
			int eq_id = rs.getInt("eq_id");
			int eq_lab= rs.getInt("eq_lab");
			int eq_state= rs.getInt("eq_state");
			eq_list.add(new Equipment(eq_id, eq_state, eq_lab));
		}
		return eq_list;
	} catch (Exception e) {
		return null;
	} finally {
		close();
	}
}

public List<Equipment> getOrderableEqs(int selectweek, int selectday, String selecttime) {		//返回所有可预约设备
	List<Equipment> eq_list = new ArrayList<Equipment>();    // 结果集存放在List集合中
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("select * from equipments,stu_eq where stu_eq.stu_eq_week=? and stu_eq.stu_eq_day=? and stu_eq.stu_eq_time=?");		//查询在此时间已经被预约的设备
		pStat.setInt(1, selectweek);
		pStat.setInt(2, selectday);
		pStat.setString(3, selecttime);
		rs = pStat.executeQuery();
		if(rs.next()) {		//若查询到这个时间段有被预约过的设备，再通过设备表查询除这些设备外的设备号，就是可被预约的设备
			pStat = conn.prepareStatement("select equipments.eq_id,equipments.eq_lab,equipments.eq_state from equipments,stu_eq where stu_eq.stu_eq_week=? and stu_eq.stu_eq_day=? and stu_eq.stu_eq_time=? and (stu_eq.eq_id!=equipments.eq_id or stu_eq.lab_id!=equipments.eq_lab)");
			pStat.setInt(1, selectweek);
			pStat.setInt(2, selectday);
			pStat.setString(3, selecttime);
			rs = pStat.executeQuery();
			if(!rs.wasNull()) {		//存入list返回
				while (rs.next()) {	
					int eq_id = rs.getInt("eq_id");
					int eq_lab= rs.getInt("eq_lab");
					int eq_state= rs.getInt("eq_state");
					eq_list.add(new Equipment(eq_id, eq_state, eq_lab));
				}
			}
		}else {		//若此时间段没有被预约的设备就返回所有设备信息，说明都可以被预约
			pStat = conn.prepareStatement("select * from equipments where 1");
			rs = pStat.executeQuery();
			if(!rs.wasNull()) {
				while (rs.next()) {	
					int eq_id = rs.getInt("eq_id");
					int eq_lab= rs.getInt("eq_lab");
					int eq_state= rs.getInt("eq_state");
					eq_list.add(new Equipment(eq_id, eq_state, eq_lab));
				}
			}
		}
		return eq_list;
	} catch (Exception e) {
		return null;
	} finally {
		close();
	}
}


}
package dao; 
import java.sql.*;
import java.util.*;

import beans.Equipment;
import beans.Stu_Eq;
import beans.Tea_Lab;


public class Tea_LabDAO {
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

public boolean findtea_lab(int tea_id, int tea_lab_week, int tea_lab_day, String tea_lab_time){		//此处注释与Stu_LabDAO注释类似
	conn=getConnectionn();
	try {
	pStat =conn.prepareStatement("select * from tea_lab where tea_id=? and tea_lab_week=? and tea_lab_day=? and tea_lab_time=?");
	pStat.setInt(1, tea_id);
	pStat.setInt(2, tea_lab_week);
	pStat.setInt(3, tea_lab_day);
	pStat.setString(4, tea_lab_time);
	rs=pStat.executeQuery();
	if( rs.next() ) { 		
		return true;
	}
	else return false;
	}
	catch (Exception e) { return false; }
	finally{
	close();
	}
} 

public boolean deltea_lab(Tea_Lab tea_lab) { 		//删除教师预约信息
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("delete from tea_lab where tea_id=? and lab_id=? and tea_lab_week=? and tea_lab_day=? and tea_lab_time=?");
		pStat.setInt(1, tea_lab.getTea_id());
		pStat.setInt(2, tea_lab.getLab_id());
		pStat.setInt(3, tea_lab.getTea_lab_week());
		pStat.setInt(4, tea_lab.getTea_lab_day());
		pStat.setString(5, tea_lab.getTea_lab_time());
		
		int cnt= pStat.executeUpdate();	
		if(cnt>0) 
			return true;
		else 
			return false;
	} catch (Exception e) {
		return false;
	} finally {
		close();
	}
}


public boolean addtea_lab(int tea_id,int lab_id, int tea_lab_week, int tea_lab_day, String tea_lab_time){		//添加教师预约信息
	conn=getConnectionn();
	try {
	pStat =conn.prepareStatement("INSERT INTO `tea_lab`(`tea_id`, `lab_id`,`tea_lab_week`,`tea_lab_day`, `tea_lab_time`) VALUES (?,?,?,?,?)");
	pStat.setInt(1, tea_id);
	pStat.setInt(2, lab_id);
	pStat.setInt(3, tea_lab_week);
	pStat.setInt(4, tea_lab_day);
	pStat.setString(5, tea_lab_time);
	int cnt=pStat.executeUpdate();
	if(cnt>0) return true;
	else return false;
	}
	catch (Exception e) { return false; }
	finally{
	close();
	}
} 

public List<Tea_Lab> getAllTea_Labs() {				//查询所有教师预约信息
	List<Tea_Lab> tea_lab_list = new ArrayList<Tea_Lab>();    // 结果集存放在List集合中
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("select * from tea_lab where 1");
		rs = pStat.executeQuery();
		while (rs.next()) {
			int tea_id = rs.getInt("tea_id");
			int lab_id = rs.getInt("lab_id");
			int tea_lab_week= rs.getInt("tea_lab_week");
			int tea_lab_day= rs.getInt("tea_lab_day");
			String tea_lab_time= rs.getString("tea_lab_time");
			tea_lab_list.add(new Tea_Lab(tea_id, lab_id, tea_lab_week, tea_lab_day, tea_lab_time));
		}
		return tea_lab_list;
	} catch (Exception e) {
		return null;
	} finally {
		close();
	}
}


public List<Tea_Lab> getTea_LabsBytea(int tea_id1) {			//根据教师号查询预约信息
	List<Tea_Lab> tea_lab_list = new ArrayList<Tea_Lab>();    
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("select * from tea_lab where tea_id=?");
		pStat.setInt(1, tea_id1);
		rs = pStat.executeQuery();
		while (rs.next()) {
			int tea_id = rs.getInt("tea_id");
			int lab_id = rs.getInt("lab_id");
			int tea_lab_week= rs.getInt("tea_lab_week");
			int tea_lab_day= rs.getInt("tea_lab_day");
			String tea_lab_time= rs.getString("tea_lab_time");
			tea_lab_list.add(new Tea_Lab(tea_id, lab_id, tea_lab_week, tea_lab_day, tea_lab_time));
		}
		return tea_lab_list;
	} catch (Exception e) {
		return null;
	} finally {
		close();
	}
}


}
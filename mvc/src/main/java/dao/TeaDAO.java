package dao; 
import java.sql.*;
import java.util.*;

import beans.Stu;
import beans.Tea;
public class TeaDAO {
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
public boolean isTeaidExists(int id) {
	conn=getConnectionn();
	try {
	pStat =conn.prepareStatement("select * from teachers where tea_id=?");
	pStat.setInt(1, id);
	rs=pStat.executeQuery();
	if( rs.next() ) return true;
	else return false;
	}catch (Exception e) { return false; }
	finally{ close(); }
} //end isTeaidExists

public boolean findTea(String tea_id, String tea_password){
	conn=getConnectionn();
	try {
	pStat =conn.prepareStatement("select * from teachers where tea_id=? and tea_password=?");
	pStat.setString(1, tea_id);
	pStat.setString(2, tea_password);
	rs=pStat.executeQuery();
	if( rs.next() ) return true;
	else return false;
	}
	catch (Exception e) { return false; }
	finally{
	close();
	}
} //end findUser

public boolean addTea(Tea tea) {
	conn=getConnectionn();
	try {
	pStat=conn.prepareStatement("insert into teachers values(?,?,?)");
	pStat.setInt(1, tea.getTea_id());
	pStat.setString(2, tea.getTea_name());
	pStat.setString(3, tea.getTea_password());
	int cnt=pStat.executeUpdate();
	if(cnt>0) return true;
	else return false;
	}
	catch (Exception e) { return false; }
	finally{
	close();
	}
	} //end add


public boolean updateTea(Tea tea) {
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("UPDATE `teachers` SET `tea_name`=?,`tea_password`=? where `tea_id`=?");
		pStat.setInt(3, tea.getTea_id());
		pStat.setString(1, tea.getTea_name());
		pStat.setString(2, tea.getTea_password());
		
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

public List<Tea> getAllTeas() {
	List<Tea> tmp_list = new ArrayList<Tea>();    // 结果集存放在List集合中
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("select * from teachers where 1");
		rs = pStat.executeQuery();
		while (rs.next()) {
			int tea_id = rs.getInt("tea_id");
			String tea_name = rs.getString("tea_name");
			String tea_password = rs.getString("tea_password");
			
			tmp_list.add(new Tea(tea_id, tea_name, tea_password));
		}
		return tmp_list;
	} catch (Exception e) {
		return null;
	} finally {
		close();
	}
}


public boolean delTeaById(int id) {
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("delete from teachers where tea_id=?");
		pStat.setInt(1, id);
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


public Tea getTeaById(int id) {
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("select * from teachers where tea_id=?");
		pStat.setInt(1, id);
		rs= pStat.executeQuery();	
		if(rs.next()) {
			Tea tea=new Tea(
					rs.getInt("tea_id"),
					rs.getString("tea_name"),
					rs.getString("tea_password")					
					);				
			return tea;
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
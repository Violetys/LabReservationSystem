package dao; 
import java.sql.*;
import java.util.*;

import beans.Equipment;
import beans.MysqlConn;
import beans.Stu_Eq;


public class Stu_EqDAO {	//学生预约DAO
	Connection conn=null;
	private PreparedStatement pStat=null;
	private ResultSet rs=null;
	public void close(){
		try{
		if( rs!=null ) rs.close();
		if( pStat!=null ) pStat.close();
		if( conn!=null ) conn.close();
		}catch(Exception e){ e.printStackTrace(); }
	} //end close
	
	public boolean addstu_eq(Stu_Eq stu_eq){		//吧预约信息添加到学生预约表，形参为学生预约类
		Connection conn=MysqlConn.getConnectionn();
	
		try {
		pStat =conn.prepareStatement("insert into stu_eq values(?,?,?,?,?,?)");
		pStat.setInt(1, stu_eq.getStu_id());		//设置第一个？的值
		pStat.setInt(2, stu_eq.getEq_id());			//设置第二个？的值
		pStat.setInt(3, stu_eq.getLab_id());		//...
		pStat.setInt(4, stu_eq.getStu_eq_week());
		pStat.setInt(5, stu_eq.getStu_eq_day());
		pStat.setString(6, stu_eq.getStu_eq_time());
		
		int cnt=pStat.executeUpdate();
		if(cnt>0) return true;
		else return false;
		}
		catch (Exception e) { return false; }
		finally{
		close();
		}
	} 
	
	public boolean delstu_eq(Stu_Eq stu_eq) {		//删除学生预约表信息
		Connection conn=MysqlConn.getConnectionn();
		try {
			pStat = conn.prepareStatement("delete from stu_eq where stu_id=? and eq_id=? and lab_id=? and stu_eq_week=? and stu_eq_day=? and stu_eq_time=?");
			pStat.setInt(1, stu_eq.getStu_id());		//...
			pStat.setInt(2, stu_eq.getEq_id());			//...
			pStat.setInt(3, stu_eq.getLab_id());
			pStat.setInt(4, stu_eq.getStu_eq_week());
			pStat.setInt(5, stu_eq.getStu_eq_day());
			pStat.setString(6, stu_eq.getStu_eq_time());
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
	
	public boolean findstu_eq(int stu_id, int stu_eq_week, int stu_eq_day, String stu_eq_time){			//查询学生在某时间是否预约过设备
		Connection conn=MysqlConn.getConnectionn();
		try {
		pStat =conn.prepareStatement("select * from stu_eq where stu_id=? and stu_eq_week=? and stu_eq_day=? and stu_eq_time=?");
		pStat.setInt(1, stu_id);		//...
		pStat.setInt(2, stu_eq_week);
		pStat.setInt(3, stu_eq_day);
		pStat.setString(4, stu_eq_time);
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
	
	public List<Stu_Eq> getAllStu_Eqs() {			//返回所有学生预约信息
		List<Stu_Eq> stu_eq_list = new ArrayList<Stu_Eq>();    // 结果集存放在List集合中
		Connection conn=MysqlConn.getConnectionn();
		try {
			pStat = conn.prepareStatement("select * from stu_eq where 1");
			rs = pStat.executeQuery();
			while (rs.next()) {
				int stu_id = rs.getInt("stu_id");
				int eq_id = rs.getInt("eq_id");
				int lab_id= rs.getInt("lab_id");
				int stu_eq_week= rs.getInt("stu_eq_week");
				int stu_eq_day= rs.getInt("stu_eq_day");
				String stu_eq_time= rs.getString("stu_eq_time");
				stu_eq_list.add(new Stu_Eq(stu_id, eq_id, lab_id, stu_eq_week, stu_eq_day, stu_eq_time));   //返回list
			}
			return stu_eq_list;
		} catch (Exception e) {
			return null;
		} finally {
			close();
		}
	}
	
	public List<Stu_Eq> getStu_EqsBystu(int stu_id1) {		//根据学生号查询学生预约信息
		List<Stu_Eq> stu_eq_list = new ArrayList<Stu_Eq>();    // 结果集存放在List集合中
		Connection conn=MysqlConn.getConnectionn();
		try {
			pStat = conn.prepareStatement("select * from stu_eq where stu_id=?");
			pStat.setInt(1, stu_id1);
			rs = pStat.executeQuery();
			while (rs.next()) {
				int stu_id = rs.getInt("stu_id");
				int eq_id = rs.getInt("eq_id");
				int lab_id= rs.getInt("lab_id");
				int stu_eq_week= rs.getInt("stu_eq_week");
				int stu_eq_day= rs.getInt("stu_eq_day");
				String stu_eq_time= rs.getString("stu_eq_time");
				stu_eq_list.add(new Stu_Eq(stu_id, eq_id, lab_id, stu_eq_week, stu_eq_day, stu_eq_time));
			}
			return stu_eq_list;			//返回list
		} catch (Exception e) {
			return null;
		} finally {
			close();
		}
	}

}
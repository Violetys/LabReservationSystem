package dao; 
import java.sql.*;
import java.util.*;

import beans.MysqlConn;
import beans.Stu;

public class StuDAO {   	//学生DAO
	
//public static final String DRIVER="org.gjt.mm.mysql.Driver";
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
	
	
	public boolean isStuidExists(int id) {			//查询学生是否存在
		Connection conn=MysqlConn.getConnectionn();
		try {
		pStat =conn.prepareStatement("select * from students where stu_id=?");
		pStat.setInt(1, id);			//设置？的值
		rs=pStat.executeQuery();
		if( rs.next() ) return true;
		else return false;
		}catch (Exception e) { return false; }
		finally{ close(); }
	} //end isUsernameExists
	
	public boolean findStu(int stu_id, String stu_password){		//验证学号密码
		Connection conn=MysqlConn.getConnectionn();
		try {
		pStat =conn.prepareStatement("select * from students where stu_id=? and stu_password=?");
		pStat.setInt(1, stu_id);		//...
		pStat.setString(2, stu_password);		//...
		rs=pStat.executeQuery();
		
		if( rs.next() ) return true;
		else return false;
		}
		catch (Exception e) { return false; }
		finally{
		close();
		}
	} //end findUser
	
	public boolean addStu(Stu stu) {		//添加学生
		Connection conn=MysqlConn.getConnectionn();
		try {
		pStat=conn.prepareStatement("insert into students values(?,?,?,?,?,?)");
		pStat.setInt(1, stu.getStu_id());			//设置？的值
		pStat.setString(2, stu.getStu_name());			//...
		pStat.setString(3, stu.getStu_password());
		pStat.setString(4, stu.getStu_major());
		pStat.setInt(5, stu.getStu_grade());
		pStat.setInt(6, stu.getStu_class());
		int cnt=pStat.executeUpdate();
		if(cnt>0) return true;
		else return false;
		}
		catch (Exception e) { return false; }
		finally{
		close();
		}
		} //end add
	
	public List<Stu> getAllStus() {			//查询所有学生信息，注释与之前类似...
		List<Stu> tmp_list = new ArrayList<Stu>();    // 结果集存放在List集合中
		Connection conn=MysqlConn.getConnectionn();
		try {
			pStat = conn.prepareStatement("select * from students where 1");
			rs = pStat.executeQuery();
			while (rs.next()) {
				int stu_id = rs.getInt("stu_id");
				String stu_name = rs.getString("stu_name");
				String stu_password = rs.getString("stu_password");
				String stu_major= rs.getString("stu_major");
				int stu_grade = rs.getInt("stu_grade");
				int stu_class = rs.getInt("stu_class");
				tmp_list.add(new Stu(stu_id, stu_name, stu_password, stu_major, stu_grade, stu_class));
			}
			return tmp_list;
		} catch (Exception e) {
			return null;
		} finally {
			close();
		}
	}
	
	
	public boolean delStuById(int id) {		//根据学号删除学生信息
		Connection conn=MysqlConn.getConnectionn();
		try {
			pStat = conn.prepareStatement("delete from students where stu_id=?");
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
	
	public boolean updateStu(Stu stu) {				//更新学生信息，形参为学生对象
		Connection conn=MysqlConn.getConnectionn();
		try {
			pStat = conn.prepareStatement("UPDATE students SET stu_name=?,stu_password=?,stu_major=?,stu_grade=?,stu_class=? where stu_id=?");
			pStat.setInt(6, stu.getStu_id());
			pStat.setString(1, stu.getStu_name());
			pStat.setString(2, stu.getStu_password());
			pStat.setString(3, stu.getStu_major());
			pStat.setInt(4, stu.getStu_grade());
			pStat.setInt(5, stu.getStu_class());
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
	
	
public Stu getStuById(int id) {			//根据学号查询学生信息，返回值为学生对象
	Connection conn=MysqlConn.getConnectionn();
		try {
			pStat = conn.prepareStatement("select * from students where stu_id=?");
			pStat.setInt(1, id);
			rs= pStat.executeQuery();	
			if(rs.next()) {
				Stu stu=new Stu(
						rs.getInt("stu_id"),
						rs.getString("stu_name"),
						rs.getString("stu_password"),
						rs.getString("stu_major"),
						rs.getInt("stu_grade"),
						rs.getInt("stu_class")
						);
				
				return stu;			//返回
			}
			else 
				return null;
		} catch (Exception e) {
			return null;
		} finally {
			close();
		}
	}

//public Stu getStuById(int id) {			//根据学号查询学生信息，返回值为学生对象
//	
//	try {
//		pStat = conn.prepareStatement("select * from students where stu_id=?");
//		pStat.setInt(1, id);
//		rs= pStat.executeQuery();	
//		if(rs.next()) {
//			Stu stu=new Stu(
//					100000001,
//					"李四",
//					"123",
//					"文法",
//					2,
//					1
//					);
//			
//			return stu;			//返回
//		}
//		else 
//			return null;
//	} catch (Exception e) {
//		return null;
//	} finally {
//		close();
//	}
//}
}
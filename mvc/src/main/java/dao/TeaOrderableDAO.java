package dao; 
import java.sql.*;
import java.util.*;

import beans.Equipment;
import beans.Lab;
import beans.MysqlConn;
import beans.Stu_Eq;


public class TeaOrderableDAO {
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
	
	public List<Lab> getOrderableLabs(int selectweek, int selectday, String selecttime) {		//与OrderableDAO注释类似
		List<Lab> lab_list = new ArrayList<Lab>();    // 结果集存放在List集合中
		Connection conn=MysqlConn.getConnectionn();
		try {
			pStat = conn.prepareStatement("select * from labs,tea_lab where tea_lab.tea_lab_week=? and tea_lab.tea_lab_day=? and tea_lab.tea_lab_time=?");
			pStat.setInt(1, selectweek);
			pStat.setInt(2, selectday);
			pStat.setString(3, selecttime);
			rs = pStat.executeQuery();
			if(rs.next()) {
				pStat = conn.prepareStatement("select lab.lab_id,lab.lab_state from labs,tea_lab where tea_lab.tea_lab_week=? and tea_lab.tea_lab_day=? and tea_lab.tea_lab_time=? and tea_lab.tea_lab_id!=labs.lab_id");
				pStat.setInt(1, selectweek);
				pStat.setInt(2, selectday);
				pStat.setString(3, selecttime);
				rs = pStat.executeQuery();
				if(!rs.wasNull()) {
					while (rs.next()) {	
						int lab_id = rs.getInt("lab_id");
						int lab_state= rs.getInt("lab_state");
						lab_list.add(new Lab(lab_id, lab_state));
					}
				}
			}else {
				pStat = conn.prepareStatement("select * from labs where 1");
				rs = pStat.executeQuery();
				if(!rs.wasNull()) {
					while (rs.next()) {	
						int lab_id = rs.getInt("lab_id");
						
						int lab_state= rs.getInt("lab_state");
						lab_list.add(new Lab(lab_id, lab_state));
					}
				}
			}
			return lab_list;
		} catch (Exception e) {
			return null;
		} finally {
			close();
		}
	}


}
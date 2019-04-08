package dao; 
import java.sql.*;
import java.util.*;
import beans.User;
public class UserDAO {
public static final String DRIVER="org.gjt.mm.mysql.Driver";
public static final String DBURL="jdbc:mysql://localhost:3306/testdb";
public static final String DBUSER="root";
public static final String DBPASS="xys9811xc98923";
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
public boolean isUsernameExists(String username) {
conn=getConnectionn();
try {
pStat =conn.prepareStatement("select * from users where username=?");
pStat.setString(1, username);
rs=pStat.executeQuery();
if( rs.next() ) return true;
else return false;
}catch (Exception e) { return false; }
finally{ close(); }
} //end isUsernameExists

public boolean findUser(String username, String password){
	conn=getConnectionn();
	try {
	pStat =conn.prepareStatement("select * from users where username=? and password=?");
	pStat.setString(1, username);
	pStat.setString(2, password);
	rs=pStat.executeQuery();
	if( rs.next() ) return true;
	else return false;
	}
	catch (Exception e) { return false; }
	finally{
	close();
	}
} //end findUser

public boolean addUser(User user) {
	conn=getConnectionn();
	try {
	pStat=conn.prepareStatement("insert into users values(null,?,?)");
	pStat.setString(1, user.getUsername());
	pStat.setString(2, user.getPassword());
	int cnt=pStat.executeUpdate();
	if(cnt>0) return true;
	else return false;
	}
	catch (Exception e) { return false; }
	finally{
	close();
	}
	} //end add

public List<User> getAllUsers() {
	List<User> tmp_list = new ArrayList<User>();    // 结果集存放在List集合中
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("select * from users where 1");
		rs = pStat.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			tmp_list.add(new User(id, username, password));
		}
		return tmp_list;
	} catch (Exception e) {
		return null;
	} finally {
		close();
	}
}


public boolean delUserById(int id) {
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("delete from users where id=?");
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


public User getUserById(int id) {
	conn = getConnectionn();
	try {
		pStat = conn.prepareStatement("select * from users where id=?");
		pStat.setInt(1, id);
		rs= pStat.executeQuery();	
		if(rs.next()) {
			User user=new User(
					rs.getInt("id"),
					rs.getString("username"),
					rs.getString("password")
					);				
			return user;
		}
		else 
			return null;
	} catch (Exception e) {
		return null;
	} finally {
		close();
	}
}


public boolean UpdateUser(User user) {
	conn = getConnectionn();
	try {
	pStat = conn.prepareStatement("Update users set username=?,password=? where id=?");
		pStat.setString(1, user.getUsername());
		pStat.setString(2, user.getPassword());
		pStat.setInt(3, user.getId());			
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



} //end class


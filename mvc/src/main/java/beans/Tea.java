package beans;

public class Tea {		//教师类

	int tea_id;		//教师号
	String tea_name;	//姓名
	String tea_password;	//密码
	
	
	public Tea() {
		
		this.tea_id =0;
		this.tea_name = "";
		this.tea_password = "";
	}
	
	public Tea(int tea_id, String tea_name, String tea_password) {
		
		this.tea_id = tea_id;
		this.tea_name = tea_name;
		this.tea_password = tea_password;
	}
	
	public int getTea_id() {
		return tea_id;
	}
	public void setTea_id(int tea_id) {
		this.tea_id = tea_id;
	}
	public String getTea_name() {
		return tea_name;
	}
	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
	}
	public String getTea_password() {
		return tea_password;
	}
	public void setTea_password(String tea_password) {
		this.tea_password = tea_password;
	}
	
	
	
	
	
}

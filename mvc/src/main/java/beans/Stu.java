package beans;

public class Stu {

	int stu_id;
	String stu_name;
	String stu_password;
	String stu_major;
	int stu_grade;
	int stu_class;
	
	public Stu() {
		this.stu_id = 0;
		this.stu_name = "";
		this.stu_password = "";
		this.stu_major="";
		this.stu_grade=0;
		this.stu_class=0;
	}
	
	public Stu(int id, String name, String password, String major, int grade, int class1) {
		this.stu_id = id;
		this.stu_name = name;
		this.stu_password = password;
		this.stu_major=major;
		this.stu_grade=grade;
		this.stu_class=class1;
	}

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public String getStu_password() {
		return stu_password;
	}

	public void setStu_password(String stu_password) {
		this.stu_password = stu_password;
	}

	public String getStu_major() {
		return stu_major;
	}

	public void setStu_major(String stu_major) {
		this.stu_major = stu_major;
	}

	public int getStu_grade() {
		return stu_grade;
	}

	public void setStu_grade(int stu_grade) {
		this.stu_grade = stu_grade;
	}

	public int getStu_class() {
		return stu_class;
	}

	public void setStu_class(int stu_class) {
		this.stu_class = stu_class;
	}
	
	
}

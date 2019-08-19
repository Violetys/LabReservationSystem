package beans;

public class Stu_Eq {		//学生预约设备类

	int stu_id;			//学生号
	int eq_id;			//设备号
	int lab_id;			//实验室号
	int stu_eq_week;	//预约周次
	int stu_eq_day;		//预约周次的星期几
	String stu_eq_time;		//预约那一天的时段
	public Stu_Eq() {		//构造函数
		this.stu_id = 0;
		this.eq_id = 0;
		this.lab_id=0;
		this.stu_eq_week=0;
		this.stu_eq_day=0;
		this.stu_eq_time = "";
	}
	
	public Stu_Eq(int stu_id, int eq_id, int lab_id, int stu_eq_week, int stu_eq_day, String stu_eq_time) {
		this.stu_id = stu_id;
		this.eq_id = eq_id;
		this.lab_id = lab_id;
		this.stu_eq_week = stu_eq_week;
		this.stu_eq_day = stu_eq_day;
		this.stu_eq_time = stu_eq_time;
	}

	public int getStu_id() {		//get学号
		return stu_id;
	}
	public void setStu_id(int stu_id) {		//设置学号
		this.stu_id = stu_id;
	}
	public int getEq_id() {			//get设备号
		return eq_id;
	}
	public void setEq_id(int eq_id) {		//...
		this.eq_id = eq_id;
	}
	public String getStu_eq_time() {
		return stu_eq_time;
	}
	public void setStu_eq_time(String stu_eq_time) {
		this.stu_eq_time = stu_eq_time;
	}
	public int getLab_id() {
		return lab_id;
	}
	public void setLab_id(int lab_id) {
		this.lab_id = lab_id;
	}

	public int getStu_eq_week() {
		return stu_eq_week;
	}

	public void setStu_eq_week(int stu_eq_week) {
		this.stu_eq_week = stu_eq_week;
	}

	public int getStu_eq_day() {
		return stu_eq_day;
	}

	public void setStu_eq_day(int stu_eq_day) {
		this.stu_eq_day = stu_eq_day;
	}
	
	
	
}

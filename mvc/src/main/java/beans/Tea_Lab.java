package beans;

public class Tea_Lab {			//教师预约实验室类

	int tea_id;		//教师号
	int lab_id;		//实验室号
	int tea_lab_week;		//预约周次
	int tea_lab_day;		//一周的第一几天
	String tea_lab_time;	//一天的哪个时段
	public Tea_Lab() {
		this.tea_id = 0;
		this.lab_id = 0;
		this.tea_lab_week=0;
		this.tea_lab_day=0;
		this.tea_lab_time = "";
		
	}
	
	public Tea_Lab(int tea_id, int lab_id, int tea_lab_week, int tea_lab_day, String tea_lab_time) {
		
		this.tea_id = tea_id;
		this.lab_id = lab_id;
		this.tea_lab_week = tea_lab_week;
		this.tea_lab_day = tea_lab_day;
		this.tea_lab_time = tea_lab_time;
	}

	public int getTea_id() {
		return tea_id;
	}

	public void setTea_id(int tea_id) {
		this.tea_id = tea_id;
	}

	public int getLab_id() {
		return lab_id;
	}

	public void setLab_id(int lab_id) {
		this.lab_id = lab_id;
	}

	public int getTea_lab_week() {
		return tea_lab_week;
	}

	public void setTea_lab_week(int tea_lab_week) {
		this.tea_lab_week = tea_lab_week;
	}

	public int getTea_lab_day() {
		return tea_lab_day;
	}

	public void setTea_lab_day(int tea_lab_day) {
		this.tea_lab_day = tea_lab_day;
	}

	public String getTea_lab_time() {
		return tea_lab_time;
	}

	public void setTea_lab_time(String tea_lab_time) {
		this.tea_lab_time = tea_lab_time;
	}

	
	
	
	
	
}

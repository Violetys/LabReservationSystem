package beans;

public class Equipment {    //设备类

	int eq_id;				//设备号
	int eq_state;			//设备状态
	int eq_lab;				//设备所在实验室号
	public Equipment() {		//构造函数
		
		this.eq_id = 0;
		this.eq_state = 0;
		this.eq_lab = 0;
	}
	public Equipment(int eq_id, int eq_state, int eq_lab) {	 		//构造函数
		this.eq_id = eq_id;
		this.eq_state = eq_state;
		this.eq_lab = eq_lab;
	}
	public int getEq_id() {			//get设备号
		return eq_id;
	}
	public void setEq_id(int eq_id) {		//设置设备号
		this.eq_id = eq_id;
	}
	public int getEq_state() {			//get设备状态
		return eq_state;
	}
	public void setEq_state(int eq_state) {		//...
		this.eq_state = eq_state;
	}
	public int getEq_lab() {		//...
		return eq_lab;
	}
	public void setEq_lab(int eq_lab) {		//...
		this.eq_lab = eq_lab;
	}
	
	
}

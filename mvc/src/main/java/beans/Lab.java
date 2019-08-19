package beans;

public class Lab {		//实验室类

	int lab_id;		//实验室号
	int lab_state;		//实验室状态	
	public Lab() { 			//构造函数	
		this.lab_id = 0;
		this.lab_state = 0;
	}
	
	public Lab(int lab_id, int lab_state) { 		//构造函数
		this.lab_id = lab_id;
		this.lab_state = lab_state;
	}

	public int getLab_id() {		//...
		return lab_id;
	}

	public void setLab_id(int lab_id) {
		this.lab_id = lab_id;
	}

	public int getLab_state() {
		return lab_state;
	}

	public void setLab_state(int lab_state) {
		this.lab_state = lab_state;
	}
	
	
}

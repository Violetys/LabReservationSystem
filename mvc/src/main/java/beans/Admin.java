package beans;

import java.io.Serializable;

public class Admin implements Serializable{
       String  ad_id;
       String ad_name;
       String ad_password;
       public Admin() {
  	   
       }
	public Admin(String ad_id, String ad_name, String ad_password) {
		super();
		this.ad_id = ad_id;
		this.ad_name = ad_name;
		this.ad_password = ad_password;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	public String getAd_password() {
		return ad_password;
	}
	public void setAd_password(String ad_password) {
		this.ad_password = ad_password;
	}
	
}

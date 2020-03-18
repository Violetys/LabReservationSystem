package facelogin;

import com.baidu.aip.face.AipFace;

public class AiFaceObject {
		 //设置APPID/AK/SK
	    public static final String APP_ID = "18499672";
	    public static final String API_KEY = "EYHGtoDVTMF5BqgheMSklwpt";
	    public static final String SECRET_KEY = "sez9VCWmrGF3q0NRqlXhQLgD6aBXBM3B";
	    
	    //创建一个aipface对象
	    private static AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
	    //创建单例的原因是避免多次获取sdk
	    public static AipFace getClient(){
	    	client.setConnectionTimeoutInMillis(2000);
	    	client.setSocketTimeoutInMillis(60000);
	    	return client;
	    }
	    
}

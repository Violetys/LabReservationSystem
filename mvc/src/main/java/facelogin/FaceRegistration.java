package facelogin;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;


import beans.Image;
public class FaceRegistration {
	
	public static String Faceregistrtion(AipFace client,String groupId,String userId,Image image){
		 // 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("user_info", "user's info");
	    options.put("quality_control", "NORMAL");
	    options.put("liveness_control", "LOW");
	    // 人脸注册
	    JSONObject res = client.addUser(image.getImage(), image.getImageType(), groupId, userId, options);
	    
		return res.toString(2);
	}
	
}

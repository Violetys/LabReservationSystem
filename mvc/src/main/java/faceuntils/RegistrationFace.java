package faceuntils;

import org.json.simple.JSONObject;

import com.baidu.aip.face.AipFace;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import facelogin.*;
import beans.*;

public class RegistrationFace {//注册人脸
	 @SuppressWarnings("unchecked")
	public static JSONObject Registration(Image image,AccountIfo account){
		
		AipFace client= AiFaceObject.getClient();
		JSONObject imgjson=new JSONObject();
		JsonParser parse=new JsonParser();//json解析器
		String res=FaceRegistration.Faceregistrtion(client, "DDServerUserIfo", account.getDd_account(), image);//接口返回的字符串
		/*
		 * 提取的数据
		 */
		String face_token=null;
		String error_msg=null;
		JsonObject json=null;
		JsonObject result=null;
		imgjson.put("error_msg", "error");
		try {
			json=(JsonObject)parse.parse(res);
			error_msg=json.get("error_msg").getAsString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if("SUCCESS".equals(error_msg)){
			result=json.get("result").getAsJsonObject();
			face_token=result.get("face_token").getAsString();
			imgjson.put("error_msg", "SUCCESS");
			imgjson.put("face_token", face_token);
			return imgjson;
		}
		else{
			return imgjson;
		}
	}
	
}

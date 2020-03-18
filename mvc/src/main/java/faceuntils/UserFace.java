package faceuntils;

import org.json.simple.JSONObject;

import com.baidu.aip.face.AipFace;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import facelogin.*;
import beans.Image;
@SuppressWarnings("unchecked")
public class UserFace {
	public static JSONObject User(Image imageU){
		AipFace client= AiFaceObject.getClient();
		JSONObject imgjson=new JSONObject();
		JsonParser parse=new JsonParser();//json解析器
		String res=FaceUser.Faceuser(client, imageU);//接口返回的字符串
		/*
		 * 提取的字段
		 */
		JsonObject json=null;
		JsonObject result=null;
		String score=null;
		String user_id=null;
		String error_msg=null;
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
			JsonArray user_list=result.get("user_list").getAsJsonArray();
			JsonObject user=user_list.get(0).getAsJsonObject();
			score= user.get("score").getAsString();
			user_id=user.get("user_id").getAsString();
			imgjson.put("error_msg", "SUCCESS");
			imgjson.put("score", score);
			imgjson.put("user_id", user_id);
			return imgjson;
		}
		else{
			return imgjson;
		}
	}
}

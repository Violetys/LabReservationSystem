package faceuntils;

import org.json.simple.JSONObject;

import com.baidu.aip.face.AipFace;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import facelogin.AiFaceObject;
import facelogin.FaceComparison;

import beans.Image;

public class ComparisonFace {
	 @SuppressWarnings("unchecked")
	public static JSONObject Comparison(Image imageU,Image imageC){
		AipFace client= AiFaceObject.getClient();
		
		JsonParser parse=new JsonParser();//json解析器
		String res=FaceComparison.Facecomparison(client, imageU, imageC);//接口返回的字符串
		JSONObject imgjson=new JSONObject();
//		System.out.println(res);
		/*
		 * 需要获取的值
		 */
		JsonObject json=null;
		JsonObject result=null;
		String score=null;
		String error_msg=null;
		imgjson.put("error_msg", "error");
		try {
			json=(JsonObject)parse.parse(res);
			//error_msg
			error_msg=json.get("error_msg").getAsString();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if("SUCCESS".equals(error_msg)){
			result=json.get("result").getAsJsonObject();
			score=result.get("score").getAsString();
			imgjson.put("error_msg", "SUCCESS");
			imgjson.put("score", score);
			return imgjson;
		}
		else{
			return imgjson;
		}
	}
	
}

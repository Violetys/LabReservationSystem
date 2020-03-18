package faceuntils;

import org.json.simple.JSONObject;

import com.baidu.aip.face.AipFace;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import facelogin.AiFaceObject;
import facelogin.FaceDetection;
import beans.Image;

public class DetectionFace {
 @SuppressWarnings("unchecked")
public static JSONObject Detection(Image image){//检测图片是否是有人脸且合格的
		
		AipFace client= AiFaceObject.getClient();
		JSONObject imgjson=new JSONObject();
		JsonParser parse=new JsonParser();//json解析器
		String res=FaceDetection.Facedetection(client, image);//接口返回的字符串
		System.out.println(res);     //控制台输出，用于测试
		/*
		 * 需要获取的值
		 */
		double face_probability=0.0;   //可能度
		String error_msg=null;
		int face_num=0;
		JsonObject json=null;
		JsonObject result=null;
		//
		imgjson.put("error_msg", "error");
		try {
			json=(JsonObject)parse.parse(res);
			//error_msg
			error_msg=json.get("error_msg").getAsString();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if("SUCCESS".equals(error_msg)){	 	//error_msg是成功的
			result=json.get("result").getAsJsonObject();
			JsonArray face_list=result.get("face_list").getAsJsonArray();
			//face_probability
			JsonObject subobj=face_list.get(0).getAsJsonObject();
			face_probability=subobj.get("face_probability").getAsDouble();
			//face_num
			face_num=result.get("face_num").getAsInt();
			if(face_num==1&&face_probability>0.8){		//有脸 且脸是人脸的概率大于0.8
				imgjson.put("error_msg", "SUCCESS");
				imgjson.put("face_number", "1");
				imgjson.put("imgpath",image.getImage());		//合格返回Base64
				return imgjson;
			}
			imgjson.put("face_number", "noface");
			return imgjson;
		}
		else{
			return imgjson;
		}
		
	}
}

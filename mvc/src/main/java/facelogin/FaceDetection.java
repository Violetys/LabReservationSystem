package facelogin;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

import beans.Image;


public class FaceDetection {//人脸检测的类

	public static String Facedetection(AipFace client,Image image){
		HashMap<String, String> options= new HashMap<String, String>();
		options.put("face_field", "age");//返回的人脸信息
		options.put("max_face_num", "1");//最大人脸识别数1
		options.put("face_type", "LIVE");//照骗类型 生活照
		JSONObject res=client.detect(image.getImage(), image.getImageType(), options);
		return res.toString(2);
	}

	
}

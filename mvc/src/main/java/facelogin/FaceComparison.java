package facelogin;

import java.util.ArrayList;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;

import beans.Image;



public class FaceComparison {//人脸比较
	
	public static String Facecomparison(AipFace client,Image imageU,Image imageC){
		
		MatchRequest req1 = new MatchRequest(imageU.getImage(), imageU.getImageType());
	    MatchRequest req2 = new MatchRequest(imageC.getImage(), imageC.getImageType());
	    ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
	    requests.add(req1);
	    requests.add(req2);
	    JSONObject res = client.match(requests);
		return res.toString(2);
		
	}

}

package facelogin;

import com.baidu.aip.face.AipFace;

import beans.Image;

public class test {
	public static void main(String[] args) {//ע������
		AipFace client=AiFaceObject.getClient();
		Image image=new Image();
		String userId="�Լ���";
		image.setImage(img);
		image.setImageType("BASE64");
		String str=FaceRegistration.Faceregistrtion(client, "�Լ���", userId, image);
		System.out.println(str);
	}
}
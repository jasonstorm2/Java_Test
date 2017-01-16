package my;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * ���� jsonarray ת���� jsonobject
 * 
 * JSON.parseArray��str���������� String ת���� JSONArray����������򱨴��Դ�д�ж�String�Ƿ���ת����JSONArray�ķ���
 * JSON.parseObject��str�� ��string ת���� jsonobject
 * 
 * jasonArray.getString(0) ȡ�������±��ֵ������ת���� jsonobject
 * 
 * ֱ�Ӵ�ӡJSONArray��JSONObject �� toJSONString()���� ��ӡ������String�ַ�����һ��
 * 
 * JSON.toJSONString(obj) ���԰����� objectת��Ϊ string��ʽ
 * jb �� toJSONString �� ja ��toJSONString �ǵ��ø��� JSON ��toJSONString ��������ͬһ������
 * 
 * @author Administrator
 *
 */
public class JSONTest {
	public static void main(String[] args) {
		String s = "[ { \"what\"  :  500,   \"sn\":   1,\"tit\":\"attact\"},   {\"country\":\"america\",\"sn\":2,\"male\":\"girl\"}]";
		String s2 = "[[1000,1000,0,0,\"common\"]]";
		String s3 = "jb";
		if(isJSONAraay(s)){
			JSONArray ja = JSON.parseArray(s);
			JSONObject jb = JSON.parseObject(ja.getString(0));

			System.out.println("ֱ�Ӵ�ӡJSONArray���ݣ�        "+ja);
			System.out.println("toJSONString��ӡJSONArray����: "+ja.toJSONString());
			//ת��Ϊjsonobj
			System.out.println("ת����jsonobject:��toJSONString:  "+jb.toJSONString());
			System.out.println("ת����jsonobject,ֱ�Ӵ�ӡ:        "+jb);
			System.out.println("��ӡjsonobject������key == sn��"+jb.getIntValue("sn"));
			
		}else{
			System.out.println("string����jsonarray");
		}		
		if(isJSONAraay(s2)){
			JSONArray ja = JSON.parseArray(s2);
			JSONArray ja2 = JSON.parseArray(ja.getString(0));

			System.out.println("s2�����ǣ�    "+ja);
			System.out.println("ja2(0):"+ja2.getString(0));
		}
		
		if(isJSONObject(s3)){
			System.out.println("s3�� jsonobject");
		}else{
			System.out.println("s3����jsonobject");
		}
	}
	
	/**
	 * �ж�String �ǲ��� JSONArray
	 * @param value
	 * @return
	 */
	public static boolean isJSONAraay(String value) {
		try {
			JSON.parseArray(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isJSONObject(String value) {
		try {
		    JSON.parseObject(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}

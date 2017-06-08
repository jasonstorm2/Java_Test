package my;

import utils.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONArrayTest {
	public static void main(String[] args) {		
		String str = "[ "
				+ "{ \"what\"  :  500,   \"sn\":   1,\"tit\":\"attact\"},  "
				+ "{ \"what\"  :  400,   \"sn\":   11,\"tit\":\"atta\"},"
				+ "{ \"what\"  :  200,   \"sn\":   2,\"tit\":\"attact\"}"				
				+ "]";
		
		String str2 = "[ "
				+ "[1,22,33],  "
				+ "[1,23,33],"
				+ "[1,22,33]"				
				+ "]";
		
//		JSONArrayDelete(str);		
		deleteOverdue(str2, 22);
		
		printJsonArray();
	}
	
	
	/**
	 * JSONArray����ѭ��ɾ������
	 * forѭ���ı���ɾ���������������������
	 */
	public  static void JSONArrayDelete(String str){
		System.out.println("����ɾ��ǰ��"+str);
		JSONArray ja = JSON.parseArray(str);
		for (int i = 0; i < ja.size(); i++) {
			JSONObject jo = ja.getJSONObject(i);
			if (jo.containsValue("attact")) {
				ja.remove(i);
			}
		}
		System.out.println("����ɾ����"+ja.toJSONString());
		
		
	}
	
	/**
	 * JSONArray����ѭ��ɾ������
	 * forѭ���ı���ɾ���������������������
	 * @param json
	 * @param value
	 * @return
	 */
	public static String deleteOverdue(String json,int value){
		System.out.println("����ɾ��ǰ��"+json);
		
		JSONArray ja = utils.toJSONArray(json);
		if(null == ja || ja.isEmpty()){
			return null;
		}
		for (int i = 0; i < ja.size(); i++) {
			JSONArray j = ja.getJSONArray(i);
			if (j.getIntValue(1) == 22) {
				ja.remove(i);
			}
		}
		System.out.println("����ɾ����"+ja.toJSONString());	
		return ja.toJSONString();
	}
	
	
	/**
	 * ���Դ�ӡ jsonarray 
	 */
	public static void printJsonArray(){
		JSONArray jaAll = new JSONArray();
		JSONArray ja = new JSONArray();
		ja.add(1);
		ja.add(2);
		ja.add(3);
		ja.add(4);
		ja.add(5);
		
		JSONArray ja1 = new JSONArray();
		ja1.add(1);
		ja1.add(2);
		ja1.add(3);
		ja1.add(4);
		ja1.add(5);
		System.out.println(ja.toJSONString());		
		jaAll.add(ja);
		jaAll.add(ja1);		
		System.out.println(jaAll.toJSONString());		
		
		
	}

}

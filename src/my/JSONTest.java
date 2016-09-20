package my;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class JSONTest {
	public static void main(String[] args) {
		String s = "[ { \"what\"  :  500,   \"  sn\":   1,\"tit\":\"attact\"},   {\"country\":\"america\",\"sn\":2,\"male\":\"girl\"}]";
		if(isJSONAraay(s)){
			JSONArray ja = JSON.parseArray(s);
			System.out.println("�����ǣ�"+ja);
		}else{
			System.out.println("string����jsonarray");
		}
	}
	
	/**
	 * �ж�String �ǲ��� JSONArray
	 * @param value
	 * @return
	 */
	public static boolean isJSONAraay(String value) {
		try {
			JSONArray ja = JSON.parseArray(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}

package my;

import java.util.HashMap;
import java.util.Map;

import utils.utils;

/**
 * 1. map ֵ���������
 * 
 * 2. map �Ƿ��ܷſ�ֵ���Ƿ��ֵ�ܴ��渳ֵ
 * @author LiZhenhua
 *
 */
public class MapSortTest {
	public static void main(String[] args) {
		Map<String,Integer> map  = new HashMap<String,Integer>();
		map.put("ss", 4);
		map.put("sd", 2);
		map.put("sddds", 1);
		map.put("sasds", 8);
		map.put("qqs", 89);
		utils.printMap(map);
		Map<String,Integer> map2  = new HashMap<String,Integer>();
		map2 = utils.sortMap(map);
		utils.printMap(map2);
		
		putNullToMap(map2);
	}
	
	
	/**
	 * �� map ���� key - null��ֵ�ԣ��Ƿ��ſ�ֵ
	 * �� key - null��ֵ�� �ٴ����ֵ �������
	 * @param map
	 */
	public static void putNullToMap(Map<String,Integer> map){
		map.put("empty", null);
		System.out.println("*******����nullֵ��");
		utils.printMap(map);
		System.out.println("*******����nullֵ��");
		if(map.containsKey("empty")){
			map.put("empty", 000);
		}
		utils.printMap(map);
	}
}

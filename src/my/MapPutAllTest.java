package my;

import java.util.HashMap;
import java.util.Map;

/**
 * putAll���ԣ�putAll���Ժϲ�����map,�������map����ͬ��key,��ôȡ��putAll��map��ֵ(����ĸ���ǰ���)
 * 
 * putAll()����������ı䱻put��map������
 * @author Administrator
 *
 */
public class MapPutAllTest {
	public static void main(String[] args) {
		test obj = new test();
		 //����map���в�ͬ��key
		Map<Object, Integer> map1 = new HashMap<Object, Integer>();
		map1.put(1, 1);
		map1.put(obj.<String> ll(), 12);
		
		System.out.println("map1:"+map1);
		
		Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		map2.put(22, 22);
		map2.put(23, 23);
		
		map1.putAll(map2);
		System.out.println("map1:"+map1);
		System.out.println("map2:"+map2);
		
		//����map�����ظ���key
		Map<Integer, Integer> map3 = new HashMap<Integer, Integer>();
		map3.put(22, 1);
		System.out.println("map3:"+map3);
		
		Map<Integer, Integer> map4 = new HashMap<Integer, Integer>();
		map4.put(22, 22);
		map4.put(23, 23);
		System.out.println("map4:"+map4);
		
		map3.putAll(map4);
		System.out.println("map3:"+map3);
		System.out.println("map4:"+map4);		
	}
}

class test{
	public Integer ll(){
		System.out.println("go away");

		return 55;
	}
}

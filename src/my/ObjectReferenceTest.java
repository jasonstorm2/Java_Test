package my;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ���� a����ֵ��b�����b����ֵ�ĸı�᲻��Ӱ�쵽a����
 * ���ۣ���Ӱ�졣������ĸ�ֵ��ʵ������Reference�Ĵ���
 * @author Administrator
 *
 */
public class ObjectReferenceTest {
	public Map<String, String> map = null;
	
	public  ObjectReferenceTest(){
		map = new HashMap<String, String>();
		map.put("1", "jack");
		map.put("2", "jessica");		
	}
	
	public static void main(String[] args) {
		ObjectReferenceTest ob = new ObjectReferenceTest();
		
		Map<String, String> map = ob.map;
		map.put("3", "test");
		for (Entry<String, String> en : map.entrySet()) {
			System.out.println("key:"+en.getKey());
			System.out.println("value:"+en.getValue());			
		}
		System.out.println("------------------------------------");
		for (Entry<String, String> en : ob.map.entrySet()) {
			System.out.println("key:"+en.getKey());
			System.out.println("value:"+en.getValue());			
		}
	}

}

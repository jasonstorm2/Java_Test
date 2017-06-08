package my;

import java.util.*;
import java.util.Map.Entry;

/**
 * list �� map �����Է���null��Ԫ�أ����ǣ�map��key ����Ϊ��
 * 
 * Map����ɾ������
 * 
 * @author Administrator
 *
 */
public class ListMapNullPutAndDeleteTest {
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		String ss = null;
		Map<Integer,String> m = new HashMap<Integer, String>();
		l.add(null);
		l.add("huhu");
		System.out.println("list�Ĵ�С��"+l.size());
		m.put(1, null);
		m.put(2, "sdfs");
		System.out.println("map�Ĵ�С��"+m.size());
		m.put(3, ss);
		System.out.println("map�Ĵ�С��"+m.size());
		
		/**
		 * Map����ɾ������
		 */
//		ListMapNullPutAndDeleteTest.delete1(m);
//		ListMapNullPutAndDeleteTest.delete2(m);
		ListMapNullPutAndDeleteTest.delete3(m);
		
	}
	
	private static void delete1(Map<Integer,String> m){
		for(Entry<Integer, String> entry :m.entrySet()){
			if(entry.getKey() == 1){
				m.remove(entry.getKey());  //false
			}			
		}
		System.out.println("map��ֵ��"+m.toString());
	}
	
	private static void delete2(Map<Integer, String> m) {
		  Iterator<Map.Entry<Integer, String>> it = m.entrySet().iterator();  
	        while(it.hasNext()){  
	            Map.Entry<Integer, String> entry = it.next();  
	            if(entry.getKey() == 3)  
//	            	m.remove(entry.getKey()); // false
	                it.remove();//ʹ�õ�������remove()����ɾ��Ԫ��  
	        }
	        
	        System.out.println("map��ֵ��"+m.toString());
	}
	
	private static void delete3(Map<Integer, String> m) {
        Iterator<Map.Entry<Integer, String>> it = m.entrySet().iterator();  
        while(it.hasNext()){  
            Map.Entry<Integer, String> entry=it.next();  
            int key=entry.getKey();  
            if(key==1){  
                System.out.println("delete this: "+key+" = "+key);  
                m.put(1, "����");   //ConcurrentModificationException  ok??????Ϊʲô�����Է��룬��������ɾ��
//                m.remove(key);      //ConcurrentModificationException
//                it.remove();        //OK   ʹ��Iterator��remove�����Ƴ���ǰ����  map��remove �����ᱨ��
            }  
        }
	        
	        System.out.println("map��ֵ��"+m.toString());
	}
	
}

package my;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SetListMapTest {
	/*
	 * ����map�ķ���1
	 */
//	private static void visitMapByEntry(Map map) {
//		Iterator ite = map.entrySet().iterator();
//		
//		while(ite.hasNext()){
//			Map.Entry<Object, Object> entry = (Entry<Object,Object>) ite.next();
//			Object key = entry.getKey();//map�е�key
//			Object value = entry.getValue();//����key��Ӧ��value
//		}
//	}
	/*
	 * ����map�ķ���2
	 */
	String ss = "";
	private static void visitMapByKey(Map<String,Object> map) {
		Iterator<String> keys = map.keySet().iterator();
		
		while(keys.hasNext()){
			Object key = keys.next();//key
			Object value = map.get(key);//����key��Ӧ��value
			System.out.println(value);
		}
	}
	public static void main(String[] args) {
		//������˵����set�в�������ͬ��Ԫ��
		Set<Object>  set=new HashSet<>();
		   set.add("abc");
		   set.add("cde");
		   set.add("efg");
		   set.add("fgh");  
		   set.add("��");    
		   set.add("��");
		   
		   set.add("abc"); //�ظ���abc,set���Զ�����ȥ��   
		   System.out.println("size="+ set.size() );
		 List<Object> list = new ArrayList<>();
		    list.add("abc");
		    list.add("aaa");
		    list.add("ȥ");
		    list.add("��");
		    list.add("��");
		    list.add("��");
		 Map<String,Object> map = new HashMap<>();
		    map.put("first", 1);
		    map.put("second", 2);
		    map.put("third", 3);
		    map.put("forth", 4);
		    map.put("fifth", 5);
		 Map<String,Object> linkmap = new LinkedHashMap<>();
		 linkmap.put("first", 11);
		 linkmap.put("second", 22);
		 linkmap.put("third", 33);
		 linkmap.put("forth", 44);
		 linkmap.put("fifth", 55);

		    
		    //list �������
		    for( Iterator<Object> it = list.iterator();  it.hasNext(); )
	        {             
	            System.out.println(it.next());            
	        }
		    System.out.println("set--------------------------------------------"); 
            //set�������
		    for( Iterator<Object> it = set.iterator();  it.hasNext(); )
	        {             
	            System.out.println(it.next());            
	        }
		    System.out.println("map--------------------------------------------"); 
		    //map���
		    visitMapByKey(map);
		    System.out.println("map--------------------------------------------"); 
		    
		  /*
		   * ����map�ķ���3
		   */
		    for(Object obj : map.values()) {
		        //blabla
		    	System.out.println(obj);
		    }
		    System.out.println("linkmap--------------------------------------------"); 
		    for(Object obj:linkmap.values()){
		    	System.out.println(obj);
		    	
		    }

		    
		    
		    
		    
		    
		    set.addAll(list); //��list�е�ֵ����set,��ȥ���ظ���abc
		         System.out.println("size="+ set.size() );
		        for( Iterator<Object> it = set.iterator();  it.hasNext(); )
		        {             
		            System.out.println("value="+it.next().toString());            
		        } 
		    //treeSet	
		    Set<Object> st = new TreeSet<>();
		    
		 }  

}

package WorldSrvTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObjectArrayTest {
	public static void main(String[] args) {
		// Object �����ʼ����ֵ�����ַ���

		// new Object����ʡ��
		Object bb[] = new Object[] { "lala", "nihao" };
		Object ss[] = { "lala2", "nihao2" };
		
		System.out.println(bb.length);
		for (int i = 0; i < bb.length; i++) {
			System.out.println(bb[i]);
			
		}
		
		//list ��add��size��get�ȷ�������Object��û����Щ����
		System.out.println("--------------------------------------------------->");

		List<String> ll = new ArrayList<String>();
		ll.add("list1");
		ll.add("list2");
		ll.add("sdfs");
		
		ll.size();
		ll.get(0);

		Iterator<String> iter=ll.iterator();
		
		while(iter.hasNext()){
			String s = iter.next();
			
			System.out.println(s);
		}

	}

}

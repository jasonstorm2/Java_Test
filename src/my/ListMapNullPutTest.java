package my;

import java.util.*;

/**
 * list �� map �����Է���null��Ԫ�أ����ǣ�map��key ����Ϊ��
 * @author Administrator
 *
 */
public class ListMapNullPutTest {
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
	}
}

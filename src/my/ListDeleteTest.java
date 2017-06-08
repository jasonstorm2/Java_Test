package my;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList�����ConcurrentModificationException �쳣ԭ�򼰽������
 * 
 * Iterator�ǹ�����һ���������߳��У�����ӵ��һ��mutex��������˵Iterator�ڹ�����ʱ���ǲ����������Ķ��󱻸ı�ġ�
 * Iterator��������ʱ�򣬽�����һ���ڴ�������������
 * ���������ָ��ԭ���Ķ��󣬵�ԭ���Ķ��������ı��ʱ����������������û��ͬ���ı䣬
 * ���Ե�����ָ�������ƶ���ʱ�� �����Ҳ���Ҫ�����Ķ������ǲ�������
 * List��Set���Ƕ�̬�ģ��ɱ�������������ݽṹ������Iterator���ǵ��򲻿ɱ䣬
 * ֻ��˳���ȡ������������������ݽṹ ����Iteratorָ���ԭʼ���ݷ����仯ʱ��Iterator�Լ�����ʧ�˷���
 * 
 * List��Set��Map ������ͨ��Iterator���б��������������ͨ��List������
 * ��ʹ���������ϱ���ʱ������ɾ��������Ҫ�����Ƿ�ᴥ��ConcurrentModificationException�쳣��
 * 
 * �ο���ַ��	http://www.itnose.net/detail/6214432.html
 *     			http://swiftlet.net/archives/743
 * 
 * @author Administrator
 *
 */
public class ListDeleteTest {
	public static void main(String[] args) {
		Data1 d1 = new Data1(1, 18);
		Data1 d2 = new Data1(2, 20);
		Data1 d3 = new Data1(3, 41);
		Data1 d4 = new Data1(4, 55);
		List<Data1> list = new ArrayList<Data1>();
		list.add(d1);
		list.add(d2);
		list.add(d3);
		list.add(d4);
		System.out.println(toString(list));	
		//1.
		List<Data1> list2 = new ArrayList<Data1>();
		for(Data1 d:list){
			if(d.id == 2){
				list2.add(d);
			}
		}
		list.removeAll(list2);
		System.out.println(toString(list));	
		
		//2.
		for (Iterator<Data1> it = list.iterator(); it.hasNext();) {
			Data1 d = it.next();
		       if (d.id == 4) {
		           it.remove();  // ��Iterator��remove�����Ų������
		      }
		}
		System.out.println(toString(list));	
		//3.
//		for (Iterator<Data1> it = list.iterator(); it.hasNext();) {
//			Data1 d = it.next();
//		       if (d.id == 3) {
//		    	   list.remove(d); //����ʱ��������list��remove���� �˴������ConcurrentModificationException�쳣
//		      }
//		}
//		System.out.println(toString(list));	
		//4.
		for (Data1 data:list)  {
			if(data.id == 3){
				list.remove(data);  // foreach ����ʱɾ��Ҳ�����
			}
		}
		System.out.println(toString(list));	
		
	}
	public static String toString(List<Data1> list) {
		String str = "list ���е�id:  ";
		for(Data1 d: list){
			str+=d.id+",";
		}		
		
		return str;
		
		
	}
}

class Data1{
	int id;
	int age;
	public Data1(int id ,int age){
		this.id = id;
		this.age = age;
	}
}

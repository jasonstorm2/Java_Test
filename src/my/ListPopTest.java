package my;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * 
 * pop() �Ӷ�������������index0����Ԫ��,��ʱ����
 * poll()�Ӷ�������������index0����Ԫ��,��ʱ����null
 * 
 * pollLast() �ӵײ�����Ԫ�أ�����indexmax ����Ԫ�أ���ʱ����null
 * pollFirst()�Ӷ�������Ԫ�أ�����index0 ����Ԫ�أ���ʱ����null
 * 
 * push() �Ӷ���ѹ��Ԫ�أ�����index0ѹ��Ԫ��
 * 
 * LinkedList ���� ѹ����� pop and push�ӵ���
 * linkList.pollLast()���ӵײ��������ݣ�ֱ��null
 * @author Administrator
 *
 */
public class ListPopTest {
	public static void main(String[] args) {
		System.out.println("--------------ConcurrentLinkedQueue--------------------");
		ConcurrentLinkedQueue<Integer> memberLocation = new ConcurrentLinkedQueue<>();
		memberLocation.add(1);
		memberLocation.add(2);
		memberLocation.add(4);
		memberLocation.add(13);
		System.out.println("memberLocation�����Ԫ�أ�"+memberLocation);
		memberLocation.poll();
		System.out.println("memberLocation�����Ԫ�أ�"+memberLocation);
		System.out.println("memberLocation.peek():"+memberLocation.peek());		
		System.out.println("memberLocation�����Ԫ�أ�"+memberLocation);
		
		System.out.println("--------------ConcurrentLinkedQueue--------------------");
		
		//list has no a METHOD call "pop"
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(2);
		list.add(6);
		list.add(7);
		System.out.println(list);
		//LinkedList has a METHOD name "pop"
		LinkedList<Integer> linkList = new LinkedList<Integer>();
		linkList.add(55);
		linkList.add(22);
		linkList.add(1);
		linkList.add(4);
		linkList.add(6);
		System.out.println("linkList�����Ԫ�أ�"+linkList);
		linkList.pop();
		System.out.println("linkList�����Ԫ�أ�"+linkList);
//		System.out.println("����Ԫ�أ�"+linkList.pop());
		System.out.println("����Ԫ�أ�"+linkList.pop());
		System.out.println("linkList.pollLast():"+linkList.pollLast());
		System.out.println("linkList�����Ԫ�أ�"+linkList);
		System.out.println("linkList.pollLast():"+linkList.pollLast());
		System.out.println("linkList.pollLast():"+linkList.pollLast());
		System.out.println("linkList.pollLast():"+linkList.pollLast());
		System.out.println("linkList�����Ԫ�أ�"+linkList);
		linkList.push(10);
		System.out.println("linkList�����Ԫ�أ�"+linkList);
		linkList.push(111);
		System.out.println("linkList�����Ԫ�أ�"+linkList);

		System.out.println("linkList�����pollԪ�أ�"+linkList.poll());	
		System.out.println("linkList�����popԪ�أ�"+linkList.pop());
		System.out.println("linkList�����pollԪ�أ�"+linkList.poll());
		System.out.println("linkList�����pollFirstԪ�أ�"+linkList.pollFirst());
		linkList.push(11);
		linkList.push(110);
		linkList.push(101);
		System.out.println("linkList�����Ԫ�أ�"+linkList);
		System.out.println("linkList�����pollFirstԪ�أ�"+linkList.pollFirst());
		System.out.println("-----------------------------------");
		ConcurrentLinkedDeque<Integer> location = new ConcurrentLinkedDeque<>();	
		location.add(1);
		location.add(2);
		location.add(3);
		popLocation(location, 4);
		
	}
	
	// ���������ж�
	public static int popLocation(ConcurrentLinkedDeque<Integer> location ,int position){
		int pos = 0;
		if(position != 0 && location.contains(position)){			
			location.remove(position);
			pos = position;			
		}else{
			pos = location.poll();
		}
		
		return pos;		
	}

}

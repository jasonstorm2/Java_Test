package my;

import java.util.ArrayList;
import java.util.List;


/**
 * ���ֲ��뷨����
 * ˼·�� ȡ����һ����±꣬�������ֵ�Ƚϴ�С��ֱ��ȷ�ϸ���ֵ�������е��±ꡣ
 * @author LiZhenhua
 *
 */
public class binaryInsertTest {
	static List<Integer> list = new ArrayList<Integer>();
	static{		
		list.add(800);//0
		list.add(700);//1
		list.add(600);//2
		list.add(500);//3
		list.add(400);//4
		list.add(300);//5
		//		 230
		list.add(200);//6
		list.add(100);//7
	}
	
	public static void main(String[] args) {
		int value  = 50;
		System.out.println(list);
		int index = binarySearchTeamInfo(value, 0, list.size() - 1);
		System.out.println("value:"+value+" index:"+index);
		list.add(index, 50);
		System.out.println(list.toString());
		
	}
	
	
	public static int binarySearchTeamInfo(Integer value, int from, int to) {
		if (to - from > 0) {
			int middle = (from + to) / 2;
			// ����
			if (compareTeamInfo(list.get(middle),value)>-1) {
				return binarySearchTeamInfo(value, middle+1, to);
			} else {
				return binarySearchTeamInfo(value, from, middle-1);
			}
		} else {
			if (compareTeamInfo(list.get(from),value)>-1) {
				return from+1;
			} else {
				return from;
			}
		}
	}
	
	/**
	 * �Ƚ�
	 * @param rank1
	 * @param rank2
	 * @return
	 */
	private static int compareTeamInfo(Integer value1,Integer value2){		
		if(value1>value2){
			return 1;
		}
		else if(value1==value2){
			return 0;
		}
		return -1;
	}
}

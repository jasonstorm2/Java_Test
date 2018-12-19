package my;

import java.util.ArrayList;
import java.util.List;

/**
 * ����list�ǽ���
 * ��230��list�в����λ��
 * @author LiZhenhua
 *
 */
public class binaryInsertTest2 {
	
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
		int value = 50;
		int i = getMiddle(0, 7, value);
		System.out.println("����λ�ã�"+i);
		list.add(9, value);
		System.out.println(list.toString());
	}
	
	/**
	 * �м�ֵ�󷨣�(start+end)/2 = middle
	 * 
	 * ��ô�ж�ȷ����ֵ���ڵķ�Χ�������ٽ��ж��ּ��㣿
	 * ��start��end���޿ɷ�ʱ���� end - start =1,�Ͳ����ڶ��ּ���
	 * 
	 * @param start
	 * @param end
	 * @param value
	 */
	static int getMiddle(int start,int end,int value){
		System.out.println("start:"+start + "  end:"+end);		
		if(end - start>1){  //��������
			int middle = (start + end)/2;		
			if(compare(list.get(middle), value)){
				return getMiddle(middle, end, value);
			}else{
				return getMiddle(start, middle, value);
			}
		}else{
			if(!compare(list.get(start), value)){
				return start;
			}
			if(!compare(list.get(end), value)){
				return end+1;
			}
			
		}
		return value;
	}
	
	/**
	 * �ж�����ֵ�Ĵ�С
	 * @param v1
	 * @param v2
	 * @return
	 */
	static boolean compare(int v1,int v2){
		if(v1 > v2){
			return true;
		}else{
		    return false;
		}		
	}
	
	

}

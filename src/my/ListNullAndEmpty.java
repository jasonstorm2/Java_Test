package my;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList �պ�empty�ıȽ�
 * @author LiZhenhua
 *
 */
public class ListNullAndEmpty {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> list2 = null;
		
		System.out.println(list);
		System.out.println(list2);
		if(list.isEmpty()){
			System.out.println("list��isEmpty��");
		}else{
			System.out.println("list����isEmpty��");
		}
		
//		if(list2.isEmpty()){ // null û�ж����޷�����List�ķ���
//			System.out.println("list2��isEmpty��");
//		}else{
//			System.out.println("list2����isEmpty��");
//		}
		
		if(list2 == null){
			System.out.println("list2��null��");
		}
		
	}

}

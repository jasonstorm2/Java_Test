package my;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * List �������������
 * ʹ��Collections.sort(baseList);����������С��������
 * @author LiZhenhua
 *
 */
public class ListSortTest {
	public int age;
	public String name;
	public int high;
	
	public ListSortTest(){
		
	}
	
	public ListSortTest(int age,int high,String name){
		this.age = age;
		this.high = high;
		this.name = name;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += age;
		return str;	
	};
	
	public static void main(String[] args) {
		utils.utils.PrintLine("��ͨ���򷽷�Collections.sort(baseList)");
		List<Integer> baseList =  new ArrayList<Integer>();
		baseList.add(11);
		baseList.add(2);
		baseList.add(33);
		baseList.add(54);
		baseList.add(5);
		Collections.sort(baseList);
		System.out.println(baseList);
		utils.utils.PrintLine("�Զ������򷽷�sortList()");
		sortList();
	}
	
	public static void sortList(){
		List<ListSortTest> guildList =  new ArrayList<ListSortTest>();
		ListSortTest l1 = new ListSortTest(50,165,"�ٴ�");
		ListSortTest l2 = new ListSortTest(20,170,"����");
		ListSortTest l3 = new ListSortTest(55,166,"����");
		ListSortTest l4 = new ListSortTest(20,180,"����");
		guildList.add(l1);
		guildList.add(l2);
		guildList.add(l3);
		guildList.add(l4);
		
		// ���A1���ұ�B1�󣬷�����ֵ1�����ұ߿�ǰ�� B1,A1
		// ����������ȣ���������������ԽС��Խǰ
		// ���,������������Լ��Խ��ǰ
		// ���� �ɴ�С
		Collections.sort(guildList, new Comparator<ListSortTest>(){
			@Override
			public int compare(ListSortTest guild1, ListSortTest guild2) {				
	              if(guild1.age> guild2.age){
	                    return 1;  
	                } else if(guild1.age == guild2.age){  
	                   if(guild1.high >= guild2.high){
	                	   return -1;  
	                   }else{
	                	   return 1;  
	                   }
	                } else {
	                	 return -1;  
	                } 
	               
			}			
		});			
		ListtoString(guildList);
	}
	
	public static void ListtoString(List<ListSortTest> list){
		List<String> nameList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			nameList.add(list.get(i).name);
		}	
		System.out.println(nameList.toString());
	}

}

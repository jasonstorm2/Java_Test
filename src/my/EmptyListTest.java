package my;

import java.util.Collections;
import java.util.List;

/**
 * ���� �ռ��ϡ�
 * �ռ��ϵ�ֵ���ܸı䣬Ҳ����˵�޷�����µ�ֵ
 * @author LiZhenhua
 *
 */
public class EmptyListTest {
	public static void main(String[] args) {
		List<String> list = Collections.emptyList();
//		list.add("a"); // ���������
		System.out.println(list.size());
		System.out.println(list);
		for (String string : list) {
			System.out.println(string);
		}
		if(list != null){
			System.out.println("���ϲ�Ϊ��");
//			list.get(0); //����
		}
		
	}

}

package JavaCoreLearn;

import java.lang.reflect.Array;

public class ArrayTest2 {
	public static void main(String[] args) {
		
		
		String[][][] cc = new String[4][4][4];
		/*
		 * ��ά���飺��ʵҲ��һ��һά���飬������Ԫ���Ƕ�ά����
		 * ��˿�����Ϊ��arr �ǳ���Ϊ 3 ��һά����
		 */
		Object arr = Array.newInstance(String.class, 3,4,10);
		//arr������ index Ϊ 2 ��Ԫ�أ���Ԫ���Ƕ�ά����
		Object arrObj = Array.get(arr, 2);
		//Ϊ��ά���鸳ֵ����ά�����Ԫ����һά����
		Array.set(arrObj, 2, new String[]{
				"����Ů��",
				"�Ͷ�����","eee"
		});
		//��ȡ��ά�����У��±�Ϊ3 ��һά����Ԫ��
		Object anAr = Array.get(arrObj, 3);
		
		Array.set(anAr, 8, "������");
		
		Object anAr0 = Array.get(arrObj, 0);		
		Array.set(anAr0,9,"����:2,0,9");
		
		String[][][] cast = (String[][][])arr;      //�˴��� ǿ��ת�� �ǲ���ȫ�Ĳ��� 
		System.out.println(cast[2][3][8]);
		System.out.println(cast[2][2][0]);
		System.out.println(cast[2][2][1]);
		System.out.println(cast[2][2][2]);
		System.out.println(cast[2][0][9]);
		
		
		System.out.println(cast[2][3][9]);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j <4; j++) {
				for (int j2 = 0; j2 < 4; j2++) {
//					if (cc[i][j][j2]!=null) {
						System.out.println(cc[i][j][j2]);
//					}
				}
				
			}
		}
	}

}

package my;

import java.util.Arrays;

/**
 * ���ַ���������
 * 
 * �˷�Ϊ�������������ʲ�ѯǰ��Ҫ��sort()���������������������û�����������ǲ�ȷ���ģ�
 * ����,��������к��ж��ָ��ֵ��Ԫ�أ����޷���֤�ҵ�������һ��
 * 
 * @author LiZhenhua
 *
 */
public class BinaryArraySearchTest {
	public static void main(String[] args) {
	     int a[] = new int[] {1, 3, 4, 6, 8, 9};
	        int x1 = Arrays.binarySearch(a, 5);
	        int x2 = Arrays.binarySearch(a, 4);
	        int x3 = Arrays.binarySearch(a, 0);
	        int x4 = Arrays.binarySearch(a, 10);
	        System.out.println("x1:" + x1 + ", x2:" + x2);
	        System.out.println("x3:" + x3 + ", x4:" + x4);
	}
}

package javaTest;

public class oddNumberTest {
	public static void main(String[] args) {
		System.out.println(oddNumberTest.oddOrNot(6));
		System.out.println(oddNumberTest.oddOrNot2(1));
		lll();
	}
	
	/**
	 * ����
	 * @param num
	 * @return
	 */
	public static boolean oddOrNot(int num){
		System.out.println("����"+num % 2);
		return num % 2 == 1;
	}
	
	/**
	 * ������ ��
	 * & 0010
	 * @param num
	 * @return
	 */
	public static boolean oddOrNot2(int num){
		return (num & 2) != 0;
	}
	
	public static void lll(){
//		 int mid = (1 >>> 1) & ~1; // force midpoint to be even
		int mid = 1>>>1;
		System.out.println("1 >>> 1���ԣ��޷������ƣ���λ��0����"+mid);
	}

}

package javaTest;

public class oddNumberTest {
	public static void main(String[] args) {
		System.out.println(oddNumberTest.oddOrNot(6));
		System.out.println(oddNumberTest.oddOrNot2(1));
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

}

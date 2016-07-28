package my;

public class BitOperationTest {
	/***
	 * & ������������λ��Ϊ1�������Ϊ1��������Ϊ0��
	 * | ������������λ��ֻҪ��һ��Ϊ1�����Ϊ1��������Ϊ0��
	 * ~ ���λΪ0�������1�����λΪ1�������0��
	 * << : �����������num << 1, ���ƣ��൱��num����2
	 * 
	 * >> : �����������num >> 1, ���ƣ��൱��num����2
	 * 
	 * >>> : �޷������ƣ����Է���λ����λ����0����
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int number = 10;
		// ԭʼ��������
		printBitInfo(number); // 1010
		printIntInfo(number);
		printLine();
		number = number << 1;
		// ����һλ
		printBitInfo(number); // 10100 2^1*(2^3 +2)
		printIntInfo(number);
		printLine();

		number = number >> 1;
		printBitInfo(number); //1010
		printIntInfo(number);
		printLine();
		
		number = number << 2;
		printBitInfo(number); //101000 2^2*(2^3 +2)
		printIntInfo(number);
		printLine();

	}

	/**
	 * ���һ��int�Ķ�������
	 * 
	 * @param num
	 */
	private static void printBitInfo(int num) {
		System.out.println(Integer.toBinaryString(num));
	}
	
	/**
	 * ���һ��int�Ķ�������
	 * 
	 * @param num
	 */
	private static void printIntInfo(int num) {
		System.out.println(num);
	}
	/**
	 * ���һ���ָ���
	 * 
	 * @param num
	 */
	private static void printLine() {
		System.out.println("----------------------");
	}
}

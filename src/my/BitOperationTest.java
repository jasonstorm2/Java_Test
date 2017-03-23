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
		System.out.println("ԭʼʮ������ֵ��"+number);
		// ԭʼ��������
		intToStrBin(number); // 1010
		printIntInfo(number);
		printLine();
		number = number << 1;
		System.out.println("����һλ<<");
		// ����һλ
		intToStrBin(number); // 10100 2^1*(2^3 +2)
		printIntInfo(number);
		printLine();
		System.out.println("����һλ>>");
		
		number = number >> 1;
		intToStrBin(number); //1010
		printIntInfo(number);
		printLine();
		System.out.println("���ƶ�λ<<");		
		number = number << 2;
		intToStrBin(number); //101000 2^2*(2^3 +2)
		printIntInfo(number);
		printLine();
		
		printLine();
		StrBinCharTest("111");
		printLine();
		strBinaToInt("911");
		
		
		int a = strBinaToInt("001");
		int b = strBinaToInt("010");
		
		int ad = AND(a,b);
		int or = OR(a,b);
		
		intToStrBin(or);
		intToStrBin(or<<1);
		
		checkStrLength("1");
		checkStrLength("11");
		checkStrLength("111");
		checkStrLength("1111");
		
	}
	
	/**
	 * strת����char,charת����String�Ȳ���
	 * @param str
	 * @return
	 */
	private static String StrBinCharTest(String str) {
		char[] strChar = str.toCharArray();
		String result = "";
		for (int i = 0; i < strChar.length; i++) {
			int a =Integer.valueOf(strChar[i]);			
			System.out.println("charת����int��"+a);
			String ss = String.valueOf(strChar[i]);
			System.out.println("charת����String��"+ss);
			int b = Integer.valueOf(ss);
			System.out.println("charת����String,��ת����int��"+b);
			
			
			result += Integer.toBinaryString(b) + " ";
		}
		System.out.println("�ַ���ת���ɶ����ƣ�"+result);
		return result;
	}

	/**
	 * �� ʮ��������ת����String��ʽ�Ķ�����
	 * �磺10  ---> 1010
	 * 
	 * @param num
	 */
	private static void intToStrBin(int num) {
		System.out.println("�� ʮ��������ת����String��ʽ�Ķ�����:"+Integer.toBinaryString(num));
	}
	
	/**
	 * ת���ɶ�����String�󣬲�����λ�ģ���0����
	 * �磺 1==>001 11==>011
	 * @param str
	 * @return
	 */
	private static String checkStrLength(String str) {
		String result = str;
		int len = str.length();
		if(len<3){
			for(int i=0;i<(3-len);i++){
				result="0"+result;
			}
		}
		return result;
	}
	
	/**
	 * �� String��ʽ�Ķ����ƣ�ֱ��ת����ʮ����
	 * @param str
	 * @return
	 */
	private static int strBinaToInt(String str){
		int res = 0;
		try {
			res = Integer.parseInt(str,2);
			System.out.println("�� String��ʽ�Ķ����ƣ�ֱ��ת����ʮ����:"+res);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�Ƕ����Ƹ�ʽ");
		}
		return res;
	}
	
	/**
	 * ʮ���������� ���ص���ʮ���Ƹ�ʽ
	 * @param a
	 * @param b
	 * @return
	 */
	private static int AND (int a,int b){
		System.out.println(a+"&"+b+":"+(a&b));
		return a&b;
	}
	
	/**
	 * ʮ���ƻ����� ���ص���ʮ���Ƹ�ʽ
	 * @param a
	 * @param b
	 * @return
	 */
	private static int OR(int a,int b){
		System.out.println(a+"|"+b+":"+(a|b));
		return a|b;
	}
	
	/**
	 * ���һ��int��ʮ������
	 * 
	 * @param num
	 */
	private static void printIntInfo(int num) {
		System.out.println("ʮ���ƣ�"+num);
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

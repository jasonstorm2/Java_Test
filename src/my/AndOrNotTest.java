package my;

/**
 * 
 * @author LiZhenhua
 * 
 * java �Ķ����Ʊ�ʾ 0b,���� 6�Ķ����Ʊ�ʾ��0b110
 * 
 * ������xת�ɶ������������ַ�����ʾ��������ַ���:Integer.toBinaryString()
 * 
 * ��λ�������ڻ������ͣ����ܱ�ʾ�߼�����
 */
public class AndOrNotTest {
	
	/**
	 * a & b��ֵ�Ƿ����a
	 * ͨ���������͵��ж�
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isSame(int a,int b){
		return (a & b) == a;
	}
	
	
	/**
	 * ������ ��ͬtrue����true 
	 * 
	 * �� 1&1 == 1,1&0=0 0&0=0;
	 * @param channelNum
	 * @param channel
	 * @return
	 */
	public static int methodAnd(int channelNum,int channel){
		return (channelNum & channel);
	}
	
	/**
	 * ������,��������תΪ�����ƣ�Ȼ��Ӹ�λ��ʼ�Ƚϣ�������ֻҪ��һ��Ϊ1��Ϊ1�������Ϊ0��
	 * 
	 * ����1|1 = 1,  1|0 = 1, 0|1 = 1
	 * @param channelNum
	 * @param channel
	 * @return
	 */
	public static int methodOr(int channelNum,int channel){
		return (channelNum | channel);
	}
	
	/**
	 * a|=b :a��b��λ��Ȼ��ֵ��a
	 * @param a
	 * @param b
	 * @return
	 */
	public static int methdoAndEqual(int a,int b){
		int c = a|=b;
		return c;
	}
	
	/**
	 * �� int ���� ת���� �����Ƶ� string��ʽ ���
	 * @param value
	 * @return
	 */
	public static String toBinaryString(int value){
		return Integer.toBinaryString(value);
	}	

	
	public static void main(String[] args) {
		//1000000000000000100000000
		//1000000000000001100000000
		utils.utils.PrintLine("1000000000000000100000000,1000000000000001100000000 ������� �Ƿ� ��1000000000000000100000000,1000000000000001100000000 ���");
		System.out.println("����������"+isSame(0b1000000000000000100000000 ,0b1000000000000001100000000));
		
		utils.utils.PrintLine("110, 010 ������Ԥ��&");
		System.out.println("ʮ���ƣ�"+methodAnd(0b110,0b10));
		System.out.println("�����ƣ�"+toBinaryString(methodAnd(0b110,0b10)));
		
		utils.utils.PrintLine("11, 10 ���Ի�Ԥ��|");	
		System.out.println("ʮ���ƣ�"+methodOr(0b11, 0b10));
		System.out.println("�����ƣ�"+toBinaryString(methodOr(0b11, 0b10)));
		
		utils.utils.PrintLine("|= ���Ų��:0b1111 |= 0b1010");	
		System.out.println("ʮ���ƣ�"+methdoAndEqual(0b1111, 0b1010));
		System.out.println("�����ƣ�"+toBinaryString(methdoAndEqual(0b1111, 0b1010)));
		
		System.out.println("ʮ���ƣ�"+methodAnd(0,4));
		System.out.println("�����ƣ�"+toBinaryString(methodAnd(0b1,0b10)));
		
		System.out.println(1<<0);//1
		System.out.println(1<<1);//2  10
		System.out.println(1<<2);//4  100
		System.out.println(1<<3);
		System.out.println(1<<4);
		System.out.println(1<<5);
		System.out.println(1<<6);
		System.out.println(1<<7);
		System.out.println(1<<8);
		System.out.println(1<<9);		
		System.out.println(1<<10);
		
		
	}

}

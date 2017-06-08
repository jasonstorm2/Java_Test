package my;

public class BinHexOctTest {
	public static void main(String[] args) {
		int a = 100729088;//0x06010100
		int b = 33619968;//0x02010000
//		long c = 4294901760l;//0Xffff0000;
		System.out.println("ȡaǰ16λ:"+Integer.toBinaryString(getSubType(a))+",���ȣ�"+Integer.toBinaryString(getSubType(a)).length());
		System.out.println("ȡbǰ16λ:"+Integer.toBinaryString(getSubType(b))+",���ȣ�"+Integer.toBinaryString(getSubType(b)).length());
		
		isSameType(a, b);
		
		isSameType(b, a);
		
		System.out.println("ȡaǰ24λ:"+Integer.toBinaryString(getSubType3(a))+",���ȣ�"+Integer.toBinaryString(getSubType3(a)).length());
		System.out.println("ȡbǰ24λ:"+Integer.toBinaryString(getSubType3(b))+",���ȣ�"+Integer.toBinaryString(getSubType3(b)).length());
		
		
		
		
	}
	/**
	 * �����Ƿ����ڸô��������
	 * @param type ��ˣ��ǲ��Ǹ�����
	 * @param itemType ��Ҫ�Ƚϼ����ĵ�������
	 * @return
	 */
	public static boolean isSameType(int baseType, int itemType) {
		System.out.println("�Ƿ�֪ͨ������"+Integer.toBinaryString(baseType & itemType));
		return (baseType & itemType) == baseType;
	}

	/**
	 * �������͵�ǰ16λ OX1200
	 * @param type
	 * @return
	 */
	public static int getSubType(int type) {
		return type & 0Xffff0000;
	}
	
	/**
	 * ǰ��24λ
	 * @param type
	 * @return
	 */
	public static int getSubType3(int type) {
//		Integer.toBinaryString();
		return type & 0xff000000;
	}
}

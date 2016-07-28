package my;


public class AssignableTest {
	
	public AssignableTest(String name) {
	}
	/**
	 * �ж�һ�����Ƿ�����һ����ĸ���
	 * �Ǵ�ӡtrue
	 * ���ӡfalse
	 */
	public static void testIsAssignedFrom1() {
		System.out.println("String��Object�ĸ���:"+String.class.isAssignableFrom(Object.class));
	}
	/**
	 * �ж�һ�����Ƿ�����һ����ĸ���
	 * �Ǵ�ӡtrue
	 * ���ӡfalse
	 */
	public static void testIsAssignedFrom2() {
		System.out.println("Object��String�ĸ���:"+Object.class.isAssignableFrom(String.class));
	}
	/**
	 * �ж�һ�����Ƿ����һ������ͬ
	 * �Ǵ�ӡtrue
	 * ���ӡfalse
	 */
	public static void testIsAssignedFrom3() {
		System.out.println("Object��Object��ͬ:"+Object.class.isAssignableFrom(Object.class));
	}

	/**
	 * �ж�str�Ƿ���Object���ʵ��
	 * �Ǵ�ӡtrue
	 * ���ӡfalse
	 */
	public static void testInstanceOf1() {
		String str = new String();
		System.out.print("str��Object��ʵ��:");
		System.out.println(str instanceof Object);
	}
	/**
	 * �ж�o�Ƿ���Object���ʵ��
	 * �Ǵ�ӡtrue
	 * ���ӡfalse
	 */
	public static void testInstanceOf2() {
		Object o = new Object();
		System.out.print("o��Object��ʵ��:");
		System.out.println(o instanceof Object);
	}
	
	public static void main(String[] args) {
		testIsAssignedFrom1();
		testIsAssignedFrom2();
		testIsAssignedFrom3();
		testInstanceOf1();
		testInstanceOf2();
	}
}


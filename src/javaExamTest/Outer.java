package javaExamTest;
/**
 * �����ڲ����ʵ��������
 * @author LiZhenhua
 *
 */
public class Outer {
	public void someOuterMethod() {
		new Inner(); // A
	}

	public class Inner {
	}

	public static void main(String[] argv) {
		Outer o = new Outer();
//		new Inner(); // B ���� ��Ҫһ���ⲿ�������
//		new o.Inner(); // C ���� ������ʵ�����ᱨ��
//		new Outer.Inner(); // D	����
		o.new Inner(); // ��ȷ
	}
}
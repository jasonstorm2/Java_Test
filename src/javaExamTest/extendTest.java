package javaExamTest;
/**
 * �̳к󣬶Ը��෽������д
 * @author Administrator
 *
 */
class A {
	protected int method1(int a, int b) {
		return 0;
	}
}

public class extendTest extends A {
//	public int method1(int a, int b) {   //��ȷ��Ȩ�޿��Դ��ڸ��෽����Ȩ��
//		return 0;
//	} // A
//
//	private int method1(int a, int b) {		//����Ȩ�޿���С�ڸ��෽����Ȩ��
//		return 0;
//	} // B
//
//	private int method1(int a, long b) {	//��ȷ�����������һ�����Ѿ���һ�� �µķ����ˡ�
//		return 0;
//	} // C
//
//	public short method1(int a, int b) {	//���󣺷������� ���ܺ� ����� �������Ͳ�һ��
//		return 0;
//	} // D
//
//	static protected int method1(int a, int b) {		//���󣺸��಻Ϊ��̬������Ҳ����Ϊ��̬
//		return 0;
//	} // E
}
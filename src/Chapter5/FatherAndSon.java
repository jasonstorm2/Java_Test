package Chapter5;

/**
 * ���� ˵���� ����ʵ������ļ���˳��:
 * 		�ȵ��ø�����޲���������
 * 
 * ����Ĺ������е���һ������д�ķ�������ô�� ����ģ���д�ķ������ᱻ���á�
 * 
 * �����Ķ���ͳ�ʼ�� ���ڹ������ĵ��ã���
 * 
 * �������ڶ�����ڣ���
 * @author LiZhenhua
 *
 */
public class FatherAndSon {
	public static void main(String[] args) {
		new B11(10);
	}
}

abstract class C11 {
	public C11() {
		this.print();// ������д������ķ���
		System.out.println("c�Ĺ�����");
	}

	public abstract void print();
}

class B11 extends C11 {
	private int x = 100;

	public B11(int x) {
		this.x = x;
		System.out.println("b�Ĺ�����");
		System.out.println("x��ֵ��"+x);
	}

	public void print() {
		System.out.println("x=" + x);
		System.out.println("��д�ĺ���print");
	}
}

package Chapter5;


/**
 * ��Ҫ�ڸ���Ĺ��췽���е��ã���������д �ķ���
 * @author Administrator
 *
 */
public class Sub extends Base {
	private String name;

	public void test() {//1
		System.out.println("������д����ķ�������name�ַ������ȣ�" + name.length());
		Chapter15.FileTest ft = new Chapter15.FileTest();
	}

	public static void main(String[] args) {
		Sub s = new Sub();//��Ĭ�ϵĵ��ø����Ĭ�Ϲ��췽����������Ĺ������е����ˣ���������д�ķ�������ô�͵����������д�ķ��������Գ�����
	}
}

class Base {
	public Base() {
		test();
	}

	public void test() {//2
		System.out.println("����������д�ķ���");
	}
}
package java8;

/**
 * �̳нӿ�ʱ���ӿ��е�Ĭ�Ϸ��� ������д
 * ������д �ӿ��еĳ��󷽷����ӿ��еķ���Ĭ���ǳ���ģ�
 * @author Administrator
 *
 */
public class impleLikeExtends implements interfacePeople, interfacePerson {

	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("�̳�");

	}

	public static void main(String[] arsf) {
		impleLikeExtends im = new impleLikeExtends();
		im.action();
		im.get();
		im.get2();
		im.sayHello();

	}

}

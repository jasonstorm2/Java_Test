package Chapter18;


class Tester{
	static{
		System.out.println("Test����ľ�̬��ʼ��������");
	}
}
public class ClassLoadTest {
	
	 static String  classPath = "Chapter18.Tester";

	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		cl.loadClass(classPath);  //�õ��� Tester��   ����һ���࣬�����ᵼ��һ����� ��ʼ��
		System.out.println("ϵͳ����Tester��");
		
		Class.forName(classPath); //�õ��� Tester��   ����forName������� Class ����Ż� ��ʼ��
	}

}

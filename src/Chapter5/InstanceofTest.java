package Chapter5;

/**
 * instanceof ��(type)����ת���Լ� +,-�ȣ����������
 * �жϵ��� ����ʱ������
 * @author Administrator
 *
 */
public class InstanceofTest {
	public static void main(String[] args) {
		//instanceof������������ǣ��ڽ���ǿ������ת��ǰ���ж�ǰһ������ �Ƿ��� ��һ������ ��ʵ�����Ƿ���Գɹ�ת��
		
		//����helloʱʹ��Object����hello�ı���������Object
		//Object��������ĸ��࣬����hello��ʵ��������String
		Object hello = "Hello";
		System.out.println("�ַ����Ƿ���Object���ʵ����"+(hello instanceof Object));
		
		System.out.println("�ַ����Ƿ���String���ʵ����"+(hello instanceof String));
		
		System.out.println("�ַ����Ƿ���Math���ʵ����"+(hello instanceof Math));
		
		System.out.println("�ַ����Ƿ���Comparable���ʵ����"+(hello instanceof Comparable));
		
		Object aob =  new A();
		
		Object bob =  new B();
		
		A  a =  new A();
		
		B b =  new B();
		
		
		//  instanceof ǰ��Ҫ�м̳й�ϵ����Ȼ�ᱨ��
		// �жϵ��� ����ʱ������
		System.out.println(aob instanceof A);//true
		System.out.println(aob instanceof B);//false		
		System.out.println(bob instanceof A);//true
		System.out.println(bob instanceof B);//true
		System.out.println("*******************************");
		System.out.println(a instanceof A);//true
		System.out.println(a instanceof B);//false			
		System.out.println(b instanceof A);//true
		System.out.println(b instanceof B);//true		
		System.out.println("____________________________________");
		System.out.println(aob instanceof C);
		System.out.println(bob instanceof C);
	

		
		
	}

}

class A{
	
	public void get(){
		
	}
	
}

class B extends A{
	
}

class C{
	
}



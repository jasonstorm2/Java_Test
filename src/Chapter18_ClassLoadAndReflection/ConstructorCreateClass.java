package Chapter18_ClassLoadAndReflection;

import java.lang.reflect.Constructor;

public class ConstructorCreateClass {
	public static void main(String[] args) throws Exception {
		Class<?> jf = Class.forName("javax.swing.JFrame");
		//ͨ��Class����,���һ���� �ַ������� �� ����������
		Constructor<?> ct = jf.getConstructor(String.class);
		//����Constructor�����newInstance������������
		Object ob = ct.newInstance("����");
		            jf.newInstance();      //���������Ա���
		System.out.println(ob);
		
	}

}

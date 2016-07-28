package Annotation;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class TestAnn2 {
	/**
	 * ��*author lighter ��* ˵��:�������Annotation��API���÷���μ�javaDoc�ĵ� ��
	 */
	public static void main(String[] args) throws Exception {
		JavaEyer java = new JavaEyer();
		String sss=java.ss;
		//Ϊʲô��ֱ��ʵ����,,,,ʵ�������޷�ʹ��
		String CLASS_NAME = "Annotation.JavaEyer";
		Class<?> test = Class.forName(CLASS_NAME);//---->it may throw a EXCEPTION
		//���������鼯�ϡ���ͳ���ռ�
		Method[] method = test.getMethods();
		boolean flag = test.isAnnotationPresent(Description.class);//�ж��Ƿ����õ�@Description
		boolean FieldFlag = test.isAnnotationPresent(FieldUse.class);
		if (flag) {
			Description des = (Description) test
					.getAnnotation(Description.class);
			System.out.println("����:" + des.value());
			System.out.println("-----------------");		
		}
		
		if(FieldFlag){
			FieldUse field = (FieldUse)test.getAnnotation(FieldUse.class);
			System.out.println("��һ��������"+field.value());
			System.out.println("�����������"+field.theOther());		
			
		}else{
			System.out.println("û�а����������_nananana");
		}
		// ��JavaEyer��һ�������õ�@Name��ȫ���������浽Set��ȥ
		Set<Method> set = new HashSet<Method>();
		for (int i = 0; i < method.length; i++) {
			//�жϷ������õ�@Name
			boolean otherFlag = method[i].isAnnotationPresent(Name.class);
			if (otherFlag)
				set.add(method[i]);
		}
		//����set
		for (Method m : set) {
			Name name = m.getAnnotation(Name.class);
			System.out.println(name.originate());
			System.out.println("����������:" + name.community());
		}
	}
}
